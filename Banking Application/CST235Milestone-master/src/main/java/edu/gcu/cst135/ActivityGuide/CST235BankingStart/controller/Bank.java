package edu.gcu.cst135.ActivityGuide.CST235BankingStart.controller;

import java.util.ArrayList;
import java.util.List;

import CST235BankingStart.src.main.java.utility.databaseUtility;
import edu.gcu.cst135.ActivityGuide.CST235BankingStart.model.Customer;
import edu.gcu.cst135.ActivityGuide.CST235BankingStart.view.Menus;
/**
 * Bank Class
 * Used as a controller class
 * @author max
 *
 */
public class Bank {
	
	// Class attributes
	private String name; // Hold the bank name
	private static int currentCustomer;  // Hold the current (picked) customer 
	List<Customer> customers = new ArrayList<>(); // Hold a list of ALL customers
	
	// Constructor
	public Bank(String name) {
		this.name = name;	
	}
	
	//Login Page
	public void login() {
		
		Menus.login();
		String username = Menus.userStrInput("Enter username: "); 
		String password = Menus.userStrInput("Enter password: ");
		customers.add(databaseUtility.login(username, password));
		if(customers.get(customers.size()-1).getLastName().equalsIgnoreCase("admin"))
			start();
		if (customers.get(customers.size()-1) != null ) {
			currentCustomer = customers.size()-1;
			processCustomerMenu(Menus.viewCustomerMenu(customers.get(currentCustomer), name));
		}
		else
			login();
	}
	
	// Starting point
	public void start() { 
		int option; 
		// Keep going until they select a valid option
		do {
			option = Menus.custMenu();
		}while (option < 0 || option > 4);
		processCustMenu(option);
	}
	
	// Process what menu item the user picked
	private void processCustMenu(int option) {
		switch (option) {
			case 1: createCustomer(); break;
			case 2: pickCustomer(); break;
			case 3: updateCustomer(); break;
			case 4: deleteCustomer(); break;
			default : start();
		}
	}
	
	private void deleteCustomer() {
		
		currentCustomer = pickCustomerFromList();
		databaseUtility.deleteCustomer(currentCustomer + 2);
		start();
	}

	private void updateCustomer() {
		currentCustomer = pickCustomerFromList();
		switch(Menus.updateMenu()) {
		case 1: String firstName = Menus.userStrInput("Enter new first name: ");
				databaseUtility.updateCustomer("first_name", firstName, currentCustomer);
				break;
		case 2: String lastName = Menus.userStrInput("Enter new last name: ");
				databaseUtility.updateCustomer("last_name", lastName, currentCustomer);
				break;
		case 3: String username = Menus.userStrInput("Enter new username: ");
				databaseUtility.updateCustomer("username", username, currentCustomer);
				break;
		case 4: String password = Menus.userStrInput("Enter new password: ");
				databaseUtility.updateCustomer("password", password, currentCustomer);
				break;
		case 0: updateCustomer(); break;
		default: start();
		}
		
	}

	private int pickCustomerFromList() {
		customers.clear();
		for(Customer c : databaseUtility.getCustomers()) {
			customers.add(c);
		}
		do {
			// CST235 TASK: REMOVE THE LIST ARGUMENT
			currentCustomer = Menus.pickCustomerMenu(customers) - 1 ;
			return currentCustomer;
		}while (currentCustomer > (customers.size() - 1));
		
	}

	// Create a customer and add to the customers list
	private void createCustomer() {
		
		String firstName = Menus.userStrInput("Enter the customer's first name: "); 
		String lastName = Menus.userStrInput("Enter the customer's last name: ");
		String username = Menus.userStrInput("Enter a username: ");
		String password = Menus.userStrInput("Enter a password: ");
		// CST235 TASK: REQUIRE THE CUSTOMER TO ENTER A USERNAME
		// CST235 TASK: REQUIRE THE CUSTOMER TO ENTER A PASSWORD
		databaseUtility.createCustomer(firstName, lastName, username, password);
		customers.add(new Customer(firstName, lastName));
		// Display the customer menu
		processCustMenu(Menus.custMenu());
		
	}
	
	// Process the selected customer to use for banking transactions
	private void pickCustomer() {
		currentCustomer = pickCustomerFromList();
		
		// If the use picked -1 (e.g. 0-1) send them back to the beginning
		if (currentCustomer == -1)
			start();
		else {
			processCustomerMenu(Menus.viewCustomerMenu(customers.get(currentCustomer), name));
		}
	}	
	
	// Process the bank transaction menu option
	private void processCustomerMenu(int parseInt) {

		switch(parseInt) {
		case 1: 
			viewDepositChecking();viewBalances();
			break;
		case 2: 
			viewDepositSavings();viewBalances();
			break;
		case 3: 
			viewWithdrawalChecking();viewBalances();
			break;
		case 4: 
			viewWithdrawalSavings();viewBalances();
			break;
		case 5: 
			viewBalances();
			break;
		case 6: 
			viewLoanPayment();viewBalances();
			break;
		case 7: 
			viewEndOfMonth();viewBalances();
			break;  
		default: 
			// Back to the starting point and reset current customer
			if(customers.get(currentCustomer).getLastName().equalsIgnoreCase("admin")) {
				currentCustomer = 0;
				processCustMenu(Menus.custMenu());
			}
			else 
				login();
		}
	}

	// Execute each end of month methods in each class
	private void viewEndOfMonth() {
		customers.get(currentCustomer).getSaving().doEndOfMonth();
		customers.get(currentCustomer).getChecking().doEndOfMonth();
		customers.get(currentCustomer).getLoan().doEndOfMonth();		
	}

	// display and process loan payment
	private void viewLoanPayment() {
		customers.get(currentCustomer).getLoan().doCredit(Menus.userDblInput("How much to pay on your loan?"));
	}
	
	// display and process savings withdraw
	private void viewWithdrawalSavings() {
		customers.get(currentCustomer).getSaving().doCredit(Menus.userDblInput("How much to withdraw from savings?"));	
	}

	// display and process checking withdraw
	private void viewWithdrawalChecking() {
		customers.get(currentCustomer).getChecking().doCredit(Menus.userDblInput("What is you check amount to withdraw from checking?"));			
	}

	// display and process savings deposit
	private void viewDepositSavings() {
		customers.get(currentCustomer).getSaving().doDebit(Menus.userDblInput("How much to deposit into savings?"));	
	}
	// display and process checking deposit	
	private void viewDepositChecking() {
		customers.get(currentCustomer).getChecking().doDebit(Menus.userDblInput("How much to deposit into checking?"));	
	}
	
	// display balances
	private void viewBalances() {
		Menus.viewBalances(customers.get(currentCustomer));
		processCustomerMenu(Menus.viewCustomerMenu(customers.get(currentCustomer), name));	
	}

}
