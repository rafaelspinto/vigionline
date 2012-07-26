package vigionline.vce.database.mapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vigionline.common.model.Image;
import vigionline.vce.database.connector.MySqlConnector;

public class ImageMapper extends Mapper<Image> {

	@Override
	public Image getObject(ResultSet result) throws SQLException {
		Image image = new Image();
		image.setIdImage(result.getInt(1));
		image.setDate(result.getDate(2));
		image.setFilename(result.getString(3));
		image.setSize(result.getInt(4));
		return image;
	}

	@Override
	protected String getAllQuery() {
		return "SELECT DISTINCT idImage, date, filename, size FROM Image";
	}

	@Override
	protected String getByIdQuery() {
		return "SELECT DISTINCT idImage, date, filename, size FROM Image WHERE idImage = ?";
	}

	@Override
	protected String getByNameQuery() {
		return "SELECT DISTINCT idImage, date, filename, size FROM Image WHERE filename = ?";
	}

	@Override
	protected PreparedStatement getInsertStatement(Image image, Connection con)
			throws SQLException {
		PreparedStatement prep = con
				.prepareStatement(
						"INSERT INTO Image (idCamera, date, filename, size) VALUES(?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
		prep.setInt(1, image.getIdCamera());
		prep.setTimestamp(2, new java.sql.Timestamp(image.getDate().getTime()));
		prep.setString(3, image.getFilename());
		prep.setInt(4, image.getSize());
		return prep;
	}

	@Override
	protected PreparedStatement getUpdateStatement(Image image, Connection con)
			throws SQLException {
		PreparedStatement prep = con
				.prepareStatement("UPDATE Image SET date = ?, filename = ?, size = ? WHERE idImage = ?");
		prep.setTimestamp(1, new java.sql.Timestamp(image.getDate().getTime()));
		prep.setString(2, image.getFilename());
		prep.setInt(3, image.getSize());
		prep.setInt(4, image.getIdImage());
		return prep;
	}

	@Override
	protected PreparedStatement getDeleteStatement(int id, Connection con)
			throws SQLException {
		PreparedStatement prep = con
				.prepareStatement("DELETE FROM Image WHERE idImage = ?");
		prep.setInt(1, id);
		return prep;
	}

	public ResultSet getImagesFrom(int idCamera, Date date) throws SQLException {
		Connection con = MySqlConnector.getConnection();
		PreparedStatement prep = con.prepareStatement(getAllQuery()
				+ " WHERE idCamera = ? AND date >= ? ");
		prep.setInt(1, idCamera);
		prep.setTimestamp(2, new java.sql.Timestamp(date.getTime()));
		return prep.executeQuery();
	}

	public ResultSet getImagesFromUntil(Date beginDate, Date endDate)
			throws SQLException {
		Connection con = MySqlConnector.getConnection();
		PreparedStatement prep = con.prepareStatement(getAllQuery()
				+ " WHERE date >= ? AND date <= ?");
		prep.setTimestamp(1, new java.sql.Timestamp(beginDate.getTime()));
		prep.setTimestamp(2, new java.sql.Timestamp(endDate.getTime()));
		return prep.executeQuery();
	}

	public int getImageCount() throws SQLException {
		try (Connection con = MySqlConnector.getConnection();
				PreparedStatement prep = con
						.prepareStatement("SELECT COUNT(*) FROM Image");
				ResultSet rs = prep.executeQuery();) {
			return rs.next() ? rs.getInt(1) : -1;
		}
	}
}
