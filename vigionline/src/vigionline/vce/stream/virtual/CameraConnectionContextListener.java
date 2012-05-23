package vigionline.vce.stream.virtual;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CameraConnectionContextListener implements ServletContextListener {

	private StreamHandler _streamHandler;

	@Override
	public void contextInitialized(ServletContextEvent sc) {
		ServletContext context = sc.getServletContext();
		_streamHandler = new StreamHandler();
		context.setAttribute("StreamHandler", _streamHandler);
		System.out.println("Started StreamHandler");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		_streamHandler.shutdown();
		System.out.println("Ended StreamHandler");
	}
}
