package vigionline.common.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import vigionline.common.database.connector.MySqlConnector;
import vigionline.common.model.Model;

public class ModelMapper extends Mapper<Model> {

	@Override
	protected Model getObject(ResultSet result) throws SQLException {
		Model model = new Model();
		model.setIdModel(result.getInt(1));
		model.setIdManufacturer(result.getInt(2));
		model.setName(result.getString(3));
		model.setVideoUrl(result.getString(4));
		model.setAudioUrl(result.getString(5));
		model.setIsMJPEG(result.getBoolean(6));
		model.setWidth(result.getInt(7));
		model.setHeight(result.getInt(8));
		model.setBeginLinesToDiscard(result.getInt(9));
		model.setEndLinesToDiscard(result.getInt(10));
		return model;
	}

	@Override
	protected String getAllQuery() {
		return "SELECT " +
				"idModel, " +
				"idManufacturer, " +
				"name, " +
				"videoUrl, " +
				"audioUrl, " +
				"isMJPEG, " +
				"width, " +
				"height, " +
				"beginLinesToDiscard, " +
				"endLinesToDiscard FROM Model";   
	}

	@Override
	protected String getByIdQuery() {
		return "SELECT " +
				"idModel, " +
				"idManufacturer, " +
				"name, " +
				"videoUrl, " +
				"audioUrl, " +
				"isMJPEG, " +
				"width, " +
				"height, " +
				"beginLinesToDiscard, " +
				"endLinesToDiscard FROM Model WHERE idModel = ?";
	}

	@Override
	protected PreparedStatement getInsertStatement(Model model, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("INSERT INTO Model (idManufacturer, name, videoUrl, audioUrl, isMJPEG, width, height, beginLinesToDiscard, endLinesToDiscard) " +
				"VALUES(?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		prep.setInt(1, model.getIdManufacturer());
		prep.setString(2, model.getName());
		prep.setString(3, model.getVideoUrl());
		prep.setString(4, model.getAudioUrl());
		prep.setBoolean(5, model.isMJPEG());
		prep.setInt(6, model.getWidth());
		prep.setInt(7, model.getHeight());
		prep.setInt(8, model.getBeginLinesToDiscard());
		prep.setInt(9, model.getEndLinesToDiscard());
		return prep;
	}

	@Override
	protected PreparedStatement getUpdateStatement(Model model, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("UPDATE Model SET idManufacturer = ?, name = ?, videoUrl = ?, audioUrl = ?, isMJPEG = ?, width = ?, height = ?, beginLinesToDiscard = ?, endLinesToDiscard = ? " +
				"WHERE idModel = ?");
		prep.setInt(1, model.getIdManufacturer());
		prep.setString(2, model.getName());
		prep.setString(3, model.getVideoUrl());
		prep.setString(4, model.getAudioUrl());
		prep.setBoolean(5, model.isMJPEG());
		prep.setInt(6, model.getWidth());
		prep.setInt(7, model.getHeight());
		prep.setInt(8, model.getBeginLinesToDiscard());
		prep.setInt(9, model.getEndLinesToDiscard());
		prep.setInt(10, model.getIdModel());
		return prep;
	}

	@Override
	protected PreparedStatement getDeleteStatement(int id, Connection con)
			throws SQLException {
		PreparedStatement prep = con.prepareStatement("DELETE FROM Model WHERE idModel = ?");
		prep.setInt(1, id);
		return prep;
	}

	public List<Model> getModelsByManufacturer(int idManufacturer) throws SQLException {
		Connection con = MySqlConnector.getConnection();
		PreparedStatement prep = con.prepareStatement(getAllQuery() + " WHERE idManufacturer = ?");
		prep.setInt(1, idManufacturer);
		return getListByPreparedStatement(prep);
	}
}
