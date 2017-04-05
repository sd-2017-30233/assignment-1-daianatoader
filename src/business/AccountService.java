package business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import data.Account;
import data.AccountGateway;
import data.Client;
import data.Report;
import data.Account.AccountType;

public class AccountService {
private AccountGateway accountGateway;
	
	public AccountService(){
		this.accountGateway = new AccountGateway();
	}
	
	public boolean checkId(int id){
		ResultSet rs = accountGateway.read();
		try {
			while(rs.next()){
					if(id == rs.getInt("id"))
						return true;
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return false;
	}
	
	public void create(String type, double amount, long CNP, String username){
		ReportService rs = new ReportService();
		ClientService cs = new ClientService();
		UserService us = new UserService();
		String date = rs.getCurrentDate();
		if(rs.checkUserDate(date, username)==false)
			rs.create(username);
		Report r = rs.getReportByUserDate(username, date);
		int id = rs.getReportId(username, date);
		
		int userid = us.getIdByUsername(username);
		Client c = cs.getClientById(CNP);
		create(new Account(AccountType.valueOf(type),amount,c,r),id,userid);
	}
	
	public void create(Account a,int id,int user_id){
		a.generateId();
		this.accountGateway.create(a,id,user_id);
		a.getClient().setAcc(a);
	}
	
	public void update(Account a){
		a.getClient().getAccList().remove(a);
		this.accountGateway.update(a.getId(), a);
		a.getClient().setAcc(a);
	}
	
	public Account getAccountById(int id){
		Account a = null;
		ClientService clientService = new ClientService();
		ReportService reportService = new ReportService();
		ResultSet rs = accountGateway.getById(id);
		try {
			while(rs.next()){
				a = new Account(AccountType.valueOf(rs.getString("Type")),rs.getDouble("Amount"),clientService.getClientById(rs.getLong("Client_CNP")),reportService.getReportById(rs.getInt("report_id")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return a;
	}
	
	public void update(int id){
		if(!checkId(id)){
			JOptionPane.showMessageDialog(null, "ID gresit/cont inexistent.");
			return;
		}	
		this.update(this.getAccountById(id));
	}
	
	public void delete(int id){
		if(!checkId(id)){
			JOptionPane.showMessageDialog(null, "ID gresit/cont inexistent.");
			return;
		}	
		Account a = this.getAccountById(id);
		a.getClient().getAccList().remove(a);
		this.accountGateway.delete(id);
		
		
	}
	
	public List<Account> listAll(){
		ClientService clientService = new ClientService();
		ReportService reportService = new ReportService();
		List<Account> a = new ArrayList<Account>();
		Account acc = null;
		ResultSet rs = accountGateway.read();
		try {
			while(rs.next()){
				acc = new Account(AccountType.valueOf(rs.getString("Type")),rs.getDouble("Amount"),clientService.getClientById(rs.getLong("Client_CNP")),reportService.getReportById(rs.getInt("report_id")));
				acc.setId(rs.getInt("id"));
				a.add(acc);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return a;
	}
	
	public List<Account> listSpendingAccounts(){
		List<Account> a = new ArrayList<Account>();
		Account acc = null;
		ClientService clientService = new ClientService();
		ReportService reportService = new ReportService();
		ResultSet rs = accountGateway.read();
		try {
			while(rs.next()){
				if(rs.getString("Type").equals(AccountType.SPENDING.name())){
					acc = new Account(AccountType.valueOf(rs.getString("Type")),rs.getDouble("Amount"),clientService.getClientById(rs.getLong("Client_CNP")),reportService.getReportById(rs.getInt("report_id")));
					acc.setId(rs.getInt("id"));
					a.add(acc);
				}
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return a;
	}
	
	public List<Account> listSpendingAccountsByClient(String name){
		List<Account> a = new ArrayList<Account>();
		ClientService clientService = new ClientService();
		ReportService reportService = new ReportService();
		Account acc = null;
		ResultSet rs = accountGateway.read();
		try {
			while(rs.next()){
				if(clientService.getClientById(rs.getLong("Client_CNP")).getName().equals(name) && rs.getString("Type").equals("SPENDING")){
					acc = new Account(AccountType.valueOf(rs.getString("Type")),rs.getDouble("Amount"),clientService.getClientById(rs.getLong("Client_CNP")),reportService.getReportById(rs.getInt("report_id")));
					acc.setId(rs.getInt("id"));
					a.add(acc);
				}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return a;
	}
	
	public List<Account> listAccountsByClient(String name){
		List<Account> a = new ArrayList<Account>();
		ClientService clientService = new ClientService();
		ReportService reportService = new ReportService();
		Account acc = null;
		ResultSet rs = accountGateway.read();
		try {
			while(rs.next()){
				if(clientService.getClientById(rs.getLong("Client_CNP")).getName().equals(name)){
					acc = new Account(AccountType.valueOf(rs.getString("Type")),rs.getDouble("Amount"),clientService.getClientById(rs.getLong("Client_CNP")),reportService.getReportById(rs.getInt("report_id")));
					acc.setId(rs.getInt("id"));
					a.add(acc);
				}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return a;
	}
	
	public List<Account> listLegal(){
		List<Account> a = new ArrayList<Account>();
		Account acc = null;
		ClientService clientService = new ClientService();
		ReportService reportService = new ReportService();
		ResultSet rs = accountGateway.read();
		try {
			while(rs.next()){
				if(clientService.getClientById(rs.getLong("Client_CNP")).getcType().name().equals("LEGAL_ENTITY")){
					acc = new Account(AccountType.valueOf(rs.getString("Type")),rs.getDouble("Amount"),clientService.getClientById(rs.getLong("Client_CNP")),reportService.getReportById(rs.getInt("report_id")));
					acc.setId(rs.getInt("id"));
					a.add(acc);
				}
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return a;
	}
}
