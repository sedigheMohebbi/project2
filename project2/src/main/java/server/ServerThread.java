package server;

import java.io.*;
import java.net.Socket;

/**
 * Created by Dotin School1 on 8/17/2015.
 */
public class ServerThread extends Thread {
    public void run(Socket socket) throws IOException {
        OutputStream outputStream=socket.getOutputStream();
        DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
        try {
            dataOutputStream.writeUTF("output : ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream inputStream=socket.getInputStream();
        DataInputStream dataInputStream=new DataInputStream(inputStream);
        String input= null;
        try {
            input = new String(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(input+ "server");
        try {
            dataInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        socket.close();
    }
}
