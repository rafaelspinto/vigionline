package vigionline.vri;

import java.text.DecimalFormat;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import vigionline.common.configuration.ConfigurationManager;
import vigionline.vce.server.SystemInfo;

@RolesAllowed("admin")
@Path("/api/system")
public class SystemInfoResource {

	private SystemInfo _systemInfo = new SystemInfo();
	private DecimalFormat df = new DecimalFormat( "#########0.00");
	@GET
	@Path("space_available")
	public Response getSpaceAvailable()
	{
		double space = _systemInfo.getFreeSpaceInGB();
		return Response.ok(df.format(space)).build();
	}
	
	@GET
	@Path("total_space")
	public Response getSpaceUsed()
	{
		double space = _systemInfo.getTotalSpaceInGB();
		return Response.ok(df.format(space)).build();
	}
	
	@GET
	@Path("image_dir")
	public Response getImageDir()
	{
		String imageDir = ConfigurationManager.getInstance().getImageDirectory();
		return Response.ok(imageDir).build();
	}
}
