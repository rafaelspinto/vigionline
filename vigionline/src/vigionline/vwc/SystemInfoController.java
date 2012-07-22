package vigionline.vwc;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}
