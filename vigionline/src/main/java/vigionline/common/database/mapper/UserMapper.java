package vigionline.common.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vigionline.common.model.User;

public class UserMapper extends Mapper<User> {

	@Override
	protected User getObject(ResultSet result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getAllQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getByIdQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PreparedStatement getInsertStatement(User elem, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PreparedStatement getUpdateStatement(User elem, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PreparedStatement getDeleteStatement(int id, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

}
