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

import com.sun.jersey.api.view.Viewable;

import vigionline.common.model.Manufacturer;
import vigionline.common.model.Model;
import vigionline.vri.ManufacturersResource;
import vigionline.vri.ModelsResource;

@RolesAllowed("admin")
@Path("/models")
public class ModelsController {

	private ModelsResource _modelsResource = new ModelsResource();
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable getModels()
	{
		List<Model> models = _modelsResource.getModels();
		return new Viewable("/models", models);
	}
	
	@GET
	@Path("{idModel}")
	@Produces(MediaType.TEXT_HTML)
	public Viewable getModel(@PathParam("idModel") int idModel)
	{
		Model model = _modelsResource.getModel(idModel);
		Manufacturer manufacturer = new ManufacturersResource().getManufacturer(model.getIdManufacturer());
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", model);
		data.put("manufacturer", manufacturer);
		return new Viewable("/model", data);
	}
	
	@GET
	@Path("create")
	@Produces(MediaType.TEXT_HTML)
	public Viewable createModelForm()
	{
		List<Manufacturer> manufacturers = new ManufacturersResource().getManufacturers();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", new Model());
		data.put("manufacturers", manufacturers);
		return new Viewable("/create_model", data);
	}
	
	@POST
	@Path("create")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable createModel(
			@FormParam("name") String name,
			@FormParam("videoUrl") String videoUrl,
			@FormParam("audioUrl") String audioUrl,
			@FormParam("idManufacturer") int idManufacturer,
			@FormParam("isMjpeg") String mjpeg,
			@FormParam("width") int width,
			@FormParam("height") int height,
			@FormParam("beginLinesToDiscard") int beginLinesToDiscard,
			@FormParam("endLinesToDiscard") int endLinesToDiscard
	)
	{
		Model model = new Model();
		model.setName(name);
		model.setVideoUrl(videoUrl);
		model.setAudioUrl(audioUrl);
		model.setIdManufacturer(idManufacturer);
		model.setWidth(width);
		model.setHeight(height);
		return Controller.getResponse(_modelsResource.createModel(model), "create_model_succeeded", "create_model_failed");
	}
	
	@GET
	@Path("{idModel}/edit")
	@Produces(MediaType.TEXT_HTML)
	public Viewable editModel(@PathParam("idModel") int idModel)
	{
		Model model = _modelsResource.getModel(idModel);
		Manufacturer manufacturer = new ManufacturersResource().getManufacturer(model.getIdManufacturer());
		List<Manufacturer> manufacturers = new ManufacturersResource().getManufacturers();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", model);
		data.put("manufacturer", manufacturer);
		data.put("manufacturers", manufacturers);
		return new Viewable("/edit_model", data);
	}
	
	@POST
	@Path("{idModel}/edit")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable updateModel(
			@FormParam("idModel") int idModel,
			@FormParam("name") String name,
			@FormParam("videoUrl") String videoUrl,
			@FormParam("audioUrl") String audioUrl,
			@FormParam("idManufacturer") int idManufacturer,
			@FormParam("isMjpeg") String mjpeg,
			@FormParam("width") int width,
			@FormParam("height") int height,
			@FormParam("beginLinesToDiscard") int beginLinesToDiscard,
			@FormParam("endLinesToDiscard") int endLinesToDiscard
	)
	{
		Model model = new Model();
		model.setIdModel(idModel);
		model.setName(name);
		model.setVideoUrl(videoUrl);
		model.setAudioUrl(audioUrl);
		model.setIdManufacturer(idManufacturer);
		model.setWidth(width);
		model.setHeight(height);
		return Controller.getResponse(_modelsResource.updateModel(model), "update_model_succeeded", "update_model_failed");
	}
	
	@POST
	@Path("{idModel}/delete")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable deleteModel(@FormParam("idModel") int idModel)
	{
		return Controller.getResponse(_modelsResource.deleteModel(idModel), "delete_model_succeeded", "delete_model_failed");
	}
}
