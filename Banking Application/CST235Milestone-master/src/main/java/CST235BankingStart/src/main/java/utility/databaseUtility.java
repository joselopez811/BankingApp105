package CST235BankingStart.src.main.java.utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.gcu.cst135.ActivityGuide.CST235BankingStart.controller.Bank;
import edu.gcu.cst135.ActivityGuide.CST235BankingStart.model.Customer;

/**
 * 
 * @author max This class is used solely for Database connections
 *
 */
public class databaseUtility {
	/**
	 * Login method used to check credentials from the database
	 * 
	 * @param user is the username
	 * @param pass is the password
	 * @return returns a Customer object if username and password match the database
	 */
	public static Customer login(String user, String pass) {
		Bank bank = new Bank("Grand Canyon Credit Union");

		Customer customer = null;
		String sql = "Select first_name, last_name, customer_id FROM bank.customers WHERE username = ? AND password = ?";
		try (PreparedStatement statement = dbConnect.connection.prepareStatement(sql)) {
			statement.setString(1, user);
			statement.setString(2, pass);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				String first = result.getString("first_name");
				String last = result.getString("last_name");
				customer = new Customer(first, last);
			}
			if(customer == null) {
				System.out.println("Invalid username/password");
				bank.login();
			}
			if (customer.getFirstName().equalsIgnoreCase("admin") && customer.getLastName().equalsIgnoreCase("admin")) {
				bank.start();
			}
			return customer;
		} catch (SQLException e) {
			System.err.println("Invalid username/password");
			bank.login();
		}
		return null;
	}

	public static void createCustomer(String firstName, String lastName, String username, String password) {
		// INSERT INTO...
		String sql = "INSERT INTO bank.customers (first_name, last_name, username, password) " + "VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement statement = dbConnect.connection.prepareStatement(sql);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, username);
			statement.setString(4, password);
			System.out.println(statement);
			statement.executeUpdate();
			System.out.println("Customer added to database.\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ArrayList<Customer> getCustomers() {
		Customer customer = null;
		ArrayList<Customer> customers = new ArrayList<>();
		String sql = "Select first_name, last_name FROM bank.customers WHERE customer_id > 1";
		try (PreparedStatement statement = dbConnect.connection.prepareStatement(sql)) {
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				String first = result.getString("first_name");
				String last = result.getString("last_name");
				customer = new Customer(first, last);
				customers.add(customer);
			}
			return customers;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void updateCustomer(String column, String firstName, int customer_id) {
		// UPDATE ...
		customer_id += 2;
		String sql = "UPDATE bank.customers SET " + column + " = ? WHERE customer_id = ?";
		try {
			PreparedStatement statement = dbConnect.connection.prepareStatement(sql);
			statement.setString(1, firstName);
			statement.setInt(2, customer_id);
			statement.executeUpdate();
			System.out.println("Customer updated.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void deleteCustomer(int customer_id) {
		Scanner sc = new Scanner(System.in);
		String sql = "DELETE From bank.customers where customer_id = ?";
		try {
			PreparedStatement statement = dbConnect.connection.prepareStatement(sql);
			statement.setInt(1, customer_id);
			String confirm = null;
			System.out.println("Are you sure you want to delete account?(y/n) customer : " + getCustomer(customer_id).toString());
			switch(confirm = sc.nextLine().toLowerCase()) {
			case "y": statement.executeUpdate(); System.out.println("Customer deleted"); break;
			case "n": System.out.println("Customer not deleted"); break;
			default : deleteCustomer(customer_id); break;
			}
			
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid Selection");
		}
	}
	
	public static Customer getCustomer(int customer_id) {
		String sql = "Select first_name, last_name from bank.customers where customer_id = ?";
		try {
			PreparedStatement statement = dbConnect.connection.prepareStatement(sql);
			statement.setInt(1, customer_id);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				String first = result.getString("first_name");
				String last = result.getString("last_name");
				return new Customer(first, last);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No customer found with that ID");
		}
		return null;
	}

}
