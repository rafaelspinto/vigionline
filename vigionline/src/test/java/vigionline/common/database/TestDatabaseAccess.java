package vigionline.common.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.Test;

import vigionline.common.database.connector.MySqlConnector;
import vigionline.common.database.mapper.UserMapper;
import vigionline.common.model.User;

public class TestDatabaseAccess {

	@Test
	public void testDatabaseAccess()
	{
		Connection con = MySqlConnector.getConnection();
		Statement prep;
		try {
			prep = con.createStatement();
			ResultSet rs = prep.executeQuery("show tables");
			while(rs.next())
			{
				System.out.println(rs.getString(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
}
