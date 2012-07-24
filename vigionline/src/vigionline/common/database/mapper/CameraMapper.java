package vigionline.common.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import vigionline.common.database.connector.MySqlConnector;
import vigionline.common.model.Camera;

public class CameraMapper extends Mapper<Camera> {

	@Override
	public Camera getObject(ResultSet result) throws SQLException {
		Camera camera = new Camera();
		camera.setIdCamera(result.getInt(1));
		camera.setIdLocation(result.getInt(2));
		camera.setIdModel(result.getInt(3));
		camera.setName(result.getString(4));
		camera.setUrl(result.getString(5));
		camera.setPort(result.getInt(6));
		camera.setUsername(result.getString(7));
		camera.setPassword(result.getString(8)); // TODO : Decrypt Password
		return camera;
	}

	@Override
	protected String getAllQuery() {
		return "SELECT idCamera, idLocation, idModel, name, url, port, username, password FROM Camera";
	}

	@Override
	protected String getByIdQuery() {
		return "SELECT idCamera, idLocation, idModel, name, url, port, username, password FROM Camera WHERE idCamera = ?";
	}

	@Override
	protected String getByNameQuery() {
		return "SELECT idCamera, idLocation, idModel, name, url, port, username, password FROM Camera WHERE name = ?";
	}

	@Override
	protected PreparedStatement getInsertStatement(Camera camera, Connection con)
			throws SQLException {
		PreparedStatement prep = con
				.prepareStatement(
						"INSERT INTO Camera (idLocation, idModel, name, url, port, username, password) VALUES(?, ?, ?, ?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
		prep.setInt(1, camera.getIdLocation());
		prep.setInt(2, camera.getIdModel());
		prep.setString(3, camera.getName());
		prep.setString(4, camera.getUrl());
		prep.setInt(5, camera.getPort());
		prep.setString(6, camera.getUsername());
		prep.setString(7, camera.getPassword());
		return prep;
	}

	@Override
	protected PreparedStatement getUpdateStatement(Camera camera, Connection con)
			throws SQLException {
		PreparedStatement prep = con
				.prepareStatement("UPDATE Camera SET idLocation = ?, idModel = ?, name = ?, url = ?, port = ?, username = ?, password = ? WHERE idCamera = ?");
		prep.setInt(1, camera.getIdLocation());
		prep.setInt(2, camera.getIdModel());
		prep.setString(3, camera.getName());
		prep.setString(4, camera.getUrl());
		prep.setInt(5, camera.getPort());
		prep.setString(6, camera.getUsername());
		prep.setString(7, camera.getPassword());
		prep.setInt(8, camera.getIdCamera());
		return prep;
	}

	@Override
	protected PreparedStatement getDeleteStatement(int id, Connection con)
			throws SQLException {
		PreparedStatement prep = con
				.prepareStatement("DELETE FROM Camera WHERE idCamera = ?");
		prep.setInt(1, id);
		return prep;
	}

	public List<Camera> getByUsername(String username) throws SQLException {
		String sql = "SELECT DISTINCT C.idCamera, C.idLocation, C.idModel, C.name, C.url, C.port, C.username, C.password FROM Camera C"
				+ " INNER JOIN Permission P ON P.idCamera = C.idCamera "
				+ " INNER JOIN Division D ON P.idDivision = D.idDivision"
				+ " INNER JOIN UserDivision UD ON D.idDivision = UD.idDivision "
				+ " INNER JOIN User U ON UD.idUser = U.idUser "
				+ " WHERE U.username = ?";
		Connection con = MySqlConnector.getConnection();
		PreparedStatement prep = con.prepareStatement(sql);
		prep.setString(1, username);
		return getListByPreparedStatement(prep);
	}

	public List<Camera> getByDivision(int idDivision) throws SQLException {
		String sql = "SELECT DISTINCT C.idCamera, C.idLocation, C.idModel, C.name, C.url, C.port, C.username, C.password FROM Camera C"
				+ " INNER JOIN Permission P ON P.idCamera = C.idCamera "
				+ " INNER JOIN Division D ON P.idDivision = D.idDivision"
				+ " WHERE D.idDivision = ? ";
		Connection con = MySqlConnector.getConnection();
		PreparedStatement prep = con.prepareStatement(sql);
		prep.setInt(1, idDivision);
		return getListByPreparedStatement(prep);
	}

	public List<Camera> getByNotInDivision(int idDivision) throws SQLException {
		String sql = "SELECT DISTINCT C.idCamera, C.idLocation, C.idModel, C.name, C.url, C.port, C.username, C.password FROM Camera C"
				+ " LEFT JOIN Permission P ON P.idCamera = C.idCamera "
				+ " LEFT JOIN Division D ON P.idDivision = D.idDivision"
				+ " WHERE  P.idDivision IS NULL OR P.idDivision != ?";
		Connection con = MySqlConnector.getConnection();
		PreparedStatement prep = con.prepareStatement(sql);
		prep.setInt(1, idDivision);
		return getListByPreparedStatement(prep);
	}
	
	public List<Camera> getByLocation(int idLocation) throws SQLException {
		String sql = getAllQuery() + " WHERE idLocation = ?";
		Connection con = MySqlConnector.getConnection();
		PreparedStatement prep = con.prepareStatement(sql);
		prep.setInt(1, idLocation);
		return getListByPreparedStatement(prep);
	}

	public List<Camera> getByUsernameAndDivision(String username, int idDivision)
			throws SQLException {
		String sql = "SELECT DISTINCT C.idCamera, C.idLocation, C.idModel, C.name, C.url, C.port, C.username, C.password FROM Camera C"
				+ " INNER JOIN Permission P ON P.idCamera = C.idCamera "
				+ " INNER JOIN User U ON U.username = ? "
				+ " INNER JOIN UserDivision UD ON U.idUser = UD.idUser "
				+ " INNER JOIN Division D ON UD.idDivision = D.idDivision"
				+ " WHERE D.idDivision = ?";
		Connection con = MySqlConnector.getConnection();
		PreparedStatement prep = con.prepareStatement(sql);
		prep.setString(1, username);
		prep.setInt(2, idDivision);
		return getListByPreparedStatement(prep);
	}

	public boolean isCameraAllowedToUser(int idCamera, String username) {
		String sql = "SELECT C.idCamera, C.idLocation, C.idModel, C.name, C.url, C.port, C.username, C.password FROM Camera C"
				+ " INNER JOIN Permission P ON P.idCamera = C.idCamera "
				+ " INNER JOIN Division D ON P.idDivision = D.idDivision"
				+ " INNER JOIN UserDivision UD ON D.idDivision = UD.idDivision "
				+ " INNER JOIN User U ON UD.idUser = U.iduser "
				+ " WHERE P.idCamera = ? AND U.username = ?";
		try(Connection con = MySqlConnector.getConnection();
		PreparedStatement prep = con.prepareStatement(sql);)
		{
			prep.setInt(1, idCamera);
			prep.setString(2, username);
			ResultSet rs = prep.executeQuery();
			
			boolean res = rs.next() ? true : false;
			rs.close();
			return res;
		} catch (SQLException e) {}
		return false;
	}
}
