
package vigionline;

import com.sun.jersey.api.container.grizzly2.servlet.GrizzlyWebContainerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.spi.container.servlet.ServletContainer;

import org.glassfish.grizzly.http.server.HttpServer;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.UriBuilder;


public class Main {

    private static int getPort(int defaultPort) {
        //grab port from environment, otherwise fall back to default port 9998
        String httpPort = System.getProperty("jersey.test.port");
        if (null != httpPort) {
            try {
                return Integer.parseInt(httpPort);
            } catch (NumberFormatException e) {
            }
        }
        return defaultPort;
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(getPort(9998)).build();
    }

    public static final URI BASE_URI = getBaseURI();
    
    protected static HttpServer startServer() throws IOException {
        final Map<String, String> initParams = new HashMap<String, String>();

        initParams.put(PackagesResourceConfig.PROPERTY_PACKAGES, "vigionline");
        initParams.put(ServletContainer.PROPERTY_WEB_PAGE_CONTENT_REGEX, "/(images|css|views)/.*");
        initParams.put(ServletContainer.JSP_TEMPLATES_BASE_PATH, "/views");
        initParams.put(ServletContainer.FEATURE_FILTER_FORWARD_ON_404, "true");
        initParams.put(ResourceConfig.FEATURE_IMPLICIT_VIEWABLES, "true");
        initParams.put(ResourceConfig.FEATURE_REDIRECT, "true");
        initParams.put(ResourceConfig.FEATURE_TRACE, "true");
        
        System.out.println("Starting Vigionline...");
        return GrizzlyWebContainerFactory.create(BASE_URI, initParams);
    }
    
    public static void main(String[] args) throws IOException {
        // Grizzly 2 initialization
        HttpServer httpServer = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...",
                BASE_URI));
        System.in.read();
        httpServer.stop();
    }    
}
