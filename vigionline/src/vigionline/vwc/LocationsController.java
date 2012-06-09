package vigionline.vwc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import vigionline.common.model.Camera;
import vigionline.common.model.Location;
import vigionline.vri.LocationsResource;

import com.sun.jersey.api.view.Viewable;

@RolesAllowed("admin")
@Path("/locations")
public class LocationsController {

	private LocationsResource _locationsResource = new LocationsResource();
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable getLocationsHTML()
	{
		List<Location> locations = _locationsResource.getLocations();
		return new Viewable("/locations", locations);
	}
	
	@GET
	@Path("create")
	@Produces(MediaType.TEXT_HTML)
	public Viewable createLocationForm()
	{
		return new Viewable("/create_location");
	}
	
	@POST
	@Path("create")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable createLocation(@FormParam("name") String name)
	{
		return Controller.getResponse(_locationsResource.createLocation(name), "create_location_succeeded", "create_location_failed");
	}
	
	@GET
	@Path("{idLocation}")
	@Produces(MediaType.TEXT_HTML)
	public Viewable getLocation(@PathParam("idLocation") int idLocation)
	{
		List<Camera> cameras = new LocationsResource().getCameras(idLocation);
		Location location = _locationsResource.getLocation(idLocation);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("location", location);
		data.put("cameras", cameras);
		return new Viewable("/location", data);
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
	public Viewable updateLocation(@FormParam("idLocation") int idLocation, @FormParam("name") String name)
	{
		Location location = new Location();
		location.setName(name);
		location.setIdLocation(idLocation);
		return Controller.getResponse(_locationsResource.updateLocation(idLocation, name), "update_location_succeeded", "update_location_failed");
	}
	
	@POST
	@Path("{idLocation}/delete")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable deleteLocation(@PathParam("idLocation") int idLocation)
	{
		return Controller.getResponse(_locationsResource.deleteLocation(idLocation), "delete_location_succeeded", "delete_location_failed");
	}
}
