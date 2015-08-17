package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public void ConnectionToTerminal() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while(true){

        Socket socket = serverSocket.accept();
        ServerThread serverThread = new ServerThread(socket);
        serverThread.start();
    }}

}
