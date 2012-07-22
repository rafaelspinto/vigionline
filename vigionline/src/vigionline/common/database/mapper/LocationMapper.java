package vigionline.common.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vigionline.common.model.Location;

public class LocationMapper extends Mapper<Location> {

	@Override
	public Location getObject(ResultSet result) throws SQLException {
		Location location = new Location();
		location.setIdLocation(result.getInt(1));
		location.setName(result.getString(2));
		return location;
	}

	@Override
	protected String getAllQuery() {
		return "SELECT idLocation, name FROM Location";
	}

	@Override
	protected String getByIdQuery() {
		return "SELECT idLocation, name FROM Location WHERE idLocation = ?";
	}
	
	@Override
	protected String getByNameQuery() {
		return "SELECT idLocation, name FROM Location WHERE name = ?";
	}

	@Override
	protected PreparedStatement getInsertStatement(Location location, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("INSERT INTO Location (name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
		prep.setString(1, location.getName());
		return prep;
	}

	@Override
	protected PreparedStatement getUpdateStatement(Location location, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("UPDATE Location SET name = ? WHERE idLocation = ?");
		prep.setString(1, location.getName());
		prep.setInt(2, location.getIdLocation());
		return prep;
	}

	@Override
	protected PreparedStatement getDeleteStatement(int id, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("DELETE FROM Location WHERE idLocation = ?");
		prep.setInt(1, id);
		return prep;
	}
}
