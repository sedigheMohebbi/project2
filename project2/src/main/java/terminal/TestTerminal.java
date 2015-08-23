package terminal;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class TestTerminal {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        XmlParser xmlParser = new XmlParser();
//        Terminal terminal = xmlParser.parseXml();
//        terminal.connectToServer();

        Terminal terminal = new Terminal("terminal.xml");
        terminal.connectToServer();

        Terminal terminal2 = new Terminal("terminal2.xml");
        terminal2.connectToServer();
    }
}
