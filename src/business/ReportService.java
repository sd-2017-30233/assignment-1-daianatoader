package business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import data.Report;
import data.ReportGateway;

public class ReportService {
	private ReportGateway reportGateway;
	
	public ReportService(){
		this.reportGateway = new ReportGateway();
	}
	
	public boolean checkId(int id){
		ResultSet rs = reportGateway.read();
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
	
	public boolean checkUserDate(String date, String username){
		UserService u = new UserService();
		int id = u.getIdByUsername(username);
		ResultSet rs = reportGateway.read();
		try {
			while(rs.next()){
					if((id == rs.getInt("user_id")) && (date.equals(rs.getString("Date"))))
						return true;
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return false;
	}
	

	
	public Report getReportById(int id){
		UserService u = new UserService();
		Report r = null;
		ResultSet rs = reportGateway.getById(id);
		try {
			while(rs.next()){
				r = new Report(rs.getString("Date"),u.getUserById(rs.getInt("user_id")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return r;
	}
	
	public Report getReportByDate(String date){
		UserService u = new UserService();
		Report r = null;
		ResultSet rs = reportGateway.getReportByDate(date);
		try {
			while(rs.next()){
				r = new Report(rs.getString("Date"),u.getUserById(rs.getInt("user_id")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return r;
	}
	
	public Report getReportByUser(String username){
		Report r = null;
		UserService u = new UserService();
		int id = u.getIdByUsername(username);
		ResultSet rs = reportGateway.getById(id);
		try {
			while(rs.next()){
				r = new Report(rs.getString("Date"),u.getUserById(rs.getInt("user_id")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return r;
	}
	
	public Report getReportByUserDate(String username,String date){
		Report r = null;
		UserService u = new UserService();
		int id = u.getIdByUsername(username);
		
		ResultSet rs = reportGateway.getReportByUserDate(id, date);
		try {
			while(rs.next()){
				r = new Report(rs.getString("Date"),u.getUserById(rs.getInt("user_id")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return r;
	}
	
	public int getReportId(String username,String date){
		UserService u = new UserService();
		int id = u.getIdByUsername(username);
		int reportId=0;
		ResultSet rs = reportGateway.getReportIdByUserDate(date, id);
		try {
			while(rs.next()){
				reportId = rs.getInt("id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return reportId;
	}
	
	public String getReportDate(int username,int id){
		String date = null;
		ResultSet rs = reportGateway.getDateByIdUser(id, username);
		try {
			while(rs.next()){
				date = rs.getString("Date");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return date;
	}
	
	public String getCurrentDate(){
		
		 Date date = Calendar.getInstance().getTime();
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		 return sdf.format(date);
	}
	
	public void create(String username){
		UserService u = new UserService();
		int id = u.getIdByUsername(username);
		reportGateway.create(getCurrentDate(), id);
	}
}
