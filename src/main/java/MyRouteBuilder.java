import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {
    private String folderFrom;
    private String folderTo;

    public MyRouteBuilder(String folderFrom, String folderTo) {
        this.folderFrom = folderFrom;
        this.folderTo = folderTo;
    }

    @Override
    public void configure() throws Exception {
        from("file:" + folderFrom + "?noop=true")
                .to("file:" + folderTo);
    }
}
