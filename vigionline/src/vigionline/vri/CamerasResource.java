package vigionline.vri;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
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
import vigionline.vce.record.RecordHandler;
import vigionline.vce.record.Recorder;
import vigionline.vce.stream.iterator.DatabaseStreamIterator;
import vigionline.vce.stream.iterator.LocalStreamIterator;
import vigionline.vce.stream.iterator.StreamIteratorFactory;
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
	public List<Camera> getCameras(@QueryParam("username") String username) {
		List<Camera> cameras = null;
		try {
			if (username != null && !username.isEmpty())
				cameras = _database.getCamerasByUsername(username);
			else
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

	/*************************** Stream STUFF ***************************/
	@RolesAllowed({ "admin", "user" })
	@GET
	@Path("{idCamera}/stream")
	@Produces("multipart/x-mixed-replace;boundary=--myboundary")
	public Response getStreamFromCamera(
			@PathParam("idCamera") final int idCamera) {
		try {
			final Camera camera = _database.getCamera(idCamera);
			final Model model = _database.getModel(camera.getIdModel());
			StreamHandler streamHandler = ((StreamHandler) _contextHandler
					.getAttribute("StreamHandler"));
			LocalStreamIterator iterator = StreamIteratorFactory
					.getLocalStreamIterator(streamHandler, camera, model);
			StreamConsumer consumer = new StreamConsumer(iterator);
			return Response.ok(consumer).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("{idCamera}/recordedstream")
	@Produces("multipart/x-mixed-replace;boundary=--myboundary")
	public Response getRecordedStream(
			@PathParam("idCamera") final int idCamera,
			@QueryParam("date") final String initialDate) {
		try {
			DatabaseStreamIterator iterator = new DatabaseStreamIterator(
					idCamera, Date.valueOf(initialDate));
			StreamConsumer consumer = new StreamConsumer(iterator);
			return Response.ok(consumer).build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	/*************************** Recording STUFF ***************************/
	@GET
	@Path("{idCamera}/record")
	public Response startRecord(@PathParam("idCamera") final int idCamera) {
		try {
			final Camera camera = _database.getCamera(idCamera);
			final Model model = _database.getModel(camera.getIdModel());
			RecordHandler recordHandler = ((RecordHandler) _contextHandler
					.getAttribute("RecordHandler"));
			StreamHandler streamHandler = ((StreamHandler) _contextHandler
					.getAttribute("StreamHandler"));
			recordHandler.submitRecorder(camera, model, streamHandler);
			return Response.ok().build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("{idCamera}/stoprecord")
	public Response stopRecord(@PathParam("idCamera") final int idCamera) {
		try {
			final Camera camera = _database.getCamera(idCamera);
			RecordHandler recordHandler = ((RecordHandler) _contextHandler
					.getAttribute("RecordHandler"));
			recordHandler.stopRecorder(camera);
			return Response.ok().build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("{idCamera}/recordstatus")
	public Response recordStatus(@PathParam("idCamera") final int idCamera) {
		try {
			final Camera camera = _database.getCamera(idCamera);
			RecordHandler recordHandler = ((RecordHandler) _contextHandler
					.getAttribute("RecordHandler"));
			Recorder recorder = recordHandler.getRecorder(camera);
			boolean recordingStatus = (recorder == null ? false : recorder
					.stillRecording());
			return Response.ok(String.valueOf(recordingStatus)).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
