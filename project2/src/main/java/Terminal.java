import terminal.Transaction;

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
        Socket socket = new Socket("localhost", 8080);
        try {
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF("hi server ");
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String st = dataInputStream.readUTF();
            System.out.println(st);
            System.out.println(dataInputStream.readInt());
            Thread.sleep(1000);
            dataInputStream.close();
            inputStream.close();
            dataOutputStream.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        socket.close();
    }


}
