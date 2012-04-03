package vigionline.vwc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.sun.jersey.api.view.Viewable;

@Path("/")
public class HomeController {
	
	@GET
	public Viewable index()
	{
		return new Viewable("/index");
	}
}
