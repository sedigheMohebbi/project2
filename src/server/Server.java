package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Dotin School1 on 8/16/2015.
 */
public class Server {
    public void ConnectionToTerminal() throws IOException {
        ServerSocket serverSocket= new ServerSocket(8080);
        Socket socket=serverSocket.accept();
        OutputStream outputStream=socket.getOutputStream();
        DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
        dataOutputStream.writeUTF("output : ");
        InputStream inputStream=socket.getInputStream();
        DataInputStream dataInputStream=new DataInputStream(inputStream);
        String input=new String(dataInputStream.readUTF());
        System.out.println(input+" server");
        dataInputStream.close();
        inputStream.close();
        dataOutputStream.close();
        outputStream.close();
        socket.close();
    }
}
