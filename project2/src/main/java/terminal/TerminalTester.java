package terminal;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class TerminalTester {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        XmlParser xmlParser = new XmlParser();
        Terminal terminal = xmlParser.parseXmlAndCreateTerminal("terminal.xml");
        terminal.start();
        Terminal terminal1 = xmlParser.parseXmlAndCreateTerminal("terminal1.xml");
        terminal1.start();

    }
}
