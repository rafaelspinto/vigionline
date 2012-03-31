package vigionline.vwc;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}
