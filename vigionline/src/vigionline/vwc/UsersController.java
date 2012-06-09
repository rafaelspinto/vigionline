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

import vigionline.common.model.Division;
import vigionline.common.model.Role;
import vigionline.common.model.User;
import vigionline.vri.DivisionsResource;
import vigionline.vri.RolesResource;
import vigionline.vri.UsersResource;

import com.sun.jersey.api.view.Viewable;

@RolesAllowed("admin")
@Path("/users")
public class UsersController {

	private UsersResource _usersResource = new UsersResource();

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable getUsersHTML() {
		List<User> users = _usersResource.getUsers();
		return new Viewable("/users", users);
	}

	@GET
	@Path("{idUser}")
	@Produces(MediaType.TEXT_HTML)
	public Viewable getUserHTML(@PathParam("idUser") int idUser) {
		User user = _usersResource.getUser(idUser);
		List<Role> roles = _usersResource.getUserRoles(idUser);
		List<Division> divisions = _usersResource.getUserDivisions(idUser);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("user", user);
		data.put("roles", roles);
		data.put("divisions", divisions);
		return new Viewable("/user", data);
	}

	@GET
	@Path("create")
	@Produces(MediaType.TEXT_HTML)
	public Viewable createUserForm() {
		return new Viewable("/create_user");
	}

	@POST
	@Path("create")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable createUser(@FormParam("name") String name,
			@FormParam("username") String username,
			@FormParam("password") String password) {
		return Controller.getResponse(
				_usersResource.createUser(name, username, password),
				"create_user_succeeded", "create_user_failed");
	}

	@GET
	@Path("{idUser}/edit")
	@Produces(MediaType.TEXT_HTML)
	public Viewable editUserForm(@PathParam("idUser") int idUser) {
		User user = _usersResource.getUser(idUser);
		List<Role> roles = _usersResource.getUserRoles(idUser);
		List<Role> allRoles = new RolesResource().getRoles();
		List<Division> allDivisions = new DivisionsResource().getDivisions();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("user", user);
		data.put("roles", roles);
		data.put("allRoles", allRoles);
		data.put("allDivisions", allDivisions);
		return new Viewable("/edit_user", data);
	}

	@POST
	@Path("{idUser}/edit")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable updateUser(@FormParam("idUser") int idUser,
			@FormParam("name") String name,
			@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("roles") List<String> roles,
			@FormParam("divisions") List<Integer> divisions) {

		return Controller.getResponse(_usersResource.updateUser(idUser, name,
				username, password, roles, divisions), "update_user_succeeded",
				"update_user_failed");
	}

	@POST
	@Path("{idUser}/delete")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable deleteAction(@FormParam("idUser") int idUser) {
		return Controller.getResponse(_usersResource.deleteUser(idUser),
				"delete_user_succeeded", "delete_user_failed");
	}
}
