import javax.swing.text.Position;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class TestWriteToFile {
    public void write(String str) throws IOException {
        RandomAccessFile file = new RandomAccessFile("./project", "rw");
        file.seek(file.length());
        file.writeBytes(str);
        file.writeBytes("\r\n");
//
        System.out.println(file.readLine());

    }

    public static void main(String[] args) throws IOException {
        TestWriteToFile testWriteToFile = new TestWriteToFile();
        testWriteToFile.write("hola");
        testWriteToFile.write("come estan");

    }
}
