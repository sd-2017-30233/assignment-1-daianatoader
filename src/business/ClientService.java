package business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import data.Client;
import data.ClientGateway;
import data.Client.ClientType;

public class ClientService {
	
	private ClientGateway clientGateway;
	
	public ClientService(){
		this.clientGateway = new ClientGateway();
	}
	
	public boolean checkId(long id){
		ResultSet rs = clientGateway.read();
		try {
			while(rs.next()){
					if(id == rs.getLong("CNP"))
						return true;
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return false;
	}
	
	public void add(Client c){
		this.clientGateway.add(c);
	}
	public static boolean validateLetters(String txt) {
	    String regx = "^[\\p{L} .'-]+$";
	    Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(txt);
	    return matcher.find();

	}
	
	public void add(long CNP, String name, String address, String cardNumber,String type){
		if(CNP < 1000000000000L || CNP > 2999999999999L){
			JOptionPane.showMessageDialog(null, "CNP invalid");
			return;
		}
		if(address.equals(null)){
			JOptionPane.showMessageDialog(null, "Nu ati introdus adresa!");
			return;
		}
		
		if(cardNumber.equals(null)){
			JOptionPane.showMessageDialog(null, "Nu ati introdus numar buletin!");
			return;
		}
		if(validateLetters(name)==false){
			JOptionPane.showMessageDialog(null, "Nume invalid!");
			return;
		}
		if(this.getClientById(CNP) != null){
			JOptionPane.showMessageDialog(null, "Clientul exista deja in baza de date!");
			return;
		}
		add(new Client(CNP, name,cardNumber, address, ClientType.valueOf(type)));
	}
	
	public void update(Client c){
		this.clientGateway.update(c.getCnp(), c);
	}
	
	public Client getClientById(long id){
		Client c = null;
		ResultSet rs = clientGateway.getById(id);
		try {
			while(rs.next()){
				c = new Client(id,rs.getString("Name"),rs.getString("Card_number"),rs.getString("Address"),ClientType.valueOf(rs.getString("Type")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return c;
	}
	
	public void updateById(long id){
		this.update(this.getClientById(id));
	}
	
	public ArrayList<Client> listAll(){
		ArrayList<Client> c = new ArrayList<Client>();
		ResultSet rs = clientGateway.read();
		try {
			while(rs.next()){
				c.add(new Client(rs.getLong("CNP"),rs.getString("Name"),rs.getString("Card_number"),rs.getString("Address"),ClientType.valueOf(rs.getString("Type"))));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return c;
	}
	
	public ArrayList<Client> listIndividual(){
		ArrayList<Client> c = new ArrayList<Client>();
		ResultSet rs = clientGateway.read();
		try {
			while(rs.next()){
				if(this.getClientById(rs.getLong("CNP")).getcType().name().equals("INDIVIDUAL")){
					c.add(new Client(rs.getLong("CNP"),rs.getString("Name"),rs.getString("Card_number"),rs.getString("Address"),ClientType.valueOf(rs.getString("Type"))));
				}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return c;
	}
	
}
