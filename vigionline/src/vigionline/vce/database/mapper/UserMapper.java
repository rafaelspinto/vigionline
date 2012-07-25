package vigionline.vce.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vigionline.common.model.User;

public class UserMapper extends Mapper<User> {

	@Override
	public User getObject(ResultSet result) throws SQLException {
		User user = new User();
		user.setIdUser(result.getInt(1));
		user.setName(result.getString(2));
		user.setUsername(result.getString(3));
		user.setPassword(result.getString(4)); // TODO : password decryption
		return user;
	}

	@Override
	protected String getAllQuery() {
		return "SELECT idUser, name, username, password FROM User";
	}

	@Override
	protected String getByIdQuery() {
		return "SELECT idUser, name, username, password FROM User WHERE idUser = ?";
	}
	
	@Override
	protected String getByNameQuery() {
		return "SELECT idUser, name, username, password FROM User WHERE username = ?";
	}

	@Override
	protected PreparedStatement getInsertStatement(User user, Connection con) throws SQLException{
		PreparedStatement prep = con.prepareStatement("INSERT INTO User (name, username, password) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
		prep.setString(1, user.getName());
		prep.setString(2, user.getUsername());
		prep.setString(3, user.getPassword()); // TODO : password encryption
		return prep;
	}

	@Override
	protected PreparedStatement getUpdateStatement(User user, Connection con)  throws SQLException{
		PreparedStatement prep = con.prepareStatement("UPDATE User SET name = ?, username = ?, password = ? WHERE idUser = ?");
		prep.setString(1, user.getName());
		prep.setString(2, user.getUsername());
		prep.setString(3, user.getPassword()); // TODO : password encryption
		prep.setInt(4, user.getIdUser());
		return prep;
	}

	@Override
	protected PreparedStatement getDeleteStatement(int id, Connection con) throws SQLException {
		PreparedStatement prep = con.prepareStatement("DELETE FROM User WHERE idUser = ?");
		prep.setInt(1, id);
		return prep;
	}
}
