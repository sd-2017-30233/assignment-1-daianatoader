package business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import data.Account;
import data.Report;
import data.Transfer;


public class TransferService {
	
	private Transfer transfer;
	
	public TransferService(){
		this.transfer = new Transfer();
	}
	


	
	public void addTransfer(int source, int destination, double amount,String username){
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
		Transfer t = new Transfer(amount,sa,da,r);
		this.transfer.create(t, rs.getReportId(username, t.getReport().getDate()), us.getIdByUsername(username));
	}
	
	public List<Transfer> listByUserDate(String username, String date){
		ReportService rs = new ReportService();
		AccountService as = new AccountService();
		UserService us = new UserService();
		List<Transfer> l = new ArrayList<Transfer>();
		int rId = rs.getReportId(username, date);
		ResultSet res = this.transfer.getByReportUser(rId, us.getIdByUsername(username));
		Transfer t =null;
		try {
			while(res.next()){
				t=new Transfer(res.getDouble("Amount"),as.getAccountById(res.getInt("Account_Source")),as.getAccountById(res.getInt("Account_Destination")),rs.getReportByUserDate(username, date));
				t.getSource().setId(res.getInt("Account_Source"));
				t.getDestination().setId(res.getInt("Account_Destination"));
				l.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	
}
