package vigionline.common.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vigionline.common.model.UserRole;

public class UserRoleMapper extends Mapper<UserRole> {

	@Override
	protected UserRole getObject(ResultSet result) throws SQLException {
		UserRole userRole = new UserRole();
		userRole.setIdUserRole(result.getInt(1));
		userRole.setIdUser(result.getInt(2));
		userRole.setIdRole(result.getInt(3));
		return userRole;
	}

	@Override
	protected String getAllQuery() {
		return "SELECT idUserRole, idUser, idRole FROM UserRole";
	}

	@Override
	protected String getByIdQuery() {
		return "SELECT idUserRole, idUser, idRole FROM UserRole WHERE idUserRole = ?";
	}

	@Override
	protected PreparedStatement getInsertStatement(UserRole userRole, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("INSERT INTO UserRole (idUser, idRole) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
		prep.setInt(1, userRole.getIdUser());
		prep.setInt(2, userRole.getIdRole());
		return prep;
	}

	@Override
	protected PreparedStatement getUpdateStatement(UserRole userRole, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("UPDATE UserRole SET idUser = ?, idRole = ? WHERE idUserRole = ?");
		prep.setInt(1, userRole.getIdUser());
		prep.setInt(2, userRole.getIdRole());
		prep.setInt(3, userRole.getIdUserRole());
		return prep;
	}

	@Override
	protected PreparedStatement getDeleteStatement(int id, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("DELETE FROM UserRole WHERE idUserRole = ?");
		prep.setInt(1, id);
		return prep;
	}
}
