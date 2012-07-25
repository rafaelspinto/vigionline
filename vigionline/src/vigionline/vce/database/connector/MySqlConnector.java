package vigionline.vce.database.connector;

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
		ds.setAutoReconnect(true);
		ds.setAutoReconnectForPools(true);
		ds.setEncoding("UTF-8");
		Connection connection = null;
		try {
			connection = ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return connection;
	}
}
