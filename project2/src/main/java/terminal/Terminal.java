package terminal;

import exception.TerminalIOException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Terminal extends Thread {
    private int terminalId;
    private String type;
    private String outPath;
    private String ip;
    private int port;
    private List<Transaction> transactions;


    public Terminal(int terminalId, String type, String outPath, String ip, int port, List<Transaction> transactions) {
        this.terminalId = terminalId;
        this.type = type;
        this.outPath = outPath;
        this.ip = ip;
        this.port = port;
        this.transactions = new ArrayList<Transaction>(transactions);
    }

    public int getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(int terminalId) {
        this.terminalId = terminalId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void connectToServer() {
        try {
            Socket socket = new Socket("localhost", port);


            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            dataOutputStream.writeInt(transactions.size());
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("terminal");
            rootElement.setAttribute("id", String.valueOf(getTerminalId()));
            document.appendChild(rootElement);
            Element transactionsElement = document.createElement("transactions");
            rootElement.appendChild(transactionsElement);

            for (int i = 0; i < transactions.size(); i++) {
                objectOutputStream.writeObject(transactions.get(i));
                writeToFile("send transaction id " + transactions.get(i).getTransactionId() + " Object");
                Element transactionElement = document.createElement("transaction");
                Attr attr = document.createAttribute("id");
                attr.setValue(String.valueOf(transactions.get(i).getTransactionId()));
                transactionElement.setAttributeNode(attr);
                transactionsElement.appendChild(transactionElement);
                String message = dataInputStream.readUTF();
                transactionElement.appendChild(document.createTextNode(message.contains("done") ? "done" : "fail"));


                System.out.println(message);
                writeToFile("response from the server. " + message);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("response" + terminalId + ".xml"));
            transformer.transform(source, result);

            dataOutputStream.writeUTF("end");
            objectOutputStream.close();
            outputStream.close();
            socket.close();
        } catch (UnknownHostException e) {
            try {
                writeToFile("invalid ip address " + ip);
            } catch (TerminalIOException e1) {
                System.err.println(e1.getMessage());
            }
        } catch (IOException e) {
            try {
                writeToFile("IO Exception in socket");
            } catch (TerminalIOException e1) {
                System.err.println(e1.getMessage());
            }
        } catch (TerminalIOException e) {
            System.err.println(e.getMessage());
        } catch (ParserConfigurationException e) {
            try {
                writeToFile("a serious configuration error.");
            } catch (TerminalIOException e1) {
                System.err.println(e1.getMessage());
            }
        } catch (TransformerConfigurationException e) {
            try {
                writeToFile("a serious configuration error");
            } catch (TerminalIOException e1) {
                System.err.println(e1.getMessage());
            }
        } catch (TransformerException e) {
            try {
                writeToFile("an exceptional condition  occured during the transformation process.");
            } catch (TerminalIOException e1) {
                System.err.println(e1.getMessage());
            }
        }
    }

    private static final Boolean lock = true;

    public void writeToFile(String str) throws TerminalIOException {
        synchronized (lock) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(outPath, "rw");
                randomAccessFile.seek(randomAccessFile.length());
                randomAccessFile.writeBytes(str);
                randomAccessFile.writeBytes("\r\n");
            } catch (FileNotFoundException e) {
                throw new TerminalIOException("Terminal Log not found", e);
            } catch (IOException e) {
                throw new TerminalIOException("IO Exception in terminal log file", e);
            }
        }
    }

    @Override
    public void run() {
        connectToServer();
    }
}
