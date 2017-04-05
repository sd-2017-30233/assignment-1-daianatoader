package data;

import java.util.Random;

public class Account {
	
	private int id;
	public enum AccountType {
		SAVING,SPENDING
	};
	private AccountType aType;
	private double amount;
	private Client client;
	private Report report;
	Random rnd;
	
	private Account(){}

	public Account(AccountType aType, double amount,Client client, Report report) {
		this.aType = aType;
		this.amount = amount;
		this.client = client;
		this.report = report;
	}

	public int getId() {
		return id;
	}
	public void setId(int id){
		this.id = id;
	}

	public void generateId() {
		rnd = new Random();
		this.id = 100000 + rnd.nextInt(999999);
	}

	public AccountType getAccountType() {
		return aType;
	}

	public void setAccountType(AccountType aType) {
		this.aType = aType;
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
	
	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
	
	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append(" Id: "+this.getId());
		result.append(" Type: "+this.getAccountType());
		result.append(" Amount: "+this.getAmount());
		result.append(" Client: "+this.getClient());
		result.append(" Report: "+this.getReport());
		return result.toString();	
	}



}
