package vigionline.vri;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import vigionline.common.Utils;
import vigionline.common.configuration.ConfigurationManager;
import vigionline.common.database.mapper.ImageMapper;
import vigionline.common.model.Image;
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
	
	@GET
	@Path("image_count")
	public Response getImageCount()
	{
		int count = -1;
		try {
			count = new ImageMapper().getImageCount();
		} catch (SQLException e) {
			throw new WebApplicationException(500);
		}
		return Response.ok(String.valueOf(count)).build();
	}
	
	@POST
	@Path("delete_images")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteImages(
			@FormParam("b_day") String b_day, 
			@FormParam("b_hour") int b_hour,
			@FormParam("b_min") int b_min,
			@FormParam("e_day") String e_day, 
			@FormParam("e_hour") int e_hour,
			@FormParam("e_min") int e_min
	)
	{		
		try {
			Date bData = Utils.makeDateFromFormFields(b_day, b_hour, b_min);
			Date eData = Utils.makeDateFromFormFields(e_day, e_hour, e_min);
			
			ImageMapper iMapper = new ImageMapper();
			ResultSet rs = iMapper.getImagesFromUntil(bData, eData);
			while(rs.next())
			{
				Image im = iMapper.getObject(rs);
				if( iMapper.delete(im.getIdImage())> 0)
					Utils.deleteFile(im.getFilename());
			}
			rs.close();
		} catch (ParseException | SQLException e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
		return Response.ok().build();
	}
}
