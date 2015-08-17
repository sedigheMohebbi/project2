import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class MainTerminal {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

            Terminal terminal = new Terminal();
            terminal.ConnectToServer();



//        XmlParser xmlParser=new XmlParser();
//        xmlParser.ParsXml();
    }
}
