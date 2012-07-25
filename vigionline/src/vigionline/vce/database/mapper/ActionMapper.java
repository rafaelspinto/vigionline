package vigionline.vce.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import vigionline.common.model.Action;
import vigionline.vce.database.connector.MySqlConnector;

public class ActionMapper extends Mapper<Action> {

	@Override
	public Action getObject(ResultSet result) throws SQLException {
		Action action = new Action();
		action.setIdAction(result.getInt(1));
		action.setIdModel(result.getInt(2));
		action.setName(result.getString(3));
		action.setAction1(result.getString(4));
		action.setAction2(result.getString(5));
		return action;
	}

	@Override
	protected String getAllQuery() {
		return "SELECT idAction, idModel, name, action1, action2 FROM Action";
	}

	@Override
	protected String getByIdQuery() {
		return "SELECT idAction, idModel, name, action1, action2 FROM Action WHERE idAction = ?";
	}

	@Override
	protected String getByNameQuery() {
		return "SELECT idAction, idModel, name, action1, action2 FROM Action WHERE name = ?";
	}

	@Override
	protected PreparedStatement getInsertStatement(Action action, Connection con)
			throws SQLException {
		PreparedStatement prep = con
				.prepareStatement(
						"INSERT INTO Action (idModel, name, action1, action2) VALUES(?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
		prep.setInt(1, action.getIdModel());
		prep.setString(2, action.getName());
		prep.setString(3, action.getAction1());
		prep.setString(4, action.getAction2());
		return prep;
	}

	@Override
	protected PreparedStatement getUpdateStatement(Action action, Connection con)
			throws SQLException {
		PreparedStatement prep = con
				.prepareStatement(
						"UPDATE Action SET idModel = ?, name = ?, action1 = ?, action2 = ? WHERE idAction = ?",
						Statement.RETURN_GENERATED_KEYS);
		prep.setInt(1, action.getIdModel());
		prep.setString(2, action.getName());
		prep.setString(3, action.getAction1());
		prep.setString(4, action.getAction2());
		prep.setInt(5, action.getIdAction());
		return prep;
	}

	@Override
	protected PreparedStatement getDeleteStatement(int id, Connection con)
			throws SQLException {
		PreparedStatement prep = con
				.prepareStatement("DELETE FROM Action WHERE idAction = ?");
		prep.setInt(1, id);
		return prep;
	}

	public List<Action> getByIdModel(int idModel) throws SQLException {
		Connection con = MySqlConnector.getConnection();
		PreparedStatement prep = con.prepareStatement(getAllQuery()
				+ " WHERE idModel = ?");
		prep.setInt(1, idModel);
		return getListByPreparedStatement(prep);
	}
}
