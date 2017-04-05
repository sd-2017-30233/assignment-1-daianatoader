package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bill {
	
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	private double amount;
	private Client client;
	private Account account;
	private Report report;
	
	public Bill(){}

	public Bill(double amount, Client client, Account account, Report report) {
		this.amount = amount;
		this.client = client;
		this.account = account;
		this.report = report;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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
		result.append(" Client: "+this.getClient());
		result.append(" Account: "+this.getAccount());
		result.append(" Report: "+this.getReport());
		return result.toString();	
	}

	
	
	private Connection getConnection() throws SQLException {
		Connection connection;
		connection = ConnectionFactory.getInstance().getConnection();
		return connection;
	}
	
	public void create(Bill b,int reportId,int userId) {
		try {
			String query = "INSERT INTO bill(Amount,client_CNP, account_id, account_Client_CNP, report_id, report_user_id) VALUES(?,?,?,?,?,?)";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setDouble(1, b.getAmount());
			ps.setLong(2, b.getClient().getCnp());
			ps.setInt(3, b.getAccount().getId());
			ps.setLong(4, b.getAccount().getClient().getCnp());
			ps.setInt(5, reportId);
			ps.setLong(6, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	

	
	public ResultSet getById(int id) {
		try {
			String query = "SELECT * FROM bill WHERE id=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getByDate(int id) {
		try {
			String query = "SELECT * FROM bill WHERE report_id=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getByUser(int id,int reportId) {
		try {
			String query = "SELECT * FROM bill WHERE report_user_id=? AND report_id=?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ps.setInt(2, reportId);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
