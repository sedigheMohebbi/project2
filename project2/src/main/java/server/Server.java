package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server {
    private int port;
    private String outLog;
    private List<Deposit> deposits=new ArrayList<Deposit>();

    public Server(int port, String outLog, List<Deposit> deposits) {
        this.port = port;
        this.outLog = outLog;
        this.deposits = deposits;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getOutLog() {
        return outLog;
    }

    public void setOutLog(String outLog) {
        this.outLog = outLog;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    public void ConnectionToTerminal() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            System.out.println("Waiting for clients...");
            Socket socket = serverSocket.accept();
            ServerThread serverThread = new ServerThread(socket,this);
            serverThread.start();
        }
    }
    public Deposit findDeposit(int depositId) throws Exception {
        for(int i=0;i<deposits.size();i++){
            if(deposits.get(i).getId()==depositId){
                return deposits.get(i);
            }
        }
        throw new Exception("shomare transaction sahih nist");
    }
    public void writeToFile(String str) throws IOException {
        RandomAccessFile randomAccessFile=new RandomAccessFile(outLog,"rw");
        randomAccessFile.writeUTF(str);
    }

}
