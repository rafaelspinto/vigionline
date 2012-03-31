package vigionline.common.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vigionline.common.model.RolePermission;

public class RolePermissionMapper extends Mapper<RolePermission> {

	@Override
	protected RolePermission getObject(ResultSet result) throws SQLException {
		RolePermission rolePermission = new RolePermission();
		rolePermission.setIdRolePermission(result.getInt(1));
		rolePermission.setIdRole(result.getInt(2));
		rolePermission.setIdPermission(result.getInt(3));
		return rolePermission;
	}

	@Override
	protected String getAllQuery() {
		return "SELECT idRolePermission, idRole, idPermission FROM RolePermission";
	}

	@Override
	protected String getByIdQuery() {
		return "SELECT idRolePermission, idRole, idPermission FROM RolePermission WHERE idRolePermission = ?";
	}

	@Override
	protected PreparedStatement getInsertStatement(RolePermission rolePermission,
			Connection con) throws SQLException {
		PreparedStatement prep = con.prepareStatement("INSERT INTO RolePermission (idRole, idPermission) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
		prep.setInt(1, rolePermission.getIdRole());
		prep.setInt(2, rolePermission.getIdPermission());
		return prep;
	}

	@Override
	protected PreparedStatement getUpdateStatement(RolePermission rolePermission,
			Connection con) throws SQLException {
		PreparedStatement prep = con.prepareStatement("UPDATE RolePermission SET idRole = ?, idPermission = ? WHERE idRolePermission = ?", Statement.RETURN_GENERATED_KEYS);
		prep.setInt(1, rolePermission.getIdRole());
		prep.setInt(2, rolePermission.getIdPermission());
		prep.setInt(3, rolePermission.getIdRolePermission());
		return prep;
	}

	@Override
	protected PreparedStatement getDeleteStatement(int id, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("DELETE FROM RolePermission WHERE idRolePermission = ?");
		prep.setInt(1, id);
		return prep;
	}
}
