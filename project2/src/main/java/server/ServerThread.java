package server;

import exception.DepositNotFoundException;
import exception.DepositTransactionException;
import exception.ServerIOException;
import exception.TransactionTypeIncorrectException;
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
                int id = transaction.getTransactionId();
                BigDecimal bigDecimalAmount = transaction.getAmount();
                int deposit = transaction.getDeposit();
                String type = String.valueOf(transaction.getTypeTransactionName());
                if (type.equals("deposit")) {
                    try {
                        Deposit dep = (server.findDeposit(deposit));
                        dep.deposit(bigDecimalAmount);

                        dataOutputStream.writeUTF(" transaction deposit :id number" + id + " deposit number  " + deposit + " done");
                        server.writeToFile(" transaction deposit ba id" + id + " deposit number  " + deposit + " done.");
                    } catch (DepositTransactionException e) {
                        System.out.println(e.getMessage());
                        dataOutputStream.writeUTF(" transaction deposit : id number " + id + " deposit number " + deposit +
                                " Failed");
                        server.writeToFile(" transaction deposit: id number " + id + " deposit Number : " + deposit + " Failed");
                    } catch (ServerIOException e) {
                        server.writeToFile(e.getMessage());
                    } catch (DepositNotFoundException e) {
                        server.writeToFile(e.getMessage());
                    } catch (IOException e) {
                        server.writeToFile(e.getMessage());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                } else if (type.equals("withdraw")) {
                    try {
                        Deposit dep = (server.findDeposit(deposit));
                        dep.withdraw(bigDecimalAmount);
                        dataOutputStream.writeUTF(" transaction withdraw :id number" + id + " deposit number  " + deposit + " done");
                        server.writeToFile(" transaction withdraw id number" + id + " deposit number  " + deposit + " done");
                    } catch (DepositTransactionException e) {
                        System.out.println(e.getMessage());
                        dataOutputStream.writeUTF(" transaction withdraw :id number" + id + " deposit number  " + deposit + " Failed");
                        server.writeToFile(" transaction withdraw  id number " + id + " deposit Number : " + deposit + " Failed");
                    } catch (ServerIOException e) {
                        server.writeToFile(e.getMessage());

                    } catch (DepositNotFoundException e) {
                        server.writeToFile(e.getMessage());

                    } catch (IOException e) {
                        server.writeToFile(e.getMessage());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    throw new TransactionTypeIncorrectException("type transaction incorrect");

                }
            }
            dataInputStream.close();
            System.out.println("end " + Thread.currentThread().getName());
            server.writeToFile("end " + Thread.currentThread().getName());
            dataInputStream.close();
            inputStream.close();
        } catch (ClassNotFoundException e) {
            try {
                server.writeToFile(e.getMessage());
            } catch (ServerIOException e1) {
                System.err.println(e1.getMessage());
            }

        } catch (TransactionTypeIncorrectException e) {
            try {
                server.writeToFile(e.getMessage());
            } catch (ServerIOException e1) {
                System.err.println(e1.getMessage());
            }
        } catch (ServerIOException e) {
            try {
                server.writeToFile(e.getMessage());
            } catch (ServerIOException e1) {
                System.err.println(e1.getMessage());
            }
        } catch (IOException e) {
            try {
                server.writeToFile(e.getMessage());
            } catch (ServerIOException e1) {
                System.err.println(e1.getMessage());
            }
        }


        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
