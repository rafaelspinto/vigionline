package vigionline.vce.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vigionline.common.model.Manufacturer;

public class ManufacturerMapper extends Mapper<Manufacturer> {

	@Override
	public Manufacturer getObject(ResultSet result) throws SQLException {
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setIdManufacturer(result.getInt(1));
		manufacturer.setName(result.getString(2));
		return manufacturer;
	}

	@Override
	protected String getAllQuery() {
		return "SELECT DISTINCT idManufacturer, name FROM Manufacturer";
	}

	@Override
	protected String getByIdQuery() {
		return "SELECT DISTINCT idManufacturer, name FROM Manufacturer WHERE idManufacturer = ?";
	}
	
	@Override
	protected String getByNameQuery() {
		return "SELECT DISTINCT idManufacturer, name FROM Manufacturer WHERE name = ?";
	}

	@Override
	protected PreparedStatement getInsertStatement(Manufacturer manufacturer,
			Connection con) throws SQLException {
		PreparedStatement prep = con.prepareStatement("INSERT INTO Manufacturer (name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
		prep.setString(1, manufacturer.getName());
		return prep;
	}

	@Override
	protected PreparedStatement getUpdateStatement(Manufacturer manufacturer,
			Connection con) throws SQLException {
		PreparedStatement prep = con.prepareStatement("UPDATE Manufacturer SET name = ? WHERE idManufacturer = ?", Statement.RETURN_GENERATED_KEYS);
		prep.setString(1, manufacturer.getName());
		prep.setInt(2, manufacturer.getIdManufacturer());
		return prep;
	}

	@Override
	protected PreparedStatement getDeleteStatement(int id, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("DELETE FROM Manufacturer WHERE idManufacturer = ?");
		prep.setInt(1, id);
		return prep;
	}
}
