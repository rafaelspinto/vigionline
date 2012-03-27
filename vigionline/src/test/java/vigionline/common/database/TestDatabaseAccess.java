package vigionline.common.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import vigionline.common.database.connector.MySqlConnector;

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
}
