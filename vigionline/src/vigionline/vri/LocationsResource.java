package vigionline.vri;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import vigionline.common.database.DatabaseLocator;
import vigionline.common.database.IDatabase;
import vigionline.common.model.Camera;
import vigionline.common.model.Location;

@RolesAllowed("admin")
@Path("/api/locations")
public class LocationsResource {
	
	private final IDatabase _database = DatabaseLocator.Get();
		
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Location> getLocations()
	{	
		List<Location> locations = null;
		try {
			locations = _database.getLocations();
		} catch (SQLException e) {
			throw new WebApplicationException(500);
		}
		if( locations == null )
			throw new WebApplicationException(404);
		return locations;
	}
	
	@GET
	@Path("{idLocation}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Location getLocation(@PathParam("idLocation") int idLocation)
	{		
		Location location = null;
		try {
			location = _database.getLocation(idLocation);
		} catch (SQLException e) {
			throw new WebApplicationException(500);
		}
		if( location == null )
			throw new WebApplicationException(404);
		return location;
	}
	
	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createLocation(@FormParam("name") String name)
	{
		Location location = new Location();
		location.setName(name);
		try {
			_database.createLocation(location);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}
	
	@POST
	@Path("{idLocation}/edit")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updateLocation(@FormParam("idLocation") int idLocation, @FormParam("name") String name)
	{
		Location location = new Location();
		location.setIdLocation(idLocation);
		location.setName(name);
		try {
			_database.updateLocation(location);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}
	
	@Path("{idLocation}/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteLocation(@FormParam("idLocation") int idLocation)
	{
		try {
			_database.deleteLocation(idLocation);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}

	@GET
	@Path("{idLocation}/cameras")
	public List<Camera> getCameras(@PathParam("idLocation") int idLocation) {
		List<Camera> cameras = null;
		try {
			cameras = _database.getCamerasByLocation(idLocation);
			return cameras;
		} catch (SQLException e) {
			throw new WebApplicationException(500);
		}
	}
}
