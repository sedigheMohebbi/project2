package terminal;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Terminal {
    private int id;
    private String type;
    private String path;
    private String ip;
    private int port;
    private List<Transaction> transactions;


    public Terminal(int id, String type, String path, String ip, int port, List<Transaction> transactions) {
        this.id = id;
        this.type = type;
        this.path = path;
        this.ip = ip;
        this.port = port;
        this.transactions = new ArrayList<Transaction>(transactions);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public void ConnectToServer() throws IOException {
        Socket socket = new Socket("localhost", port);
        try {

            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            //Todo xml
            dataOutputStream.writeInt(transactions.size());
            for (int i = 0; i < transactions.size(); i++) {
                objectOutputStream.writeObject(transactions.get(i));
                writeToFile("send transaction Object");
                String message = dataInputStream.readUTF();
                System.out.println(message);
                writeToFile("response from the server.");
            }
            dataOutputStream.writeUTF("end");
            objectOutputStream.close();
            outputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void writeToFile(String str) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");
        randomAccessFile.seek(randomAccessFile.length());
        randomAccessFile.writeBytes(str);
        randomAccessFile.writeBytes("\r\n");
    }


}
