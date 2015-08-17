import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Dotin School1 on 8/16/2015.
 */
public class MainTerminal {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        Terminal terminal=new Terminal();
        terminal.ConnectToServer();



//        XmlParser xmlParser=new XmlParser();
//        xmlParser.ParsXml();
    }
}
