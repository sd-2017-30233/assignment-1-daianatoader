package business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import data.Client;
import data.User;
import data.UserGateway;
import data.Client.ClientType;

public class UserService {
	private UserGateway UserGateway;
	
	public UserService(){
		this.UserGateway = new UserGateway();
	}
	
	public boolean checkId(int id){
		ResultSet rs = UserGateway.read();
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
	
	public void create(User u){
		this.UserGateway.create(u);
	}
	public static boolean validateLetters(String txt) {
	    String regx = "^[\\p{L} .'-]+$";
	    Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(txt);
	    return matcher.find();

	}
	
	public boolean validateUsernamePassword(String username, String password){
		ResultSet rs = UserGateway.read();
		try {
			while(rs.next()){
					if(username.equals(rs.getString("Username")) && password.equals(rs.getString("Password")))
						return true;
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return false;
	}
	
	public boolean checkAdmin(String username, String password){
		ResultSet rs = UserGateway.read();
		try {
			while(rs.next()){

					if(username.equals(rs.getString("Username")) && password.equals(rs.getString("Password"))&& 0==rs.getInt("Type") )
						return true;
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return false;
	}
	
	public void create(String name,String username, String password, int type){
		if(validateLetters(name)==false){
			JOptionPane.showMessageDialog(null, "Nume invalid!");
			return;
		}
		
		if(username.equals(null)){
			JOptionPane.showMessageDialog(null, "Username obligatoriu!");
			return;
		}
		
		if(password.equals(null)){
			JOptionPane.showMessageDialog(null, "Parola obligatorie!");
			return;
		}
		if(type==0){
			create(new User(name,username,password,0));
		}
		else{
			create(new User(name,username,password,1));
		}
		
	}
	
	public void update(int id,User u){
		this.UserGateway.update(id, u);
	}
	
	public User getUserById(int id){
		User u = null;
		ResultSet rs = UserGateway.getById(id);
		try {
			while(rs.next()){
				u = new User(rs.getString("Name"),rs.getString("Username"),rs.getString("Password"),rs.getInt("Type"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return u;
	}
	
	public int getIdByUsername(String username){
		int id = 0;
		ResultSet rs = UserGateway.getIdByUsername(username);
		try {
			while(rs.next()){
				id = rs.getInt("id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return id;
	}
	
	
	public void delete(int id){
		this.UserGateway.delete(id);
	}
	
	public List<User> listAll(){
		List<User> u = new ArrayList<User>();
		ResultSet rs = UserGateway.read();
		try {
			while(rs.next()){
				u.add(new User(rs.getString("Name"),rs.getString("Username"),rs.getString("Password"),rs.getInt("Type")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return u;
	}

}
