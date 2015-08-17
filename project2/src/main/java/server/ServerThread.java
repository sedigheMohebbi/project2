package server;

import java.io.*;
import java.net.Socket;


public class ServerThread extends Thread {


    public void run(Socket socket) throws IOException {
        try {

            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String st = dataInputStream.readUTF();
            System.out.println(st);
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF("dar server neveshte shode ");
            dataInputStream.close();
            outputStream.close();
            dataInputStream.close();
            inputStream.close();
            Thread.sleep(1000);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        socket.close();


    }

}
