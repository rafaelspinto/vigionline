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
import vigionline.common.model.Role;

@RolesAllowed("admin")
@Path("/api/roles")
public class RolesResource {

	private final IDatabase _database = DatabaseLocator.Get();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Role> getRoles() {
		List<Role> roles = null;
		try {
			roles = _database.getRoles();
		} catch (SQLException e) {
			throw new WebApplicationException(500);
		}
		if (roles == null)
			throw new WebApplicationException(404);
		return roles;
	}

	@GET
	@Path("{idRole}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Role getRole(@PathParam("idRole") int idRole) {
		Role role = null;
		try {
			role = _database.getRole(idRole);
		} catch (SQLException e) {
			throw new WebApplicationException(500);
		}
		if (role == null)
			throw new WebApplicationException(404);
		return role;
	}

	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createRole(@FormParam("name") String name) {
		Role role = new Role();
		role.setName(name);
		try {
			_database.createRole(role);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}

	@POST
	@Path("{idRole}/edit")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updateRole(@FormParam("idRole") int idRole,
			@FormParam("name") String name) {
		Role role = new Role();
		role.setIdRole(idRole);
		role.setName(name);
		try {
			_database.updateRole(role);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}

	@Path("{idRole}/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteRole(@FormParam("idRole") int idRole) {
		try {
			_database.deleteRole(idRole);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}
}
