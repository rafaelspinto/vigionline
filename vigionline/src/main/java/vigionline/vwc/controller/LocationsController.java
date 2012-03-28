package vigionline.vwc.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.sun.jersey.api.view.Viewable;

@Path("/locations")
public class LocationsController {

	@GET
	public Viewable getLocations()
	{
		return new Viewable("vigionline/vwc/view/locations.jsp");
	}
}
