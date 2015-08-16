import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XmlParser {
    public void ParsXml() throws ParserConfigurationException, IOException, SAXException {
        File file = new File("./terminal.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document dom = documentBuilder.parse(file);
        Element terminal = dom.getDocumentElement();
        int idTerminal = Integer.parseInt(terminal.getAttribute("id"));
        String typeTerminal = terminal.getAttribute("type");
        Element server = (Element) terminal.getElementsByTagName("server").item(0);
        String ip = server.getAttribute("ip");
        int port = Integer.parseInt(server.getAttribute("port"));
        Element outLog = (Element) terminal.getElementsByTagName("outLog").item(0);
        String path = outLog.getAttribute("path");
        Element transactions = (Element) terminal.getElementsByTagName("transactions").item(0);
        ArrayList transactionsList= new ArrayList();
        NodeList transactionsChildNodes = transactions.getChildNodes();
        for (int i = 0; i < transactionsChildNodes.getLength(); i++) {
            if (transactionsChildNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element transactionElement = (Element) transactionsChildNodes.item(i);
                int transactionId = Integer.parseInt(transactionElement.getAttribute("id"));
                String type = transactionElement.getAttribute("type");
                int amount=Integer.parseInt(transactionElement.getAttribute("amount"));
                int deposit=Integer.parseInt(transactionElement.getAttribute("deposit"));


            }
        }
        System.out.println(path);
    }
}
