package server;

import exception.DepositNotFoundException;
import exception.ServerIOException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server {
    private int port;
    private String outLog;
    private List<Deposit> deposits = new ArrayList<Deposit>();

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

    public void connectionToTerminal() {
        try {


            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                System.out.println("Waiting for clients...");
                writeToFile("waiting for client");
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket, this);
                serverThread.start();
            }
        } catch (IOException e) {
            try {
                writeToFile("IO exception in server socket");
            } catch (ServerIOException e1) {
                System.err.println(e1.getMessage());
            }

        } catch (ServerIOException e) {
            System.err.println(e.getMessage());
        }
    }

    public Deposit findDeposit(int depositId) throws DepositNotFoundException, ServerIOException {
        for (int i = 0; i < deposits.size(); i++) {
            if (deposits.get(i).getId() == depositId) {
                writeToFile(" deposit Number " + depositId + " is correct.");
                return deposits.get(i);
            }
        }

        throw new DepositNotFoundException(" Deposit Id is incorrect " + "id :" + depositId);
    }

    private static final Boolean lock = true;

    public void writeToFile(String str) throws ServerIOException {
        synchronized (lock) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(outLog, "rw");
                randomAccessFile.seek(randomAccessFile.length());
                randomAccessFile.writeBytes(str);
                randomAccessFile.writeBytes("\n");
                randomAccessFile.close();
            } catch (FileNotFoundException e) {
                throw new ServerIOException("server log file not found", e);
            } catch (IOException e) {
                throw new ServerIOException("IO Exception in file", e);
            }

        }
    }


}
