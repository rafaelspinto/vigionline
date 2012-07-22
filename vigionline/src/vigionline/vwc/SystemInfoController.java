package vigionline.vwc;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import vigionline.vri.SystemInfoResource;

import com.sun.jersey.api.view.Viewable;

@RolesAllowed("admin")
@Path("/system")
public class SystemInfoController {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable getSystemInfo()
	{
		return new Viewable("/system_info");
	}
	
	@GET
	@Path("maintenance")
	@Produces(MediaType.TEXT_HTML)
	public Viewable getSystemMaintenance()
	{
		return new Viewable("/system_maintenance");
	}
	
	@POST
	@Path("maintenance")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Viewable postMaintenance(
			@FormParam("b_day") String b_day, 
			@FormParam("b_hour") int b_hour,
			@FormParam("b_min") int b_min,
			@FormParam("e_day") String e_day, 
			@FormParam("e_hour") int e_hour,
			@FormParam("e_min") int e_min)
	{
		return Controller.getResponse(new SystemInfoResource().deleteImages(b_day, b_hour, b_min, e_day, e_hour, e_min),
				"delete_images_succeeded", "delete_images_failed");
	}
}
