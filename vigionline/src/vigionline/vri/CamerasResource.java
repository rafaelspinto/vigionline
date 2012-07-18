package vigionline.vri;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletContext;
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

import vigionline.common.database.DatabaseLocator;
import vigionline.common.database.IDatabase;
import vigionline.common.model.Camera;
import vigionline.common.model.Model;
import vigionline.vce.stream.iterator.DatabaseFrameIterator;
import vigionline.vce.stream.iterator.LocalFrameIterator;
import vigionline.vce.stream.iterator.FrameIteratorFactory;
import vigionline.vce.stream.virtual.StreamConsumer;
import vigionline.vce.stream.virtual.StreamHandler;

@RolesAllowed("admin")
@Path("/api/cameras")
public class CamerasResource {

	private final IDatabase _database = DatabaseLocator.Get();

	private @Context
	ServletContext _contextHandler;

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
	public List<Camera> getCamerasInDivision(@DefaultValue("-1") @QueryParam("idDivision") int idDivision) {
		List<Camera> cameras = null;
		try {
			if(idDivision != -1)
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
	public List<Camera> getCamerasNotInDivision(@DefaultValue("-1") @QueryParam("idDivision") int idDivision) {
		List<Camera> cameras = null;
		try {
			if(idDivision != -1)
				cameras = _database.getCamerasNotInDivision(idDivision);
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
		try {
			final Camera camera = _database.getCamera(idCamera);
			final Model model = _database.getModel(camera.getIdModel());
			StreamHandler streamHandler = ((StreamHandler) _contextHandler
					.getAttribute("StreamHandler"));
			LocalFrameIterator iterator = FrameIteratorFactory
					.getLocalStreamIterator(streamHandler, camera, model);
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
			SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
			Date d = new java.sql.Date(parser.parse(day).getTime());
			Calendar date = Calendar.getInstance();
			date.setTime(d);
			date.add(Calendar.HOUR_OF_DAY, hour);
			date.add(Calendar.MINUTE, min);

			Date data = new java.sql.Date(date.getTimeInMillis());
			DatabaseFrameIterator iterator = new DatabaseFrameIterator(idCamera, data);

			StreamConsumer consumer = new StreamConsumer(iterator, fps);
			return Response.ok(consumer).build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}
}
