package CST235BankingStart.src.main.java.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnect {
	private static String amazonURL = "jdbc:mysql://cst-235-mysql.csmba86dsbyo.us-east-2.rds.amazonaws.com:3306/bank";
	private static String dbURL =  "jdbc:mysql://localhost:3306";
	private static String user = "admin";
	private static String pass = "password";
	
	public static Connection connection;
	
	static {
		try {
			connection = DriverManager.getConnection(dbURL, user, pass);
			System.err.println("Connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Did not connect!");
			e.printStackTrace();
		}
	}

	public static void close() {
		// TODO Auto-generated method stub
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
