package vigionline.vce.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vigionline.common.model.Permission;
import vigionline.vce.database.connector.MySqlConnector;

public class PermissionMapper extends Mapper<Permission> {

	@Override
	public Permission getObject(ResultSet result) throws SQLException {
		Permission perm = new Permission();
		perm.setIdPermission(result.getInt(1));
		perm.setIdCamera(result.getInt(2));
		perm.setIdDivision(result.getInt(3));
		return perm;
	}

	@Override
	protected String getAllQuery() {
		return "SELECT DISTINCT idPermission, idCamera, idDivision FROM Permission";
	}

	@Override
	protected String getByIdQuery() {
		return "SELECT DISTINCT idPermission, idCamera, idDivision FROM Permission WHERE idPermission";
	}

	@Override
	protected String getByNameQuery() {
		return null;
	}

	@Override
	protected PreparedStatement getInsertStatement(Permission perm,
			Connection con) throws SQLException {
		PreparedStatement prep = con.prepareStatement(
				"INSERT INTO Permission (idCamera, idDivision) VALUES(?,?)",
				Statement.RETURN_GENERATED_KEYS);
		prep.setInt(1, perm.getIdCamera());
		prep.setInt(2, perm.getIdDivision());
		return prep;
	}

	@Override
	protected PreparedStatement getUpdateStatement(Permission perm,
			Connection con) throws SQLException {
		PreparedStatement prep = con
				.prepareStatement("UPDATE Permission SET idCamera = ?, idDivision = ? WHERE idPermission = ?");
		prep.setInt(1, perm.getIdCamera());
		prep.setInt(2, perm.getIdDivision());
		prep.setInt(3, perm.getIdPermission());
		return prep;
	}

	@Override
	protected PreparedStatement getDeleteStatement(int id, Connection con)
			throws SQLException {
		PreparedStatement prep = con
				.prepareStatement("DELETE FROM Permission WHERE idPermission = ?");
		prep.setInt(1, id);
		return prep;
	}

	public int deleteByDivision(int idDivision) throws SQLException {
		try (Connection con = MySqlConnector.getConnection()) {
			PreparedStatement prep = con
					.prepareStatement("DELETE FROM Permission WHERE idDivision = ?");
			prep.setInt(1, idDivision);
			return prep.executeUpdate();
		}
	}
}
