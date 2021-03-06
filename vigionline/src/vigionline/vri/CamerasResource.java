package vigionline.vri;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import vigionline.common.Utils;
import vigionline.common.model.Camera;
import vigionline.common.model.Model;
import vigionline.vce.authorization.AuthorizationAuditor;
import vigionline.vce.database.DatabaseLocator;
import vigionline.vce.database.IDatabase;
import vigionline.vce.stream.iterator.DatabaseFrameIterator;
import vigionline.vce.stream.iterator.FrameIteratorFactory;
import vigionline.vce.stream.iterator.AbstractLocalFrameIterator;
import vigionline.vce.stream.virtual.StreamConsumer;
import vigionline.vce.stream.virtual.StreamHandler;

@RolesAllowed("admin")
@Path("/api/cameras")
public class CamerasResource {

	private final IDatabase _database = DatabaseLocator.Get();

	private @Context
	ServletContext _contextHandler;
	private @Context
	HttpServletRequest _request;

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Camera> getCameras(
			@DefaultValue("null") @QueryParam("username") String username,
			@DefaultValue("-1") @QueryParam("idDivision") int idDivision) {
		List<Camera> cameras = null;
		try {

			if (username == null || username.equals("null") && idDivision == -1)
				cameras = _database.getCameras();
			else {
				if (username != null && !username.isEmpty())
					cameras = _database.getCamerasByUsername(username);
				else if (idDivision != -1)
					cameras = _database.getCamerasInDivision(idDivision);
			}

		} catch (SQLException e) {
			throw new WebApplicationException(500);
		}
		if (cameras == null)
			throw new WebApplicationException(404);
		return cameras;
	}

	@GET
	@Path("indivision/{idDivision}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Camera> getCamerasInDivision(
			@DefaultValue("-1") @QueryParam("idDivision") int idDivision) {
		List<Camera> cameras = null;
		try {
			if (idDivision != -1)
				cameras = _database.getCamerasInDivision(idDivision);
		} catch (SQLException e) {
			throw new WebApplicationException(500);
		}
		if (cameras == null)
			throw new WebApplicationException(404);
		return cameras;
	}

	@GET
	@Path("notindivision/{idDivision}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Camera> getCamerasNotInDivision(
			@DefaultValue("-1") @QueryParam("idDivision") int idDivision) {
		List<Camera> cameras = null;
		try {
			if (idDivision != -1)
				cameras = _database.getCamerasNotInDivision(idDivision);
		} catch (SQLException e) {
			throw new WebApplicationException(500);
		}
		if (cameras == null)
			throw new WebApplicationException(404);
		return cameras;
	}

	@GET
	@Path("foruser/{username}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Camera> getCamerasAllowedToUser(
			@PathParam("username") String username) {
		List<Camera> cameras = null;
		try {
			if (username != null)
				cameras = _database.getCamerasByUsername(username);
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

	/*************************** Stream STUFF ***************************/
	@RolesAllowed({ "admin", "user" })
	@GET
	@Path("{idCamera}/stream")
	@Produces("multipart/x-mixed-replace;boundary=--myboundary")
	public Response getStreamFromCamera(
			@PathParam("idCamera") final int idCamera,
			@DefaultValue("-1") @QueryParam("fps") int fps) {
		String username = _request.getUserPrincipal().getName();
		AuthorizationAuditor.enforceForCamera(username, idCamera);
		try {

			Camera camera = _database.getCamera(idCamera);
			Model model = _database.getModel(camera.getIdModel());
			StreamHandler streamHandler = ((StreamHandler) _contextHandler.getAttribute("StreamHandler"));
			AbstractLocalFrameIterator iterator = FrameIteratorFactory.getDiscardingLocalStreamIterator(streamHandler, camera, model);
			StreamConsumer consumer = new StreamConsumer(iterator, fps);
			return Response.ok(consumer).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("{idCamera}/recordedstream")
	@Produces("multipart/x-mixed-replace;boundary=--myboundary")
	public Response getRecordedStream(@PathParam("idCamera") int idCamera,
			@QueryParam("day") String day, @QueryParam("hour") int hour,
			@QueryParam("min") int min,
			@DefaultValue("-1") @QueryParam("fps") int fps) {
		try {
			Date data = Utils.makeDateFromFormFields(day, hour, min);
			DatabaseFrameIterator iterator = new DatabaseFrameIterator(idCamera, data);
			StreamConsumer consumer = new StreamConsumer(iterator, fps);
			return Response.ok(consumer).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
