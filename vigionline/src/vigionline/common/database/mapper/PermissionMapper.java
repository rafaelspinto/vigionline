package vigionline.common.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vigionline.common.model.Permission;

public class PermissionMapper extends Mapper<Permission> {

	@Override
	protected Permission getObject(ResultSet result) throws SQLException {
		Permission perm = new Permission();
		perm.setIdPermission(result.getInt(1));
		perm.setIdPermissionType(result.getInt(2));
		perm.setIdCamera(result.getInt(3));
		return perm;
	}

	@Override
	protected String getAllQuery() {
		return "SELECT idPermission, idPermissionType, idCamera FROM Permission";
	}

	@Override
	protected String getByIdQuery() {
		return "SELECT idPermission, idPermissionType, idCamera FROM Permission WHERE idPermission";
	}

	@Override
	protected PreparedStatement getInsertStatement(Permission perm,
			Connection con) throws SQLException {
		PreparedStatement prep = con.prepareStatement("INSERT INTO Permission (idPermissionType, idCamera) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
		prep.setInt(1, perm.getIdPermissionType());
		prep.setInt(2, perm.getIdCamera());
		return prep;
	}

	@Override
	protected PreparedStatement getUpdateStatement(Permission perm,
			Connection con) throws SQLException {
		PreparedStatement prep = con.prepareStatement("UPDATE Permission SET idPermissionType = ?, idCamera = ? WHERE idPermission = ?");
		prep.setInt(1, perm.getIdPermissionType());
		prep.setInt(2, perm.getIdCamera());
		prep.setInt(2, perm.getIdPermission());
		return prep;
	}

	@Override
	protected PreparedStatement getDeleteStatement(int id, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("DELETE FROM Permission WHERE idPermission = ?");
		prep.setInt(1, id);
		return prep;
	}
}
