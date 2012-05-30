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
import vigionline.common.model.Manufacturer;
import vigionline.common.model.Model;

@RolesAllowed("admin")
@Path("/api/manufacturers")
public class ManufacturersResource {

	private final IDatabase _database = DatabaseLocator.Get();
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Manufacturer> getManufacturers()
	{	
		List<Manufacturer> manufacturers = null;
		try {
			manufacturers = _database.getManufacturers();
		} catch (SQLException e) {
			throw new WebApplicationException(500);
		}
		if( manufacturers == null)
			throw new WebApplicationException(404);
		return manufacturers;
	}

	@GET
	@Path("{idManufacturer}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Manufacturer getManufacturer(@PathParam("idManufacturer") int idManufacturer) {
		Manufacturer manufacturer = null;
		try {
			manufacturer = _database.getManufacturer(idManufacturer);
		} catch (SQLException e) {
			throw new WebApplicationException(500);
		}
		if( manufacturer == null )
			throw new WebApplicationException(404);
		return manufacturer;
	}

	@POST
	@Path("{idManufacturer}/edit")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updateManufacturer(int idManufacturer,
			String name) {
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setIdManufacturer(idManufacturer);
		manufacturer.setName(name);
		try {
			_database.updateManufacturer(manufacturer);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}

	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createManufacturer(@FormParam("name") String name) {
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(name);
		try {
			_database.createManufacturer(manufacturer);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}

	@POST
	@Path("{idManufacturer}/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteManufacturer(@FormParam("idManufacturer") int idManufacturer) {
		try {
			_database.deleteManufacturer(idManufacturer);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}
	
	@GET
	@Path("{idManufacturer}/models")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Model> getManufacturerModels(@PathParam("idManufacturer") int idManufacturer) {
		List<Model> models = null;
		try {
			models = _database.getModelsByManufacturer(idManufacturer);
		} catch (SQLException e) {
			throw new WebApplicationException(500);
		}
		if( models == null )
			throw new WebApplicationException(404);
		return models;
	}
}
