
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Util {
    private static final Logger logger = Logger.getLogger(Util.class);

    private Util() {

    }

    public static List<Route> parseRoute(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        NodeList routeNodeList = document.getElementsByTagName("route");
        final List<Route> routeList = new ArrayList<Route>();
        logger.info("Start parsing file.");
        for (int i = 0; i < routeNodeList.getLength(); i++) {
            Element elementRoute = (Element) routeNodeList.item(i);
            Route route = new Route(
                    elementRoute.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue(),
                    elementRoute.getElementsByTagName("from").item(0).getChildNodes().item(0).getNodeValue(),
                    elementRoute.getElementsByTagName("to").item(0).getChildNodes().item(0).getNodeValue()
            );
            routeList.add(route);
        }
        logger.info("Finish parsing file");
        return routeList;
    }
}
