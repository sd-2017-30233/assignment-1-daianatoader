package data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static ConnectionFactory instance;
	
	public static ConnectionFactory getInstance()
	{
		if(instance==null)
		{
			instance=new ConnectionFactory();
		}
		return instance;
	}
	
	private ConnectionFactory()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException
	{		
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:mysql://localhost/bank?user=root&password=");
			return connection;	
	}

}

