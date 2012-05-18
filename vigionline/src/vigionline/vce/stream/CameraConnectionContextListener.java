package vigionline.vce.stream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CameraConnectionContextListener implements ServletContextListener {

	private CameraConnectionHandler _cameraConnectionHandler;

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext context = arg0.getServletContext();
		_cameraConnectionHandler = new CameraConnectionHandler();
		context.setAttribute("CameraConnectionHandler",
				_cameraConnectionHandler);
		System.out.println("Started CameraConnectionContextListener");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		_cameraConnectionHandler.shutdown();
		System.out.println("Ended CameraConnectionContextListener");
	}
}
