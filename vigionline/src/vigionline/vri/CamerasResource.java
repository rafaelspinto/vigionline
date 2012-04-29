package vigionline.vri;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import vigionline.common.database.DatabaseLocator;
import vigionline.common.database.IDatabase;
import vigionline.common.model.Camera;
import vigionline.common.model.Model;
import vigionline.vce.stream.CameraStreamIterator;
import vigionline.vce.stream.ConnectionManager;

import com.sun.jersey.api.core.HttpContext;

@Path("/api/cameras")
public class CamerasResource {

	private final IDatabase _database = DatabaseLocator.Get();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Camera> getCameras() {
		List<Camera> cameras = null;
		try {
			cameras = _database.getCameras();
		} catch (SQLException e) {
			throw new WebApplicationException(500);
		}
		if (cameras == null)
			throw new WebApplicationException(404);
		return cameras;
	}

	@GET
	@Path("{idCamera}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Camera getCamera(@PathParam("idCamera") int idCamera) {
		Camera camera = null;
		try {
			camera = _database.getCamera(idCamera);
		} catch (SQLException e) {
			throw new WebApplicationException(500);
		}
		if (camera == null)
			throw new WebApplicationException(404);
		return camera;
	}

	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createCamera(@FormParam("name") String name,
			@FormParam("url") String url, @FormParam("port") int port,
			@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("idLocation") int idLocation,
			@FormParam("idModel") int idModel) {
		Camera camera = new Camera();
		camera.setName(name);
		camera.setUrl(url);
		camera.setPort(port);
		camera.setUsername(username);
		camera.setPassword(password);
		camera.setIdLocation(idLocation);
		camera.setIdModel(idModel);
		try {
			_database.createCamera(camera);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}

	@POST
	@Path("{idAction}/edit")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updateCamera(@FormParam("idCamera") int idCamera,
			@FormParam("name") String name, @FormParam("url") String url,
			@FormParam("port") int port,
			@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("idLocation") int idLocation,
			@FormParam("idModel") int idModel) {
		Camera camera = new Camera();
		camera.setIdCamera(idCamera);
		camera.setName(name);
		camera.setUrl(url);
		camera.setPort(port);
		camera.setUsername(username);
		camera.setPassword(password);
		camera.setIdLocation(idLocation);
		camera.setIdModel(idModel);
		try {
			_database.updateCamera(camera);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}

	@POST
	@Path("{idCamera}/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteCamera(@FormParam("idCamera") int idCamera) {
		try {
			_database.deleteCamera(idCamera);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}

	@GET
	@Path("{idCamera}/stream")
	public Response getStreamFromCamera(final @Context HttpContext hc,
			@PathParam("idCamera") final int idCamera) {

		try {
			final Camera camera = _database.getCamera(idCamera);
			final Model model = _database.getModel(camera.getIdModel());

			/** Direct View from Source **/
			ConnectionManager conManager = new ConnectionManager(camera, model);
			CameraStreamIterator iter = new CameraStreamIterator(conManager,
					model);

			if (conManager.isUrlReady()) {
				StreamingOutput sOut = new CameraStreamingOutput(iter, hc);
				return Response.ok(sOut).build();
			}
			return Response.serverError().build();
		} catch (Exception e) {
			// TODO : Define output
			return Response.status(404).build();
		}
	}
}
