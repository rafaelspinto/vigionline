package vigionline.vwc;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import vigionline.common.model.Camera;
import vigionline.vri.CamerasResource;

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
		return new Viewable("/console", cameras);
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
