package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserGateway {
	
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public UserGateway(){}
	
	private Connection getConnection() throws SQLException {
		Connection connection;
		connection = ConnectionFactory.getInstance().getConnection();
		return connection;
	}
	
	public void create(User u) {
		try {
			String query = "INSERT INTO user(Name, Username, Password, Type) VALUES(?,?,?,?)";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, u.getName());
			ps.setString(2, u.getUsername());
			ps.setString(3, u.getPassword());
			ps.setInt(4, u.getType());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(int id, User u) {
		try {
			String query = "UPDATE user SET Name=?,Username=?,Password=?,Type=? WHERE id=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, u.getName());
			ps.setString(2, u.getUsername());
			ps.setString(3, u.getPassword());
			ps.setInt(4, u.getType());
			ps.setInt(5,id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	
	public ResultSet getById(int id) {
		try {
			String query = "SELECT * FROM user WHERE id=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getIdByUsername(String username) {
		try {
			String query = "SELECT * FROM user WHERE Username=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getIdByName(String name) {
		try {
			String query = "SELECT * FROM user WHERE Name=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, name);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void delete(int id) {
		try {
			String query = "DELETE FROM user WHERE id=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ResultSet read() {
		try {
			String query = "SELECT * FROM bank.user;";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			//System.out.println(rs.getString("Username"));
			//System.out.println(rs.getString("Password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;

	}
}
