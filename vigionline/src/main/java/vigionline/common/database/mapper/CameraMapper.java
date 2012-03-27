package vigionline.common.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vigionline.common.model.Camera;

public class CameraMapper extends Mapper<Camera> {

	@Override
	protected Camera getObject(ResultSet result) throws SQLException {
		Camera camera = new Camera();
		camera.setIdCamera(result.getInt(1));
		camera.setIdLocation(result.getInt(2));
		camera.setName(result.getString(3));
		camera.setUrl(result.getString(4));
		camera.setPort(result.getInt(5));
		camera.setUsername(result.getString(6));
		camera.setPassword(result.getString(7)); // TODO : Decrypt Password
		return camera;
	}

	@Override
	protected String getAllQuery() {
		return "SELECT idCamera, idLocation, name, url, port, username, password FROM Camera";
	}

	@Override
	protected String getByIdQuery() {
		return "SELECT idCamera, idLocation, name, url, port, username, password FROM Camera WHERE idCamera = ?";
	}

	@Override
	protected PreparedStatement getInsertStatement(Camera camera, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("INSERT INTO Camera (idLocation, name, url, port, username, password) VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		prep.setInt(1, camera.getIdLocation());
		prep.setString(2, camera.getName());
		prep.setString(3, camera.getUrl());
		prep.setInt(4, camera.getPort());
		prep.setString(5, camera.getUsername());
		prep.setString(6, camera.getPassword());
		return prep;
	}

	@Override
	protected PreparedStatement getUpdateStatement(Camera camera, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("UPDATE Camera SET idLocation = ?, name = ?, url = ?, port = ?, username = ?, password = ? WHERE idCamera = ?");
		prep.setInt(1, camera.getIdLocation());
		prep.setString(2, camera.getName());
		prep.setString(3, camera.getUrl());
		prep.setInt(4, camera.getPort());
		prep.setString(5, camera.getUsername());
		prep.setString(6, camera.getPassword());
		prep.setInt(7, camera.getIdCamera());
		return prep;
	}

	@Override
	protected PreparedStatement getDeleteStatement(int id, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("DELETE FROM Camera WHERE idCamera = ?");
		prep.setInt(1, id);
		return prep;
	}

}
