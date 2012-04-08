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

import com.sun.jersey.api.view.Viewable;

import vigionline.common.model.User;
import vigionline.vri.UsersResource;

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
		return new Viewable("/user", user);
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
		return new Viewable("/edit_user", user);
	}

	@POST
	@Path("{idUser}/edit")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable updateUser(@FormParam("idUser") int idUser,
			@FormParam("name") String name,
			@FormParam("username") String username,
			@FormParam("password") String password) {
		return Controller.getResponse(
				_usersResource.updateUser(idUser, name, username, password),
				"update_user_succeeded", "update_user_failed");
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
