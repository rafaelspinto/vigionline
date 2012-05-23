package vigionline.vce.stream.virtual;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CameraConnectionContextListener implements ServletContextListener {

	private StreamHandler _streamHandler;
	private StreamBroker _streamBroker;

	@Override
	public void contextInitialized(ServletContextEvent sc) {
		ServletContext context = sc.getServletContext();
		_streamHandler = new StreamHandler();
		_streamBroker = new StreamBroker();
		context.setAttribute("StreamHandler", _streamHandler);
		context.setAttribute("StreamBroker", _streamBroker);
		System.out.println("Started StreamHandler");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		_streamHandler.shutdown();
		System.out.println("Ended StreamHandler");
	}

	public StreamBroker getBroker() {
		return _streamBroker;
	}
}
