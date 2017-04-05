package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transfer {
	
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	

	

	private double amount;
	private Account source;
	private Account destination;
	private Report report;
	
	
	
	public Transfer(double amount, Account source,Account destination,Report report) {
		this.amount = amount;
		this.source = source;
		this.destination = destination;
		this.setReport(report);
	}

	public Transfer(){}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


	public Account getSource() {
		return source;
	}

	public void setSource(Account source) {
		this.source = source;
	}

	public Account getDestination() {
		return destination;
	}

	public void setDestination(Account destination) {
		this.destination = destination;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
	
	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append(" Amount: "+this.getAmount());
		result.append(" Source account: "+this.getSource());
		result.append(" Destination account: "+this.getDestination());
		result.append(" Report: "+this.getReport());
		return result.toString();	
	}
	
	private Connection getConnection() throws SQLException {
		Connection connection;
		connection = ConnectionFactory.getInstance().getConnection();
		return connection;
	}
	
	public void create(Transfer t,int reportId,int userId) {
		try {
			String query = "INSERT INTO transfer(Amount, Account_Source, Client_CNP_Source, Account_Destination, Client_CNP_Destination, report_id, report_user_id) VALUES(?,?,?,?,?,?,?)";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setDouble(1, t.getAmount());
			ps.setInt(2, t.getSource().getId());
			ps.setLong(3, t.getSource().getClient().getCnp());
			ps.setInt(4, t.getDestination().getId());
			ps.setLong(5, t.getDestination().getClient().getCnp());
			ps.setInt(6, reportId);
			ps.setLong(7, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	

	
	public ResultSet getById(int id) {
		try {
			String query = "SELECT * FROM transfer WHERE id=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getByReportUser(int reportId, int userId) {
		try {
			String query = "SELECT * FROM transfer WHERE report_id=? AND report_user_id=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, reportId);
			ps.setInt(2, userId);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}


}
