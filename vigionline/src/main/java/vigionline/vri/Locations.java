package vigionline.vri;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import vigionline.common.database.DatabaseLocator;
import vigionline.common.database.IDatabase;
import vigionline.common.model.Location;

@Path("/api/locations")
public class Locations {

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
		if( locations == null || locations.size() == 0)
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
}
