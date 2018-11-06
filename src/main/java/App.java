import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.log4j.Logger;


import java.io.File;
import java.util.List;

public class App {
    private static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        //парсим xml с помощью DOM
        File file = new File("src/main/resources/config.xml");
        List<Route> routes = Util.parseRoute(file);
        // перебираем спарсеные данные, создаем роуты под каждый путь и копируем файлы
        for (int i = 0; i<routes.size(); i++){
            logger.info("Building path...");
            fileCopyWithCamel(routes.get(i));
        }
    }

    public static void fileCopyWithCamel (Route route) throws Exception {
        MyRouteBuilder routeBuilder = new MyRouteBuilder(route.getFolderFrom(),route.getFolderTo());
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(routeBuilder);
        logger.info("Start copy file");
        context.start();
        Thread.sleep(10000);
        context.stop();
        logger.info("Stop copy file");
    }
}
