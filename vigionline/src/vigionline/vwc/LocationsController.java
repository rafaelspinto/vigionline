package vigionline.vwc;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import vigionline.common.model.Location;
import vigionline.vri.LocationsResource;

import com.sun.jersey.api.view.Viewable;

@Path("/locations")
public class LocationsController {

	private LocationsResource _locationsResource = new LocationsResource();
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable getLocationsHtml()
	{
		List<Location> locations = _locationsResource.getLocations();
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
		return Controller.getResponse(_locationsResource.createLocation(name), "Location Created Successfully", "Location Create Failed");
	}
	
	@GET
	@Path("{idLocation}")
	@Produces(MediaType.TEXT_HTML)
	public Viewable getLocation(@PathParam("idLocation") int idLocation)
	{
		Location location = _locationsResource.getLocation(idLocation);
		return new Viewable("/location", location);
	}
	
	@GET
	@Path("{idLocation}/edit")
	@Produces(MediaType.TEXT_HTML)
	public Viewable editLocation(@PathParam("idLocation") int idLocation)
	{
		Location location = _locationsResource.getLocation(idLocation);
		return new Viewable("/edit_location", location);
	}
	
	@POST
	@Path("{idLocation}/edit")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable updateLocation(@PathParam("idLocation") int idLocation, @FormParam("name") String name)
	{
		Location location = new Location();
		location.setName(name);
		location.setIdLocation(idLocation);
		return Controller.getResponse(_locationsResource.updateLocation(idLocation, location), "Location Updated Successfully", "Location Update Failed");
	}
}
