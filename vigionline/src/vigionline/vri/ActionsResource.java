package vigionline.vri;

import java.io.IOException;
import java.sql.SQLException;
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

import org.apache.http.client.ClientProtocolException;

import vigionline.common.database.DatabaseLocator;
import vigionline.common.database.IDatabase;
import vigionline.common.model.Action;
import vigionline.common.model.Camera;
import vigionline.common.model.Model;
import vigionline.vce.record.RecordHandler;
import vigionline.vce.record.Recorder;
import vigionline.vce.stream.actions.HttpGetActionExecuter;
import vigionline.vce.stream.virtual.StreamHandler;

@RolesAllowed("admin")
@Path("/api/actions")
public class ActionsResource {

	private final IDatabase _database = DatabaseLocator.Get();
	private @Context ServletContext _contextHandler;
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Action> getActions(
			@DefaultValue("-1") @QueryParam("idModel") final int idModel) {
		List<Action> actions = null;
		try {
			if (idModel == -1)
				actions = _database.getActions();
			else
				actions = _database.getActionsForModel(idModel);
		} catch (SQLException e) {
			throw new WebApplicationException(500);
		}
		if (actions == null)
			throw new WebApplicationException(404);
		return actions;
	}

	@GET
	@Path("{idAction}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Action getAction(@PathParam("idAction") int idAction) {
		Action action = null;
		try {
			action = _database.getAction(idAction);
		} catch (SQLException e) {
			throw new WebApplicationException(500);
		}
		if (action == null)
			throw new WebApplicationException(404);
		return action;
	}

	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createAction(@FormParam("name") String name,
			@FormParam("action1") String action1,
			@FormParam("action2") String action2,
			@FormParam("idModel") int idModel) {
		Action action = new Action();
		action.setName(name);
		action.setAction1(action1);
		action.setAction2(action2);
		action.setIdModel(idModel);
		try {
			_database.createAction(action);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}

	@POST
	@Path("{idAction}/edit")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updateAction(int idAction, String name, String action1,
			String action2, int idModel) {
		Action action = new Action();
		action.setIdAction(idAction);
		action.setName(name);
		action.setAction1(action1);
		action.setAction2(action2);
		action.setIdModel(idModel);
		try {
			_database.updateAction(action);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}

	@POST
	@Path("{idAction}/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteAction(@FormParam("idAction") int idAction) {
		try {
			_database.deleteAction(idAction);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}

	@GET
	@Path("{idAction}/execute")
	public Response executeAction(@PathParam("idAction") int idAction,
			@QueryParam("idCamera") int idCamera) {
		Action action = null;
		Camera camera = null;
		try {
			action = _database.getAction(idAction);
			camera = _database.getCamera(idCamera);

			if (action != null && action.getIdModel() == camera.getIdModel()) {
				HttpGetActionExecuter ae = new HttpGetActionExecuter(camera, action);
				return Response.status(
						ae.execute().getStatusLine().getStatusCode()).build();
			}
		} catch (SQLException e) {
			throw new WebApplicationException(500);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new WebApplicationException(404);
	}
	
	/*************************** Recording STUFF ***************************/
	@GET
	@Path("record")
	public Response startRecord(@QueryParam("idCamera") final int idCamera) {
		try {
			final Camera camera = _database.getCamera(idCamera);
			final Model model = _database.getModel(camera.getIdModel());
			RecordHandler recordHandler = ((RecordHandler) _contextHandler.getAttribute("RecordHandler"));
			StreamHandler streamHandler = ((StreamHandler) _contextHandler.getAttribute("StreamHandler"));
			System.out.println("Submitting recorder");
			recordHandler.submitRecorder(camera, model, streamHandler);
			return Response.ok().build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("stoprecord")
	public Response stopRecord(@QueryParam("idCamera") final int idCamera) {
		try {
			final Camera camera = _database.getCamera(idCamera);
			RecordHandler recordHandler = ((RecordHandler) _contextHandler.getAttribute("RecordHandler"));
			System.out.println("Stopping recorder");
			recordHandler.stopRecorder(camera);
			return Response.ok().build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("recordstatus")
	public Response recordStatus(@QueryParam("idCamera") final int idCamera) {
		try {
			final Camera camera = _database.getCamera(idCamera);
			RecordHandler recordHandler = ((RecordHandler) _contextHandler.getAttribute("RecordHandler"));
			Recorder recorder = recordHandler.getRecorder(camera);
			boolean recordingStatus = (recorder == null ? false : recorder.stillRecording());
			return Response.ok(String.valueOf(recordingStatus)).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
