package vigionline.common.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		PreparedStatement prep = con.prepareStatement("INSERT INTO Image (date, filename, size) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
		prep.setDate(1, image.getDate());
		prep.setString(2, image.getFilename());
		prep.setInt(3, image.getSize());
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
}
