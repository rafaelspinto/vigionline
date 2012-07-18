package vigionline.vce.record;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import vigionline.vce.record.RecordHandler;

public class RecordManagerContextListener implements ServletContextListener {

	private RecordHandler _recordHandler;

	@Override
	public void contextInitialized(ServletContextEvent sc) {
		ServletContext context = sc.getServletContext();
		_recordHandler = new RecordHandler();
		context.setAttribute("RecordHandler", _recordHandler);
		System.out.println("Started RecordHandler");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		_recordHandler.shutdown();
		System.out.println("Ended RecordHandler");
	}
}
