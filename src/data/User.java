package data;

public class User {
	private String name;
	private String username;
	private String password;
	private int type;
	
	public User(){}

	public User(String name, String username, String password, int type) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append(" Name: "+this.getName());
		result.append(" Username: "+this.getUsername());
		result.append(" Password: "+this.getPassword());
		result.append(" Type: "+this.getType());
		return result.toString();	
	}
	
}
