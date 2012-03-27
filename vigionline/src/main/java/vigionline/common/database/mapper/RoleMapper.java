package vigionline.common.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vigionline.common.model.Role;

public class RoleMapper extends Mapper<Role> {

	@Override
	protected Role getObject(ResultSet result) throws SQLException {
		Role role = new Role();
		role.setIdRole(result.getInt(1));
		role.setName(result.getString(2));
		return role;
	}

	@Override
	protected String getAllQuery() {
		return "SELECT idRole, name FROM Role";
	}

	@Override
	protected String getByIdQuery() {
		return "SELECT idRole, name FROM Role WHERE idRole = ?";
	}

	@Override
	protected PreparedStatement getInsertStatement(Role role, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("INSERT INTO Role (name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
		prep.setString(1, role.getName());
		return prep;
	}

	@Override
	protected PreparedStatement getUpdateStatement(Role role, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("UPDATE Role SET name = ? WHERE idRole = ?");
		prep.setString(1, role.getName());
		prep.setInt(2, role.getIdRole());
		return prep;
	}

	@Override
	protected PreparedStatement getDeleteStatement(int id, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("DELETE FROM Role WHERE idRole = ?");
		prep.setInt(1, id);
		return prep;
	}
}
