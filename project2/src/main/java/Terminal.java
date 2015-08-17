import java.io.*;
import java.net.Socket;

public class Terminal {
    public void ConnectToServer() throws IOException {
        Socket socket = new Socket("localhost",8080);
        try {
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF("hi");
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String st = dataInputStream.readUTF();
            System.out.println(st);
            Thread.sleep(10000);
            dataInputStream.close();
            inputStream.close();
            dataOutputStream.close();
            outputStream.close();

        } catch (Exception e) {
          e.printStackTrace();
        }
        socket.close();
    }
}
