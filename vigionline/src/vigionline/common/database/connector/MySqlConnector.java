package vigionline.common.database.connector;

import java.sql.Connection;
import java.sql.SQLException;

import vigionline.common.configuration.ConfigurationManager;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public final class MySqlConnector {

	public static Connection getConnection()
	{
		/*** Using DataSource ***/
		String DATABASE_NAME = ConfigurationManager.getInstance().getString("database");
		String USER_NAME = ConfigurationManager.getInstance().getString("user");
		String PASSWORD = ConfigurationManager.getInstance().getString("password");
		
		MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
		ds.setDatabaseName(DATABASE_NAME);
		ds.setUser(USER_NAME);
		ds.setPassword(PASSWORD);
		Connection connection = null;
		try {
			connection = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**************** Using DriverManager 
		String DRIVER_NAME = "com.mysql.jdbc.Driver";
		String CONNECTION_STRING = "jdbc:mysql://localhost:" + DATABASE_PORT + "/" + DATABASE_NAME;
		int DATABASE_PORT = ConfigurationManager.getInstance().getInt("port");
		try {
			Class.forName(DRIVER_NAME).newInstance();			
			connection = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*******************************************/
		return connection;
	}
}
