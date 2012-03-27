package vigionline.common.database;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import vigionline.common.database.mapper.CameraMapper;
import vigionline.common.database.mapper.LocationMapper;
import vigionline.common.database.mapper.ManufacturerMapper;
import vigionline.common.database.mapper.ModelMapper;
import vigionline.common.database.mapper.PermissionTypeMapper;
import vigionline.common.database.mapper.RoleMapper;
import vigionline.common.database.mapper.UserMapper;
import vigionline.common.model.Camera;
import vigionline.common.model.Location;
import vigionline.common.model.Manufacturer;
import vigionline.common.model.Model;
import vigionline.common.model.PermissionType;
import vigionline.common.model.Role;
import vigionline.common.model.User;

public class TestDatabaseAccess {

	@Test
	public void testUserCRUD() throws SQLException
	{
		UserMapper uMapper = new UserMapper();
		User user = new User();
		user.setName("Rafael Pinto");
		user.setUsername("rpinto"+System.currentTimeMillis());
		user.setPassword("teste");
		
		// CREATE
		int uid = uMapper.insert(user);
		user.setIdUser(uid);
		Assert.assertTrue( uid != 0);
				
		// READ
		User dbUser = uMapper.getById(user.getIdUser());
		Assert.assertEquals(user.getUsername(), dbUser.getUsername());
		
		// UPDATE
		dbUser.setName("Teste");
		uMapper.update(dbUser);
		User dbUser2 = uMapper.getById(dbUser.getIdUser());
		Assert.assertEquals("Teste", dbUser2.getName());
		
		//DELETE
		Assert.assertTrue(uMapper.delete(user.getIdUser()) != 0 );	
	}
	
	@Test
	public void testRoleCRUD() throws SQLException
	{
		RoleMapper rMapper = new RoleMapper();
		Role role = new Role();
		role.setName("Role"+System.currentTimeMillis());
			
		// CREATE
		int uid = rMapper.insert(role);
		role.setIdRole(uid);
		Assert.assertTrue( uid != 0);
				
		// READ
		Role dbRole = rMapper.getById(role.getIdRole());
		Assert.assertEquals(role.getName(), dbRole.getName());
		
		// UPDATE
		dbRole.setName("Teste");
		rMapper.update(dbRole);
		Role dbRole2 = rMapper.getById(dbRole.getIdRole());
		Assert.assertEquals("Teste", dbRole2.getName());
		
		//DELETE
		Assert.assertTrue(rMapper.delete(role.getIdRole()) != 0 );	
	}
	
	@Test
	public void testPermissionTypeCRUD() throws SQLException
	{
		PermissionTypeMapper ptMapper = new PermissionTypeMapper();
		PermissionType permType = new PermissionType();
		permType.setType("PermissionType"+System.currentTimeMillis());
			
		// CREATE
		int uid = ptMapper.insert(permType);
		permType.setIdPermissionType(uid);
		Assert.assertTrue( uid != 0);
				
		// READ
		PermissionType dbPermType = ptMapper.getById(permType.getIdPermissionType());
		Assert.assertEquals(permType.getType(), dbPermType.getType());
		
		// UPDATE
		dbPermType.setType("Teste");
		ptMapper.update(dbPermType);
		PermissionType dbPermType2 = ptMapper.getById(dbPermType.getIdPermissionType());
		Assert.assertEquals("Teste", dbPermType2.getType());
		
		//DELETE
		Assert.assertTrue(ptMapper.delete(permType.getIdPermissionType()) != 0 );	
	}
	
	@Test
	public void testLocationCRUD() throws SQLException
	{
		LocationMapper lMapper = new LocationMapper();
		Location location = new Location();
		location.setName("Location"+System.currentTimeMillis());
			
		// CREATE
		int uid = lMapper.insert(location);
		location.setIdLocation(uid);
		Assert.assertTrue( uid != 0);
				
		// READ
		Location dbLocation = lMapper.getById(location.getIdLocation());
		Assert.assertEquals(location.getName(), dbLocation.getName());
		
		// UPDATE
		dbLocation.setName("Teste");
		lMapper.update(dbLocation);
		Location dbLocation2 = lMapper.getById(dbLocation.getIdLocation());
		Assert.assertEquals("Teste", dbLocation2.getName());
		
		//DELETE
		Assert.assertTrue(lMapper.delete(location.getIdLocation()) != 0 );	
	}
	
	@Test
	public void testCameraCRUD() throws SQLException
	{
		// Location
		Location loc = new Location();
		loc.setName("TestLoc");
		LocationMapper lMapper = new LocationMapper();
		int locId = lMapper.insert(loc);
		
		// Manufacturer
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName("TestManufacturer"+System.currentTimeMillis());
		
		ManufacturerMapper manMapper = new ManufacturerMapper();
		int manUid = manMapper.insert(manufacturer);
		
		// Model
		Model model = new Model();
		model.setName("TestModel");
		model.setIdManufacturer(manUid);
		model.setVideoUrl("video");
		
		ModelMapper modMapper = new ModelMapper();
		int modUid = modMapper.insert(model);
		
		// Camera
		
		CameraMapper cMapper = new CameraMapper();
		Camera camera = new Camera();
		camera.setName("Camera"+System.currentTimeMillis());
		camera.setUrl("http://localhost");
		camera.setIdLocation(locId);
		camera.setIdModel(modUid);
		
		// CREATE
		int uid = cMapper.insert(camera);
		camera.setIdCamera(uid);
		Assert.assertTrue( uid != 0);
				
		// READ
		Camera dbCamera = cMapper.getById(camera.getIdCamera());
		Assert.assertEquals(camera.getName(), dbCamera.getName());
		
		// UPDATE
		dbCamera.setName("Teste");
		cMapper.update(dbCamera);
		Camera dbCamera2 = cMapper.getById(dbCamera.getIdCamera());
		Assert.assertEquals("Teste", dbCamera2.getName());
		
		//DELETE
		Assert.assertTrue(cMapper.delete(camera.getIdCamera()) != 0 );
		
		// CLEANUP
		lMapper.delete(locId);
		modMapper.delete(modUid);
		manMapper.delete(manUid);
	}
}
