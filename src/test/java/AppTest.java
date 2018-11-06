import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class AppTest extends CamelTestSupport {
    private final String FROM = "C:/temp/from1";
    private final String TO = "C:/temp/to1";

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:" + FROM).to("file:" + TO);
            }
        };
    }

    @Test
    public void parseRouteTest() throws IOException, SAXException, ParserConfigurationException {
        File file = new File("src/test/resources/config.xml");
        List<Route> routes = Util.parseRoute(file);
        Assert.assertEquals("C:\\temp\\from1", routes.get(0).getFolderFrom());
        Assert.assertEquals("C:\\temp\\to1", routes.get(0).getFolderTo());
    }

    @Test
    public void moveFileTest() throws InterruptedException {
        template.sendBodyAndHeader("file:" + FROM, "Hello World",
                Exchange.FILE_NAME, "hello.txt");
        Thread.sleep(1000);
        File target = new File(TO + "/hello.txt");
        assertTrue("File not moved", target.exists());
    }

    @Test
    public void addRoutesTest() throws Exception {
        context.addRoutes(new MyRouteBuilder("direct:foo", "mock:foo"));
        assertMockEndpointsSatisfied();
        assertEquals(2, context.getRoutes().size());
    }


}

