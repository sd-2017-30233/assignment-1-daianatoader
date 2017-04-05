package data;

import java.util.ArrayList;
import java.util.List;

public class Client {
	private long cnp;
	private String name;
	private String cardNumber;
	private String address;
	public enum ClientType {
		INDIVIDUAL,LEGAL_ENTITY
	};
	private ClientType cType;
	private List<Account> accList;
	
	
	public Client(){}
	
	
	public Client(long cnp, String name, String cardNumber, String address, ClientType cType) {
		this.cnp = cnp;
		this.name = name;
		this.cardNumber = cardNumber;
		this.address = address;
		this.cType = cType;
		this.accList = new ArrayList<Account>();
	}


	public long getCnp() {
		return cnp;
	}
	public void setCnp(long cnp) {
		this.cnp = cnp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}



	public ClientType getcType() {
		return cType;
	}


	public void setcType(ClientType cType) {
		this.cType = cType;
	}


	
	public List<Account> getAccList() {
		return accList;
	}


	public void setAcc(Account a) {
		this.accList.add(a);
	}


	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append(" CNP: "+this.getCnp());
		result.append(" Name: "+this.getName());
		result.append(" Card number: "+this.getCardNumber());
		result.append(" Address: "+this.getAddress());
		result.append(" Client type: "+this.getcType());
		return result.toString();	
	}
	
}
