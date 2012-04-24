package vigionline.vri;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import vigionline.common.database.DatabaseLocator;
import vigionline.common.database.IDatabase;
import vigionline.common.model.UserRole;

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
