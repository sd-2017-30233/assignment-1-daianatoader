package data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Report {
	private String date;
	private User user;
	
	public Report(){}
	

	
	public Report(String date, User user) {
		this.date = date;
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append(" Date: "+this.getDate());
		result.append(" User: "+this.getUser());
		return result.toString();	
	}
}
