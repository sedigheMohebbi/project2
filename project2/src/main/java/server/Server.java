package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public void ConnectionToTerminal() throws IOException {
        ServerSocket serverSocket= new ServerSocket(8080);
        Socket socket=serverSocket.accept();
        ServerTheared serverTheared=new ServerTheared();
        serverTheared.run(socket);
    }
}
