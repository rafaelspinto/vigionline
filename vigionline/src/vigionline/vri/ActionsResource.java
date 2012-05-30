package vigionline.vri;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.client.ClientProtocolException;

import vigionline.common.database.DatabaseLocator;
import vigionline.common.database.IDatabase;
import vigionline.common.model.Action;
import vigionline.common.model.Camera;
import vigionline.vce.ActionExecuter;

@Path("/api/actions")
public class ActionsResource {

	private final IDatabase _database = DatabaseLocator.Get();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Action> getActions() {
		List<Action> actions = null;
		try {
			actions = _database.getActions();
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
				ActionExecuter ae = new ActionExecuter(camera, action);
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
}
