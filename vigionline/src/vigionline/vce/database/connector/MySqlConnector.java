package vigionline.vce.database.connector;

import java.sql.Connection;
import java.sql.SQLException;

import vigionline.common.configuration.ConfigurationManager;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public final class MySqlConnector {

	private static MysqlConnectionPoolDataSource _datasource;

	public synchronized static Connection getConnection() {
		if (_datasource == null) {
			/*** Using DataSource ***/
			String DATABASE_NAME = ConfigurationManager.getInstance().getString("database");
			String USER_NAME = ConfigurationManager.getInstance().getString("user");
			String PASSWORD = ConfigurationManager.getInstance().getString("password");
			_datasource = new MysqlConnectionPoolDataSource();
			_datasource.setDatabaseName(DATABASE_NAME);
			_datasource.setUser(USER_NAME);
			_datasource.setPassword(PASSWORD);
			_datasource.setAutoReconnect(true);
			_datasource.setAutoReconnectForPools(true);
			_datasource.setEncoding("UTF-8");
		}
		try {
			return _datasource.getConnection();
		} catch (SQLException e) {	throw new RuntimeException(e);}
	}
}
