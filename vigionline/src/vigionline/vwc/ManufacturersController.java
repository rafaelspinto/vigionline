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

import vigionline.common.model.Manufacturer;
import vigionline.vri.ManufacturersResource;

import com.sun.jersey.api.view.Viewable;

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
		Manufacturer manufacturer = _manufacturersResource.getManufacturer(idManufacturer);
		return new Viewable("/manufacturer", manufacturer);
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
		return Controller.getResponse(_manufacturersResource.updateManufacturer(idManufacturer, name), "Manufacturer Updated Successfully", "Manufacturer Update Failed");
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
		return Controller.getResponse(_manufacturersResource.createManufacturer(name), "Manufacturer Created Successfully", "Manufacturer Create Failed");
	}
	
	@POST
	@Path("{idManufacturer}/delete")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable deleteManufacturer(@FormParam("idManufacturer") int idManufacturer)
	{
		return Controller.getResponse(_manufacturersResource.deleteManufacturer(idManufacturer), "Manufacturer Updated Successfully", "Manufacturer Update Failed");
	}
}
