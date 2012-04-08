package vigionline.vwc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import vigionline.common.model.Action;
import vigionline.common.model.Manufacturer;
import vigionline.common.model.Model;
import vigionline.vri.ActionsResource;
import vigionline.vri.ManufacturersResource;
import vigionline.vri.ModelsResource;

import com.sun.jersey.api.view.Viewable;

@Path("/actions")
public class ActionsController {

	private ActionsResource _actionsResource = new ActionsResource();
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable getActionsHTML()
	{
		List<Action> actions = _actionsResource.getActions();
		return new Viewable("/actions", actions);
	}
	
	@GET
	@Path("create")
	@Produces(MediaType.TEXT_HTML)
	public Viewable createActionForm()
	{
		ManufacturersResource manResource = new ManufacturersResource(); 
		List<Manufacturer> manufacturers = manResource.getManufacturers();
		List<Model> models = manResource.getManufacturerModels(manufacturers.get(0).getIdManufacturer());
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("manufacturers", manufacturers);
		data.put("models", models);
		return new Viewable("/create_action", data);
	}
	
	@POST
	@Path("create")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable createAction(
			@FormParam("name") String name,
			@FormParam("action1") String action1,
			@FormParam("action2") String action2,
			@FormParam("idModel") int idModel
	)
	{		
		return Controller.getResponse(_actionsResource.createAction(name, action1, action2, idModel), "create_action_succeeded", "create_action_failed");
	}
	
	@GET
	@Path("{idAction}")
	@Produces(MediaType.TEXT_HTML)
	public Viewable getAction(@PathParam("idAction") int idAction)
	{
		Action action = _actionsResource.getAction(idAction);
		Model model = new ModelsResource().getModel(action.getIdModel());
		Manufacturer manufacturer = new ManufacturersResource().getManufacturer(model.getIdManufacturer());
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("action", action);
		data.put("manufacturer", manufacturer);
		data.put("model", model);
		return new Viewable("/action", data);
	}
	
	@GET
	@Path("{idAction}/edit")
	@Produces(MediaType.TEXT_HTML)
	public Viewable editActionForm(@PathParam("idAction") int idAction)
	{
		Action action = _actionsResource.getAction(idAction);
		ModelsResource modResource = new ModelsResource();
		Model model = modResource.getModel(action.getIdModel());
		ManufacturersResource manResource = new ManufacturersResource();
		List<Manufacturer> manufacturers = manResource.getManufacturers();
		Manufacturer manufacturer = manResource.getManufacturer(model.getIdManufacturer());
		List<Model> models = manResource.getManufacturerModels(model.getIdManufacturer());
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("action", action);
		data.put("manufacturer", manufacturer);
		data.put("manufacturers", manufacturers);
		data.put("model", model);
		data.put("models", models);
		return new Viewable("/edit_action", data);
	}
	
	@POST
	@Path("{idAction}/edit")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable updateAction(
			@FormParam("idAction") int idAction,
			@FormParam("name") String name,
			@FormParam("action1") String action1,
			@FormParam("action2") String action2,
			@FormParam("idModel") int idModel
	)
	{		
		return Controller.getResponse(_actionsResource.updateAction(idAction, name, action1, action2, idModel), "update_action_succeeded", "update_action_failed");
	}
	
	@POST
	@Path("{idAction}/delete")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable deleteAction(
			@FormParam("idAction") int idAction
	)
	{		
		return Controller.getResponse(_actionsResource.deleteAction(idAction), "delete_action_succeeded", "delete_action_failed");
	}
}
