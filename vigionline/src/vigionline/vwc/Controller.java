package vigionline.vwc;

import javax.ws.rs.core.Response;

import com.sun.jersey.api.view.Viewable;

public class Controller {

	public static Viewable getResponse(Response response, String msgOk, String msgNotOk )
	{
		return response.getStatus() == 200 ? 
				new Viewable("/success", new Message(msgOk, "")) :
				new Viewable("/error", new Message(msgNotOk, ""));
	}
}
