package vigionline.common.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vigionline.common.model.PermissionType;

public class PermissionTypeMapper extends Mapper<PermissionType> {

	@Override
	protected PermissionType getObject(ResultSet result) throws SQLException {
		PermissionType permType = new PermissionType();
		permType.setIdPermissionType(result.getInt(1));
		permType.setType(result.getString(2));
		return permType;
	}

	@Override
	protected String getAllQuery() {
		return "SELECT idPermissionType, type FROM PermissionType";
	}

	@Override
	protected String getByIdQuery() {
		return "SELECT idPermissionType, type FROM PermissionType WHERE idPermissionType = ?";
	}

	@Override
	protected PreparedStatement getInsertStatement(PermissionType permType,
			Connection con) throws SQLException {
		PreparedStatement prep = con.prepareStatement("INSERT INTO PermissionType (type) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
		prep.setString(1, permType.getType());
		return prep;
	}

	@Override
	protected PreparedStatement getUpdateStatement(PermissionType permType,
			Connection con) throws SQLException {
		PreparedStatement prep = con.prepareStatement("UPDATE PermissionType SET type = ? WHERE idPermissionType = ?");
		prep.setString(1, permType.getType());
		prep.setInt(2, permType.getIdPermissionType());
		return prep;
	}

	@Override
	protected PreparedStatement getDeleteStatement(int id, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("DELETE FROM PermissionType WHERE idPermissionType = ?");
		prep.setInt(1, id);
		return prep;
	}
}
