package business;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import data.Account;
import data.Bill;

import data.Client;
import data.Report;



public class BillService {
	
	private Bill bill;
	
	public BillService(){
		this.bill = new Bill();
	}
	
	public void addWithoutAccount(long id,double sum, int dest,String username){
		ClientService c = new ClientService();
		AccountService as = new AccountService();
		ReportService rs = new ReportService();
		UserService us = new UserService();
		Account a = as.getAccountById(dest);
		a.setId(dest);
		Client cl = c.getClientById(id);
		Report r = rs.getReportByUserDate(username, rs.getCurrentDate());
		a.setAmount(a.getAmount()+sum);
		as.update(a);
		Bill b = new Bill(sum,cl,a,r);
		bill.create(b,rs.getReportId(username, b.getReport().getDate()),us.getIdByUsername(username));	
	}
	
	public void addWithAccount(int source, int destination, double amount,String username){
		AccountService as = new AccountService();
		ReportService rs = new ReportService();
		UserService us = new UserService();
		Account sa = as.getAccountById(source);
		Account da = as.getAccountById(destination);
		sa.setId(source);
		da.setId(destination);
		
		if(sa.getAmount()<amount){
			JOptionPane.showMessageDialog(null, "Sold insuficient pentru transfer!");
			return;
		}
		if(!(rs.checkUserDate(rs.getCurrentDate(), username))){
			rs.create(username);
		}
		sa.setAmount(sa.getAmount()-amount);
		da.setAmount(da.getAmount()+amount);
		as.update(sa);
		as.update(da);
		Report r = rs.getReportByUserDate(username, rs.getCurrentDate());
		Bill b = new Bill(amount,sa.getClient(),da,r);
		this.bill.create(b, rs.getReportId(username, b.getReport().getDate()), us.getIdByUsername(username));
	}
	
	
	public List<Bill> listByUserDate(String username, String date){
		ReportService rs = new ReportService();
		AccountService as = new AccountService();
		ClientService cs = new ClientService();
		UserService us = new UserService();
		List<Bill> b = new ArrayList<Bill>();
		int rId = rs.getReportId(username, date);
		Bill bill = null;
		ResultSet res = this.bill.getByUser(us.getIdByUsername(username),rId);
		try {
			while(res.next()){
				bill = new Bill(res.getDouble("Amount"),cs.getClientById(res.getLong("client_CNP")),as.getAccountById(res.getInt("account_id")),rs.getReportByUserDate(username, date));
				bill.getAccount().setId(res.getInt("account_id"));
				b.add(bill);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	

}
