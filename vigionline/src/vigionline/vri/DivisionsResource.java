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
import vigionline.common.model.Division;

@RolesAllowed("admin")
@Path("/api/divisions")
public class DivisionsResource {

	private final IDatabase _database = DatabaseLocator.Get();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Division> getDivisions() {
		List<Division> divisions = null;
		try {
			divisions = _database.getDivisions();
		} catch (SQLException e) {
			throw new WebApplicationException(500);
		}
		if (divisions == null)
			throw new WebApplicationException(404);
		return divisions;
	}

	@GET
	@Path("{idDivision}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Division getDivision(@PathParam("idDivision") int idDivision) {
		Division division = null;
		try {
			division = _database.getDivision(idDivision);
		} catch (SQLException e) {
			throw new WebApplicationException(500);
		}
		if (division == null)
			throw new WebApplicationException(404);
		return division;
	}

	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createDivision(@FormParam("name") String name) {
		Division division = new Division();
		division.setName(name);
		try {
			_database.createDivision(division);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}

	@POST
	@Path("{idDivision}/edit")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updateDivision(@FormParam("idDivision") int idDivision,
			@FormParam("name") String name) {
		Division division = new Division();
		division.setIdDivision(idDivision);
		division.setName(name);
		try {
			_database.updateDivision(division);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}
	
	@Path("{idDivision}/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteDivision(@FormParam("idDivision") int idDivision) {
		try {
			_database.deleteDivision(idDivision);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}
}
