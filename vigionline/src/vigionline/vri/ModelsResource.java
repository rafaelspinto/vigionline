package vigionline.vri;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import vigionline.common.model.Model;
import vigionline.vce.database.DatabaseLocator;
import vigionline.vce.database.IDatabase;

@RolesAllowed("admin")
@Path("/api/models")
public class ModelsResource {

	private final IDatabase _database = DatabaseLocator.Get();
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Model> getModels() {
		List<Model> models = null;
		try
		{
			models = _database.getModels();
		} catch (SQLException e) {
			throw new WebApplicationException(500);
		}
		if( models == null )
			throw new WebApplicationException(404);
		return models;
	}

	@GET
	@Path("{idModel}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Model getModel(@PathParam("idModel") int idModel) {
		Model model = null;
		try	{
			model = _database.getModel(idModel);
		}catch (SQLException e) {
			throw new WebApplicationException(500);
		}
		if( model == null )
			throw new WebApplicationException(404);
		return model;
	}

	@POST
	@Path("{idModel}/edit")
	public Response updateModel(Model model) {
		try {
			_database.updateModel(model);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}
	
	@POST
	@Path("{idModel}/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteModel(@FormParam("idModel")int idModel) {
		try {
			_database.deleteModel(idModel);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}
	
	@POST
	@Path("create")
	public Response createModel(Model model) {
		try {
			_database.createModel(model);
			return Response.status(200).build();
		} catch (SQLException e) {
			return Response.status(500).build();
		}
	}
}
