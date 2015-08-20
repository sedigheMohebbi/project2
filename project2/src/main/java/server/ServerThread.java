package server;

import terminal.Transaction;

import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerThread extends Thread {
    private Socket socket;
    List<Transaction> transaction = new ArrayList<Transaction>();
    Server server;

    public ServerThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            ObjectInputStream in = new ObjectInputStream(inputStream);
            int count = dataInputStream.readInt();
            for (int i = 0; i < count; i++) {
                Transaction transaction = (Transaction) in.readObject();
                int id = transaction.getIdTransaction();
                BigDecimal bigDecimalAmount = transaction.getAmount();
                int deposit = transaction.getDeposit();
                String type = String.valueOf(transaction.getTypeTransactionName());
                if (type.equals("deposit")) {
                    try {
                        Deposit dep = (server.findDeposit(deposit));
                        dep.deposit(bigDecimalAmount);

                        dataOutputStream.writeUTF("tranaction deposit ba id" + id + "be shomare " + deposit + "anjam shod");
                        server.writeToFile("tranaction deposit ba id" + id + "be shomare " + deposit + "anjam shod");
                    } catch (Exception e) {
                        dataOutputStream.writeUTF("tranaction deposit ba id" + id + "be shomare " + deposit + "anjam nashod");
                        server.writeToFile("tranaction deposit ba id" + id + "be shomare " + deposit + "anjam nashod");
                    }

                } else if (type.equals("withdraw")) {
                    try {
                        Deposit dep = (server.findDeposit(deposit));
                        dep.withdraw(bigDecimalAmount);
                        dataOutputStream.writeUTF("tranaction withdraw ba id" + id + "be shomare " + deposit + "anjam shod");
                        server.writeToFile("tranaction withdraw ba id" + id + "be shomare " + deposit + "anjam shod");
                    } catch (Exception e) {
                        dataOutputStream.writeUTF("tranaction withdraw ba id" + id + "be shomare " + deposit + "anjam nashod");
                        server.writeToFile("tranaction withdraw ba id" + id + "be shomare " + deposit + "anjam nashod");
                    }
                } else {
                    throw new Exception("type transaction mojod nist");
                }
            }
            dataInputStream.close();
            System.out.println("end " + Thread.currentThread().getName());
            server.writeToFile("end " + Thread.currentThread().getName());
            dataInputStream.close();
            inputStream.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
