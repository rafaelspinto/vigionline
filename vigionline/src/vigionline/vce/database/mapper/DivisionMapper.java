package vigionline.vce.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import vigionline.common.model.Division;
import vigionline.vce.database.connector.MySqlConnector;

public class DivisionMapper extends Mapper<Division> {

	@Override
	public Division getObject(ResultSet result) throws SQLException {
		Division division = new Division();
		division.setIdDivision(result.getInt(1));
		division.setName(result.getString(2));
		return division;
	}

	@Override
	protected String getAllQuery() {
		return "SELECT DISTINCT idDivision, name FROM Division";
	}

	@Override
	protected String getByIdQuery() {
		return "SELECT DISTINCT idDivision, name FROM Division WHERE idDivision = ?";
	}

	@Override
	protected String getByNameQuery() {
		return "SELECT DISTINCT idDivision, name FROM Division WHERE name = ?";
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
				.prepareStatement("SELECT DISTINCT D.idDivision, D.name FROM Division D "
						+ "INNER JOIN UserDivision UD ON D.idDivision = UD.idDivision "
						+ "INNER JOIN User U ON UD.idUser = U.idUser "
						+ "WHERE U.idUser = ?");
		prep.setInt(1, idUser);
		return getListByPreparedStatement(prep);
	}

	public List<Division> getWhereUserIsNotIn(int idUser) throws SQLException{
		Connection con = MySqlConnector.getConnection();
		PreparedStatement prep = con
				.prepareStatement("SELECT DISTINCT D.idDivision, D.name FROM Division D "
						+ "LEFT JOIN UserDivision UD ON D.idDivision = UD.idDivision "
						+ "LEFT JOIN User U ON UD.idUser = U.idUser "
						+ "WHERE UD.idUser IS NULL OR U.idUser != ?");
		prep.setInt(1, idUser);
		return getListByPreparedStatement(prep);
	}

	//TODO: Validate
	public boolean isDivisionAllowedToUser(int idDivision, String username) {
		try(Connection con = MySqlConnector.getConnection();
				PreparedStatement prep = con
						.prepareStatement("SELECT DISTINCT D.idDivision, D.name FROM Division D "
								+ "INNER JOIN UserDivision UD ON D.idDivision = UD.idDivision "
								+ "INNER JOIN User U ON UD.idUser = U.idUser "
								+ "WHERE UD.idUser IS NULL OR U.idUser != ?"))
		{
			prep.setInt(1, idDivision);
			prep.setString(2, username);
			ResultSet rs = prep.executeQuery();
			boolean isAuthorized = rs.next() ? true : false;
			rs.close();
			return isAuthorized;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
