package vigionline.vwc;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import vigionline.common.model.Location;
import vigionline.vri.LocationsResource;

import com.sun.jersey.api.view.Viewable;

@Path("/locations")
public class LocationsController {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable getLocationsHtml()
	{
		List<Location> locations = new LocationsResource().getLocations();
		return new Viewable("/locations", locations);
	}
	
	@GET
	@Path("create")
	@Produces(MediaType.TEXT_HTML)
	public Viewable createLocationForm()
	{
		return new Viewable("/create_location", new Location());
	}
	
	@POST
	@Path("create")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable getLocationByIdHtml(@FormParam("name") String name)
	{
		Response response = new LocationsResource().createLocation(name);
		return response.getStatus() == 200 ? 
				new Viewable("/success", new Message("Location Created Successfully", "")) :
				new Viewable("/error", new Message("Could not create Location", ""));
	}
}
