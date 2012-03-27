package vigionline.common.database.connector;

import java.sql.Connection;
import java.sql.DriverManager;

import vigionline.common.configuration.ConfigurationManager;

public final class MySqlConnector {

	public static Connection getConnection()
	{
		String DATABASE_NAME = ConfigurationManager.getInstance().getString("database");
		int DATABASE_PORT = ConfigurationManager.getInstance().getInt("port");
		String DRIVER_NAME = "com.mysql.jdbc.Driver";
		String CONNECTION_STRING = "jdbc:mysql://localhost:" + DATABASE_PORT + "/" + DATABASE_NAME;
		String USER_NAME = ConfigurationManager.getInstance().getString("user");
		String PASSWORD = ConfigurationManager.getInstance().getString("password");
		
		Connection connection = null;
		
		// TODO : Clear Exceptions
		try {
			Class.forName(DRIVER_NAME).newInstance();			
			connection = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
