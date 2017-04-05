package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ClientGateway {
	
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public ClientGateway(){}
	
	private Connection getConnection() throws SQLException {
		Connection connection;
		connection = ConnectionFactory.getInstance().getConnection();
		return connection;
	}
	
	public void add(Client c) {
		try {
			String query = "INSERT INTO client(CNP, Name, Card_Number, Address, Type) VALUES(?,?,?,?,?)";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setLong(1, c.getCnp());
			ps.setString(2, c.getName());
			ps.setString(3, c.getCardNumber());
			ps.setString(4, c.getAddress());
			ps.setString(5, c.getcType().name());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(long id, Client c) {
		try {
			String query = "UPDATE client SET CNP=?,Name=?,Card_Number=?,Address=?,Type=? WHERE CNP=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setLong(1,c.getCnp());
			ps.setString(2, c.getName());
			ps.setString(3, c.getCardNumber());
			ps.setString(4, c.getAddress());
			ps.setString(5, c.getcType().name());
			ps.setLong(6,id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	
	public ResultSet getById(long id) {
		try {
			String query = "SELECT * FROM client WHERE CNP=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setLong(1, id);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet read() {
		try {
			String query = "SELECT * FROM client;";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return rs;
	}

		
}
