package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountGateway {
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	private Connection getConnection() throws SQLException {
		Connection connection;
		connection = ConnectionFactory.getInstance().getConnection();
		return connection;
	}
	
	public AccountGateway(){}
	
	public void create(Account a,int id,int user_id) {
		try {
			String query = "INSERT INTO account(id, Type, Amount, Client_CNP, report_id, report_user_id) VALUES(?,?,?,?,?,?)";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, a.getId());
			ps.setString(2, a.getAccountType().name());
			ps.setDouble(3, a.getAmount());
			ps.setLong(4, a.getClient().getCnp());
			ps.setInt(5, id);
			ps.setLong(6, user_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(int id, Account a) {
		try {
			String query = "UPDATE account SET id=?,Type=?,Amount=? WHERE id=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, a.getId());
			ps.setString(2, a.getAccountType().name());
			ps.setDouble(3, a.getAmount());
			ps.setInt(4, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	
	public ResultSet getById(int id) {
		try {
			String query = "SELECT * FROM account WHERE id=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	

	
	public void delete(int id) {
		try {
			String query = "DELETE FROM account WHERE id=?";
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
			String query = "SELECT * FROM account;";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public ResultSet readByClient(long id) {
		try {
			String query = "SELECT * FROM account WHERE Client_CNP=? AND Type='SPENDING';";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setLong(1, id);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
}
