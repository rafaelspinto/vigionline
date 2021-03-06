package vigionline.vce.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vigionline.common.model.UserDivision;
import vigionline.vce.database.connector.MySqlConnector;

public class UserDivisionMapper extends Mapper<UserDivision> {

	@Override
	public UserDivision getObject(ResultSet result) throws SQLException {
		UserDivision userDivision = new UserDivision();
		userDivision.setIdUserDivision(result.getInt(1));
		userDivision.setIdUser(result.getInt(2));
		userDivision.setIdDivision(result.getInt(3));
		return userDivision;
	}

	@Override
	protected String getAllQuery() {
		return "SELECT DISTINCT idUserDivsion, idUser, idDivision FROM UserDivision";
	}

	@Override
	protected String getByIdQuery() {
		return "SELECT DISTINCT idUserDivsion, idUser, idDivision FROM UserDivision WHERE idUserDivision = ?";
	}

	@Override
	protected String getByNameQuery() {
		return null;
	}

	@Override
	protected PreparedStatement getInsertStatement(UserDivision userDivision,
			Connection con) throws SQLException {
		PreparedStatement prep = con.prepareStatement(
				"INSERT INTO UserDivision (idUser, idDivision) VALUES(?,?)",
				Statement.RETURN_GENERATED_KEYS);
		prep.setInt(1, userDivision.getIdUser());
		prep.setInt(2, userDivision.getIdDivision());
		return prep;
	}

	@Override
	protected PreparedStatement getUpdateStatement(UserDivision userDivision,
			Connection con) throws SQLException {
		PreparedStatement prep = con
				.prepareStatement("UPDATE UserDivision SET idUser = ?, idDivision = ? WHERE idUserDivision = ?");
		prep.setInt(1, userDivision.getIdUser());
		prep.setInt(2, userDivision.getIdDivision());
		prep.setInt(3, userDivision.getIdUserDivision());
		return prep;
	}

	@Override
	protected PreparedStatement getDeleteStatement(int id, Connection con)
			throws SQLException {
		PreparedStatement prep = con
				.prepareStatement("DELETE FROM UserDivision WHERE idUserDivision = ?");
		prep.setInt(1, id);
		return prep;
	}

	public int deleteByUser(int idUser) throws SQLException {
		try (Connection con = MySqlConnector.getConnection()) {
			PreparedStatement prep = con
					.prepareStatement("DELETE FROM UserDivision WHERE idUser = ?");
			prep.setInt(1, idUser);
			return prep.executeUpdate();
		}
	}
}
