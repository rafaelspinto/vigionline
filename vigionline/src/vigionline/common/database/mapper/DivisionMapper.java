package vigionline.common.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import vigionline.common.database.connector.MySqlConnector;
import vigionline.common.model.Division;

public class DivisionMapper extends Mapper<Division> {

	@Override
	protected Division getObject(ResultSet result) throws SQLException {
		Division division = new Division();
		division.setIdDivision(result.getInt(1));
		division.setName(result.getString(2));
		return division;
	}

	@Override
	protected String getAllQuery() {
		return "SELECT idDivision, name FROM Division";
	}

	@Override
	protected String getByIdQuery() {
		return "SELECT idDivision, name FROM Division WHERE idDivision = ?";
	}

	@Override
	protected String getByNameQuery() {
		return "SELECT idDivision, name FROM Division WHERE name = ?";
	}

	@Override
	protected PreparedStatement getInsertStatement(Division division,
			Connection con) throws SQLException {
		PreparedStatement prep = con.prepareStatement(
				"INSERT INTO Division (name) VALUES(?)",
				Statement.RETURN_GENERATED_KEYS);
		prep.setString(1, division.getName());
		return prep;
	}

	@Override
	protected PreparedStatement getUpdateStatement(Division division,
			Connection con) throws SQLException {
		PreparedStatement prep = con
				.prepareStatement("UPDATE Division SET name = ? WHERE idDivision = ?");
		prep.setString(1, division.getName());
		prep.setInt(2, division.getIdDivision());
		return prep;
	}

	@Override
	protected PreparedStatement getDeleteStatement(int id, Connection con)
			throws SQLException {
		PreparedStatement prep = con
				.prepareStatement("DELETE FROM Division WHERE idDivision = ?");
		prep.setInt(1, id);
		return prep;
	}

	public List<Division> getByUserId(int idUser) throws SQLException {
		Connection con = MySqlConnector.getConnection();
		PreparedStatement prep = con
				.prepareStatement("SELECT D.idDivision, D.name FROM Division D "
						+ "INNER JOIN UserDivision UD ON D.idDivision = UD.idDivision "
						+ "INNER JOIN User U ON UD.idUser = U.idUser "
						+ "WHERE U.idUser = ?");
		prep.setInt(1, idUser);
		return getListByPreparedStatement(prep);
	}
}
