package vigionline.vwc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import vigionline.common.model.Camera;
import vigionline.common.model.Location;
import vigionline.vri.CamerasResource;
import vigionline.vri.LocationsResource;

import com.sun.jersey.api.view.Viewable;

@Path("/")
public class HomeController {

	@GET
	public Viewable index()
	{
		return new Viewable("/index");
	}
	
	@GET
	@Path("console")
	public Viewable getConsole()
	{
		List<Camera> cameras = new CamerasResource().getCameras();
		List<Location> locations = new LocationsResource().getLocations();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("cameras", cameras);
		data.put("locations", locations);
		return new Viewable("/console", data);
	}
	
	@PermitAll
	@GET
	@Path("logon")
	public Viewable logon()
	{
		return new Viewable("/logon");
	}
	
	@POST
	@Path("logon")
	public Viewable doLogon(@FormParam("username") String username, @FormParam("password") String password)
	{
		return new Viewable("/logon");
	}
	
	@GET
	@Path("logout")
	public Viewable doLogout()
	{
		return new Viewable("/logout");
	}
	
	@GET
	@Path("setLang")
	public Response setLang(@QueryParam("country") String country,
			@QueryParam("lang") String lang)
	{
		return Response.seeOther(UriBuilder.fromUri("/").build()).
				cookie(new NewCookie[] {new NewCookie("lang", lang),new NewCookie("country", country)}).
				build();
	}
}
