package server;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;
    private static int a = 0;
    private final Boolean ID = true;

    public ServerThread(Socket socket) {
        this.socket = socket;

    }

    public void run() {

        try {
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String st = dataInputStream.readUTF();
            System.out.println(st);
            OutputStream outputStream = socket.getOutputStream();

            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF("hello " + Thread.currentThread().getName());



            dataInputStream.close();
            outputStream.close();
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

    private int sum() throws InterruptedException {
        int b;
        for (int i = 0; i < 100; i++) {
            b = a + 1;
            a = b;
            Thread.sleep(50);
        }
        return a;
    }

}
