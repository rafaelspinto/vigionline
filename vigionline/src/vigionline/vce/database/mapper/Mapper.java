package vigionline.vce.database.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import vigionline.vce.database.connector.MySqlConnector;

public abstract class Mapper<T> {
		
	public List<T> getAll() throws SQLException
	{
		List<T> list = new LinkedList<T>();
		try(
			Connection con = MySqlConnector.getConnection();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(getAllQuery());
		)
		{
			while(rs.next())  	
				list.add(getObject(rs));
		}
		return list;
	}
	public T getById(int id) throws SQLException
	{
		T obj = null;
		try(
				Connection con = MySqlConnector.getConnection();
				PreparedStatement prep = con.prepareStatement(getByIdQuery());				
			)
		{
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if( rs.next() )
			{
				obj = getObject(rs);
			}
			rs.close();			
		}
		return obj;
	}
	
	public T getByName(String name) throws SQLException
	{
		T obj = null;
		try(
				Connection con = MySqlConnector.getConnection();
				PreparedStatement prep = con.prepareStatement(getByNameQuery());
			)
		{
			prep.setString(1, name);			
			ResultSet rs = prep.executeQuery();
			if( rs.next() )
			{
				obj = getObject(rs);
			}
			rs.close();	
		}
		return obj;
	}
	
	public List<T> getListByPreparedStatement(PreparedStatement prep) throws SQLException
	{
		List<T> list = new LinkedList<T>();
		try
		{
			ResultSet rs = prep.executeQuery();
			while(rs.next()) { 	list.add(getObject(rs)); }
			rs.close();
		}finally
		{
			prep.close();
		}
		return list;
	}
	
	public int insert(T elem) throws SQLException
	{
		int res = 0;	
		try(
				Connection con = MySqlConnector.getConnection();
				PreparedStatement prep = getInsertStatement(elem, con);
			)
		{	
			prep.executeUpdate();
			ResultSet rs = prep.getGeneratedKeys();
			if( rs.next() )
				res = rs.getInt(1);
		}
		return res;
	}
	
	public int update(T elem) throws SQLException
	{
		int res = 0;
		try(
				Connection con = MySqlConnector.getConnection();
				PreparedStatement prep = getUpdateStatement(elem, con);
			)
		{
			res = prep.executeUpdate();
		}
		return res;
	}
	
	public int delete(int id) throws SQLException
	{
		int res = 0;
		try(
			Connection con = MySqlConnector.getConnection();
			PreparedStatement prep = getDeleteStatement(id, con);
			)
		{			
			res = prep.executeUpdate();
		}
		return res;
	}
	
	/** Row to Object **/
	public abstract T getObject(ResultSet result) throws SQLException;
	
	/** Queries **/
	protected abstract String getAllQuery();
	protected abstract String getByIdQuery();
	protected abstract String getByNameQuery();
	
	/** Prepared Statements **/
	protected abstract PreparedStatement getInsertStatement(T elem, Connection con)  throws SQLException;
	protected abstract PreparedStatement getUpdateStatement(T elem, Connection con)  throws SQLException;
	protected abstract PreparedStatement getDeleteStatement(int id, Connection con)  throws SQLException;
}
