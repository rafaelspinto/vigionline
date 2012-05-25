package vigionline.common.database.mapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vigionline.common.database.connector.MySqlConnector;
import vigionline.common.model.Image;

public class ImageMapper extends Mapper<Image> {

	@Override
	protected Image getObject(ResultSet result) throws SQLException {
		Image image = new Image();
		image.setIdImage(result.getInt(1));
		image.setDate(result.getDate(2));
		image.setFilename(result.getString(3));
		image.setSize(result.getInt(4));
		return image;
	}

	@Override
	protected String getAllQuery() {
		return "SELECT idImage, date, filename, size FROM Image";
	}

	@Override
	protected String getByIdQuery() {
		return "SELECT idImage, date, filename, size FROM Image WHERE idImage = ?";
	}

	@Override
	protected PreparedStatement getInsertStatement(Image image, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("INSERT INTO Image (idCamera, date, filename, size) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		prep.setInt(1, image.getIdCamera());
		prep.setDate(2, image.getDate());
		prep.setString(3, image.getFilename());
		prep.setInt(4, image.getSize());
		return prep;
	}

	@Override
	protected PreparedStatement getUpdateStatement(Image image, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("UPDATE Image SET date = ?, filename = ?, size = ? WHERE idImage = ?");
		prep.setDate(1, image.getDate());
		prep.setString(2, image.getFilename());
		prep.setInt(3, image.getSize());
		prep.setInt(4, image.getIdImage());
		return prep;
	}

	@Override
	protected PreparedStatement getDeleteStatement(int id, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("DELETE FROM Image WHERE idImage = ?");
		prep.setInt(1, id);
		return prep;
	}
	
	public ResultSet getImagesFrom(int idCamera, Date date) throws SQLException
	{
		Connection con = MySqlConnector.getConnection();
		
		PreparedStatement prep = con.prepareStatement(getAllQuery()+ " WHERE idCamera = ? AND date >= ? ");
		prep.setInt(1, idCamera);
		prep.setDate(2, date);
		return prep.executeQuery();
	}
}
