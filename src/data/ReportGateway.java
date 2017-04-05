package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportGateway {
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public ReportGateway(){}
	
	private Connection getConnection() throws SQLException {
		Connection connection;
		connection = ConnectionFactory.getInstance().getConnection();
		return connection;
	}
	
	public void create(String date,int id) {
		try {
			String query = "INSERT INTO report(Date, user_id) VALUES(?,?)";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, date);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(int id, Report r) {
		try {
			String query = "UPDATE report SET Date=? WHERE id=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, r.getDate());
			ps.setInt(2,id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	
	public ResultSet getById(int id) {
		try {
			String query = "SELECT * FROM report WHERE id=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getReportIdByUserDate(String date, int id) {
		try {
			String query = "SELECT id FROM report WHERE date=? AND user_id=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, date);
			ps.setInt(2, id);
			rs = ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getReportIdByUser(int id) {
		try {
			String query = "SELECT id FROM report WHERE user_id=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getDateByIdUser(int id,int user) {
		try {
			String query = "SELECT id FROM report WHERE id=? AND user_id=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ps.setInt(2, user);
			rs = ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getReportByDate(String date) {
		try {
			String query = "SELECT * FROM report WHERE date=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, date);
			rs = ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getReportByUser(int id) {
		try {
			String query = "SELECT * FROM report WHERE user_id=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getReportByUserDate(int id, String date) {
		try {
			String query = "SELECT * FROM report WHERE user_id=? AND Date=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, date);
			rs = ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void delete(int id) {
		try {
			String query = "DELETE FROM report WHERE id=?";
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
			String query = "SELECT * FROM report;";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
