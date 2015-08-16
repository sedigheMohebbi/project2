package terminal;

import java.io.*;
import java.net.Socket;

/**
 * Created by Dotin School1 on 8/16/2015.
 */
public class Terminal {
    public void ConnectToServer() throws IOException {
        Socket socket=new Socket("localhost",8080);
        OutputStream outputStream=socket.getOutputStream();
        DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
        dataOutputStream.writeUTF("output  : *");
        InputStream inputStream=socket.getInputStream();
        DataInputStream dataInputStream=new DataInputStream(inputStream);
        String dataInput= new String(dataInputStream.readUTF());
        System.out.println(dataInput +" terminal");
        dataInputStream.close();
        inputStream.close();
        dataOutputStream.close();
        outputStream.close();
        socket.close();

    }
}
