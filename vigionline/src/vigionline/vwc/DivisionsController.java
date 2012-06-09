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

import vigionline.common.model.Camera;
import vigionline.common.model.Division;
import vigionline.vri.CamerasResource;
import vigionline.vri.DivisionsResource;

import com.sun.jersey.api.view.Viewable;

@RolesAllowed("admin")
@Path("/divisions")
public class DivisionsController {

	private DivisionsResource _divisionsResource = new DivisionsResource();

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable getDivisionsHTML() {
		List<Division> divisions = _divisionsResource.getDivisions();
		return new Viewable("/divisions", divisions);
	}

	@GET
	@Path("{idDivision}")
	@Produces(MediaType.TEXT_HTML)
	public Viewable getDivisionHTML(@PathParam("idDivision") int idDivision) {
		Division division = _divisionsResource.getDivision(idDivision);
		List<Camera> cameras = new CamerasResource().getCameras(null,idDivision);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("division", division);
		data.put("cameras", cameras);
		return new Viewable("/division", data);
	}

	@GET
	@Path("create")
	@Produces(MediaType.TEXT_HTML)
	public Viewable createDivisionForm() {
		return new Viewable("/create_division");
	}

	@POST
	@Path("create")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable createDivision(@FormParam("name") String name) {
		return Controller.getResponse(_divisionsResource.createDivision(name),
				"create_division_succeeded", "create_division_failed");
	}

	@GET
	@Path("{idDivision}/edit")
	@Produces(MediaType.TEXT_HTML)
	public Viewable editDivisionForm(@PathParam("idDivision") int idDivision) {
		Division division = _divisionsResource.getDivision(idDivision);
		List<Camera> cameras = new CamerasResource().getCameras(null,-1);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("division", division);
		data.put("cameras", cameras);
		return new Viewable("/edit_division", data);
	}

	@POST
	@Path("{idDivision}/edit")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable updateDivision(@FormParam("idDivision") int idDivision,
			@FormParam("name") String name,
			@FormParam("cameras") List<Integer> cameras) {
		
		return Controller.getResponse(
				_divisionsResource.updateDivision(idDivision, name, cameras),
				"update_division_succeeded", "update_division_failed");
	}

	@POST
	@Path("{idDivision}/delete")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable deleteDivision(@FormParam("idDivision") int idDivision) {
		return Controller.getResponse(
				_divisionsResource.deleteDivision(idDivision),
				"delete_division_succeeded", "delete_division_failed");
	}
}
