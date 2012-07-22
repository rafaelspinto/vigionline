package vigionline.vwc;

import java.util.List;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import vigionline.common.model.Role;
import vigionline.vri.RolesResource;

import com.sun.jersey.api.view.Viewable;

@RolesAllowed("admin")
@Path("/roles")
public class RolesController {

	private RolesResource _rolesResource = new RolesResource();

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable getRolesHTML() {
		List<Role> roles = _rolesResource.getRoles();
		return new Viewable("/roles", roles);
	}

	@GET
	@Path("{idRole}")
	@Produces(MediaType.TEXT_HTML)
	public Viewable getRoleHTML(@PathParam("idRole") int idRole) {
		Role role = _rolesResource.getRole(idRole);
		return new Viewable("/role", role);
	}

	@DenyAll
	@GET
	@Path("create")
	@Produces(MediaType.TEXT_HTML)
	public Viewable createRoleForm() {
		return new Viewable("/create_role");
	}

	@DenyAll
	@POST
	@Path("create")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable createRole(@FormParam("name") String name) {
		return Controller.getResponse(_rolesResource.createRole(name),
				"create_role_succeeded", "create_role_failed");
	}

	@DenyAll
	@GET
	@Path("{idRole}/edit")
	@Produces(MediaType.TEXT_HTML)
	public Viewable editRoleForm(@PathParam("idRole") int idRole) {
		Role role = _rolesResource.getRole(idRole);
		return new Viewable("/edit_role", role);
	}

	@DenyAll
	@POST
	@Path("{idRole}/edit")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable updateRole(@FormParam("idRole") int idRole,
			@FormParam("name") String name) {
		return Controller.getResponse(_rolesResource.updateRole(idRole, name),
				"update_role_succeeded", "update_role_failed");
	}
	
	@DenyAll
	@POST
	@Path("{idRole}/delete")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable deleteRole(@FormParam("idRole") int idRole) {
		return Controller.getResponse(_rolesResource.deleteRole(idRole),
				"delete_role_succeeded", "delete_role_failed");
	}
}
