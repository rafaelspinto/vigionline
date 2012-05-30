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

import vigionline.common.model.Manufacturer;
import vigionline.common.model.Model;
import vigionline.vri.ManufacturersResource;

import com.sun.jersey.api.view.Viewable;

@RolesAllowed("admin")
@Path("/manufacturers")
public class ManufacturersController {

	private ManufacturersResource _manufacturersResource = new ManufacturersResource();
		
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable getManufacturers()
	{
		List<Manufacturer> manufacturers = _manufacturersResource.getManufacturers();
		return new Viewable("/manufacturers", manufacturers);
	}
	
	@GET
	@Path("{idManufacturer}")
	@Produces(MediaType.TEXT_HTML)
	public Viewable getManufacturer(@PathParam("idManufacturer") int idManufacturer)
	{
		List<Model> models = _manufacturersResource.getManufacturerModels(idManufacturer); 
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("manufacturer", _manufacturersResource.getManufacturer(idManufacturer));
		data.put("models", models);
		return new Viewable("/manufacturer", data);
	}
	
	@GET
	@Path("{idManufacturer}/edit")
	@Produces(MediaType.TEXT_HTML)
	public Viewable editManufacturer(@PathParam("idManufacturer") int idManufacturer)
	{
		Manufacturer manufacturer = _manufacturersResource.getManufacturer(idManufacturer);
		return new Viewable("/edit_manufacturer", manufacturer);
	}
	
	@POST
	@Path("{idManufacturer}/edit")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable updateManufacturer(@FormParam("idManufacturer") int idManufacturer, @FormParam("name") String name)
	{
		return Controller.getResponse(_manufacturersResource.updateManufacturer(idManufacturer, name), "update_manufacturer_succeeded", "update_manufacturer_failed");
	}
	
	@GET
	@Path("create")
	@Produces(MediaType.TEXT_HTML)
	public Viewable createManufacturerForm()
	{
		return new Viewable("/create_manufacturer");
	}
	
	@POST
	@Path("create")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable createManufacturer(@FormParam("name") String name)
	{
		return Controller.getResponse(_manufacturersResource.createManufacturer(name), "create_manufacturer_succeeded", "create_manufacturer_failed");
	}
	
	@POST
	@Path("{idManufacturer}/delete")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable deleteManufacturer(@FormParam("idManufacturer") int idManufacturer)
	{
		return Controller.getResponse(_manufacturersResource.deleteManufacturer(idManufacturer), "delete_manufacturer_succeeded", "delete_manufacturer_failed");
	}
}
