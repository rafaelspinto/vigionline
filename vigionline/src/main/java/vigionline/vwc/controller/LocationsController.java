package vigionline.vwc.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.view.Viewable;

@Path("locations")
public class LocationsController {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable getLocations()
	{
		return new Viewable("/locations.jsp",this);
	}
}
