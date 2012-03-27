package vigionline.common.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import vigionline.common.database.connector.MySqlConnector;

public abstract class Mapper<T> {
		
	public List<T> getAll() throws SQLException
	{
		List<T> list = new LinkedList<T>();
		Connection con = MySqlConnector.getConnection();
		try
		{
			Statement stm = con.createStatement();
			
			ResultSet rs = stm.executeQuery(getAllQuery());
			while(rs.next()) { 	list.add(getObject(rs)); }
			rs.close();
		}finally
		{
			con.close();
		}
		return list;
	}
	public T getById(int id) throws SQLException
	{
		Connection con = MySqlConnector.getConnection();
		T obj = null;
		try
		{
			PreparedStatement prep = con.prepareStatement(getByIdQuery());
			prep.setInt(1, id);
			
			ResultSet rs = prep.executeQuery(getAllQuery());
			obj = getObject(rs);
			rs.close();
			
		}finally	{ 	con.close(); 	}
		return obj;
	}
	
	public int insert(T elem) throws SQLException
	{
		int res = 0;
		Connection con = MySqlConnector.getConnection();
		try
		{
			PreparedStatement prep = getInsertStatement(elem, con);
			res = prep.executeUpdate();
		}
		finally
		{
			con.close();
		}
		return res;
	}
	
	public int update(T elem) throws SQLException
	{
		int res = 0;
		Connection con = MySqlConnector.getConnection();
		try
		{
			PreparedStatement prep = getUpdateStatement(elem, con);
			res = prep.executeUpdate();
		}
		finally
		{
			con.close();
		}
		return res;
	}
	
	public int delete(int id) throws SQLException
	{
		int res = 0;
		Connection con = MySqlConnector.getConnection();
		try
		{
			PreparedStatement prep = getDeleteStatement(id, con);
			res = prep.executeUpdate();
		}
		finally
		{
			con.close();
		}
		return res;
	}
	
	/** Row to Object **/
	protected abstract T getObject(ResultSet result);
	
	/** Queries **/
	protected abstract String getAllQuery();
	protected abstract String getByIdQuery();
	
	/** Prepared Statements **/
	protected abstract PreparedStatement getInsertStatement(T elem, Connection con);
	protected abstract PreparedStatement getUpdateStatement(T elem, Connection con);
	protected abstract PreparedStatement getDeleteStatement(int id, Connection con);
}
