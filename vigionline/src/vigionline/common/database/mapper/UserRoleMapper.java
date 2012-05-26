package vigionline.common.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vigionline.common.database.connector.MySqlConnector;
import vigionline.common.model.UserRole;

public class UserRoleMapper extends Mapper<UserRole> {

	@Override
	protected UserRole getObject(ResultSet result) throws SQLException {
		UserRole userRole = new UserRole();
		userRole.setIdUserRole(result.getInt(1));
		userRole.setUsername(result.getString(2));
		userRole.setRolename(result.getString(3));
		return userRole;
	}

	@Override
	protected String getAllQuery() {
		return "SELECT idUserRole, username, rolename FROM UserRole";
	}

	@Override
	protected String getByIdQuery() {
		return "SELECT idUserRole, username, rolename FROM UserRole WHERE idUserRole = ?";
	}

	@Override
	protected String getByNameQuery() {
		return null;
	}

	@Override
	protected PreparedStatement getInsertStatement(UserRole userRole,
			Connection con) throws SQLException {
		PreparedStatement prep = con.prepareStatement(
				"INSERT INTO UserRole (username, rolename) VALUES(?,?)",
				Statement.RETURN_GENERATED_KEYS);
		prep.setString(1, userRole.getUsername());
		prep.setString(2, userRole.getRolename());
		return prep;
	}

	@Override
	protected PreparedStatement getUpdateStatement(UserRole userRole,
			Connection con) throws SQLException {
		PreparedStatement prep = con
				.prepareStatement("UPDATE UserRole SET username = ?, rolename = ? WHERE idUserRole = ?");
		prep.setString(1, userRole.getUsername());
		prep.setString(2, userRole.getRolename());
		prep.setInt(3, userRole.getIdUserRole());
		return prep;
	}

	@Override
	protected PreparedStatement getDeleteStatement(int id, Connection con)
			throws SQLException {
		PreparedStatement prep = con
				.prepareStatement("DELETE FROM UserRole WHERE idUserRole = ?");
		prep.setInt(1, id);
		return prep;
	}

	public boolean isUserInRole(String username, String rolename) {
		try (Connection con = MySqlConnector.getConnection()) {
			PreparedStatement prep = con
					.prepareStatement("SELECT username, rolename FROM UserRole WHERE username = ? AND rolename = ?");
			prep.setString(1, username);
			prep.setString(2, rolename);
			return prep.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
