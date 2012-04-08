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

import vigionline.common.model.Camera;
import vigionline.common.model.Location;
import vigionline.common.model.Manufacturer;
import vigionline.common.model.Model;
import vigionline.vri.CamerasResource;
import vigionline.vri.LocationsResource;
import vigionline.vri.ManufacturersResource;
import vigionline.vri.ModelsResource;

import com.sun.jersey.api.view.Viewable;

@Path("/cameras")
public class CamerasController {

	private CamerasResource _camerasResource = new CamerasResource();
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable getActionsHTML()
	{
		List<Camera> cameras = _camerasResource.getCameras();
		return new Viewable("/cameras", cameras);
	}
	
	@GET
	@Path("{idCamera}")
	@Produces(MediaType.TEXT_HTML)
	public Viewable getCamera(@PathParam("idCamera") int idCamera)
	{
		Camera camera = _camerasResource.getCamera(idCamera);
		Model model = new ModelsResource().getModel(camera.getIdModel());
		Manufacturer manufacturer = new ManufacturersResource().getManufacturer(model.getIdManufacturer());
		Location location = new LocationsResource().getLocation(camera.getIdLocation());
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("camera", camera);
		data.put("location", location);
		data.put("manufacturer", manufacturer);
		data.put("model", model);
		return new Viewable("/camera", data);
	}
	
	@GET
	@Path("create")
	@Produces(MediaType.TEXT_HTML)
	public Viewable createCameraForm()
	{
		Camera camera = new Camera();
		ManufacturersResource manResource = new ManufacturersResource();
		List<Manufacturer> manufacturers = manResource.getManufacturers();
		List<Model> models = manResource.getManufacturerModels(manufacturers.get(0).getIdManufacturer());
		LocationsResource locResource = new LocationsResource(); 
		List<Location> locations = locResource.getLocations();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("camera", camera);
		data.put("locations", locations);
		data.put("manufacturers", manufacturers);
		data.put("models", models);
		return new Viewable("/create_camera", data);
	}
	
	@POST
	@Path("create")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable createCamera(
			@FormParam("name") String name,
			@FormParam("url") String url,
			@FormParam("port") int port,
			@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("idLocation") int idLocation,
			@FormParam("idModel") int idModel
	)
	{
		return Controller.getResponse(_camerasResource.createCamera(name, url, port, username, password, idLocation, idModel), "create_camera_succeeded", "create_camera_failed");
	}
	
	@GET
	@Path("{idCamera}/edit")
	@Produces(MediaType.TEXT_HTML)
	public Viewable editCameraForm(@PathParam("idCamera") int idCamera)
	{
		Camera camera = _camerasResource.getCamera(idCamera);
		ModelsResource modResource = new ModelsResource();
		Model model = modResource.getModel(camera.getIdModel());
		ManufacturersResource manResource = new ManufacturersResource();
		List<Manufacturer> manufacturers = manResource.getManufacturers();
		Manufacturer manufacturer = manResource.getManufacturer(model.getIdManufacturer());
		List<Model> models = manResource.getManufacturerModels(model.getIdManufacturer());
		LocationsResource locResource = new LocationsResource(); 
		Location location = locResource.getLocation(camera.getIdLocation());
		List<Location> locations = locResource.getLocations();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("camera", camera);
		data.put("location", location);
		data.put("locations", locations);
		data.put("manufacturer", manufacturer);
		data.put("manufacturers", manufacturers);
		data.put("model", model);
		data.put("models", models);
		return new Viewable("/edit_camera", data);
	}
	
	@POST
	@Path("{idCamera}/edit")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable updateCamera(
			@FormParam("idCamera") int idCamera,
			@FormParam("name") String name,
			@FormParam("url") String url,
			@FormParam("port") int port,
			@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("idLocation") int idLocation,
			@FormParam("idModel") int idModel
	)
	{
		return Controller.getResponse(_camerasResource.updateCamera(idCamera, name, url, port, username, password, idLocation, idModel), "update_camera_succeeded", "update_camera_failed");
	}
	
	@POST
	@Path("{idCamera}/delete")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable deleteAction(
			@FormParam("idCamera") int idCamera
	)
	{		
		return Controller.getResponse(_camerasResource.deleteCamera(idCamera), "delete_camera_succeeded", "delete_camera_failed");
	}
}
