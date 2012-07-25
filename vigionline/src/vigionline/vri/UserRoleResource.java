package vigionline.vri;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import vigionline.common.model.UserRole;
import vigionline.vce.database.DatabaseLocator;
import vigionline.vce.database.IDatabase;

@RolesAllowed("admin")
@Path("/api/usersroles")
public class UserRoleResource {
	
	private final IDatabase _database = DatabaseLocator.Get();
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<UserRole> getAllUsersRoles() {
		List<UserRole> usersRoles = null;
		try {
			usersRoles = _database.getUsersRoles();
		} catch (SQLException e) {
			throw new WebApplicationException(500);
		}
		if (usersRoles == null)
			throw new WebApplicationException(404);
		return usersRoles;
	}
}
