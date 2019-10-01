package edu.gcu.cst135.ActivityGuide.CST235BankingStart.view;

import java.util.List;
import java.util.Scanner;

import edu.gcu.cst135.ActivityGuide.CST235BankingStart.model.Account;
import edu.gcu.cst135.ActivityGuide.CST235BankingStart.model.Customer;
/**
 * Menu class is used as a View
 * @author max
 *
 */
// These are all the menus for the application
public class Menus {
	
	// Create a scanner for reuse across all methods
	public static Scanner sc = new Scanner(System.in);
	
	public static void login() {

		System.out.println("***************************");
		System.out.println("      Log In");
		System.out.println("***************************");

	}
	
	/**
	 * Customer menu
	 * @return Opening menu for the application
	 */
		public static int custMenu() {
		int option = 0;
		try {
			System.out.println("***************************");
			System.out.println("  BANK CUSTOMER MENU  ");
			System.out.println("***************************");
			System.out.println(" 1. Create a customer");
			System.out.println(" 2. Pick a customer");
			System.out.println(" 3. Update customer information");
			System.out.println(" 4. Delete a customer");
			System.out.println("---------------------------");
			System.out.println(" 0. Exit");
			System.out.println("***************************");
			System.out.println("What is your choice?");
			String opt = sc.nextLine();
			option = Integer.parseInt(opt);
		} catch (Exception e) { // Can we parse the user's entry?
			System.out.println("Bad customer menu input. Try again!");
			option = -1;
		}
		return option;
	}
	
	/**
	 * Inserts list of Customer objects
	 * @param custs list of Customer objects
	 * @return Picking a customer for banking transaction menu
	 */
	// CST235 TASK: REMOVE THE LIST PARAMETER
	// 
	public static int pickCustomerMenu(List<Customer> custs) {
		int number;
		int cust = 0;
		try {
			number = 1;
			System.out.println("***************************");
			System.out.println("  PICK CUSTOMER MENU  ");
			System.out.println("***************************");
			
			// CST235 TASK: CUSTOMER LIST MUST COME FROM THE DB
			for (Customer c : custs) {
				System.out.println(number + ". " + c.toString());
				number++;
			}
			
			System.out.println("---------------------------");
			System.out.println(" 0. Exit");
			System.out.println("***************************");
			System.out.println("Who is your choice?");
			cust = sc.nextInt();
			sc.nextLine();
		} catch (Exception e) { // Can we parse the user's entry?
			System.out.println("Bad customer select. Try again!");
		}
		
		return cust;
	}
	/**
	 * Takes a string and prints it out
	 * @param message displays String input
	 * @return user input
	 */
	// 
	public static String userStrInput(String message) {
		System.out.println(message);
		return sc.nextLine();
	}
	public static int userIntInput(String message) {
		int selection = 0;
		try {
			System.out.println(message);
			String input = sc.nextLine();
			selection = Integer.parseInt(input);
		} catch (Exception e) {
			System.out.println("Wrong integer input\n");
			selection = -1;
		}
		return selection;
	}
	/**
	 * Takes a string and parses into double
	 * @param message string input
	 * @return amount inputed into double
	 */
		public static double userDblInput(String message) {	
		double amount = 0.0;
		try {
			System.out.println(message);
			String input = sc.nextLine();
			amount = Double.parseDouble(input);
		} catch (Exception e) { // Can we parse the user's entry?
			System.out.println("Wrong double input\n");
			amount = -1.0;
		}
		return amount;
	}
	/**
	 * Banking transaction menu
	 * @param c Customer object
	 * @param name name of bank object
	 * @return input option selected
	 */
	// Banking transaction menu
	public static int viewCustomerMenu(Customer c, String name) {

		try {
			String option;
			do {
				System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				System.out.println("                MAIN MENU");
				System.out.println("                " + name.toUpperCase());
				System.out.println("Hello " + c.getFirstName().toUpperCase() + " " + c.getLastName().toUpperCase());				
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				System.out.println("Pick an option: ");
				System.out.println("-----------------------");
				System.out.println(" 1: Deposit to Checking");
				System.out.println(" 2: Deposit to Savings");
				System.out.println(" 3: Withdraw from Checking");
				System.out.println(" 4: Withdraw from Savings");			
				System.out.println(" 5: Get balance");
				System.out.println(" 6: Make Loan Payment");
				System.out.println(" 7: Get monthly statement");
				System.out.println("------------------------");
				System.out.println(" 9: : Logout");
				option = sc.nextLine();
				return Integer.parseInt(option);
			} while (Integer.parseInt(option) != 9);
		} catch (Exception e) { // Can we parse the user's entry?
			System.out.println("Wrong transaction menu input\n");
			viewCustomerMenu(c, name);
		}
		return 0;
	}
	/**
	 * Balance displays for each account class type
	 * @param cust customer object
	 */
	// Balance displays for each account class type
	public static void viewBalances(Customer cust) {
		System.out.println("------------------------");
		System.out.println("CUSTOMER BALANCES");
		System.out.println("------------------------");
		System.out.println("CHECKING : \t" + cust.getChecking().getAccountNumber() + " \t $" + cust.getChecking().getAccountBalance() );
		System.out.println("SAVING :   \t" + cust.getSaving().getAccountNumber() + " \t $" + cust.getSaving().getAccountBalance() );
		System.out.println("LOAN :     \t" + cust.getLoan().getAccountNumber() + " \t $" + cust.getLoan().getAccountBalance() );
		System.out.println("------------------------");
	}
	/**
	 * Formatted syso method
	 * @param message input String wanted print out formatted with 
	 */
	public static void printOut(String message) {
		System.out.println(" > " + message);
	}
	/**
	 * Prints balance of the given account object
	 * @param <T> Generic Method for objects of Checking Saving or Loan
	 * @param obj Formatted balance printing method
	 */
	// 
	public static <T> void printBalance(T obj){
		System.out.println(((Account) obj).getAccountNumber() + " : $" + ((Account) obj).getAccountBalance());
	}

	public static int updateMenu() {
		String option = null;
		do {
		System.out.println("------------------------");
		System.out.println("   UPDATE MENU");
		System.out.println("------------------------");
		System.out.println("1.) First Name");
		System.out.println("2.) Last Name");
		System.out.println("3.) Username");
		System.out.println("4.) Password");
		System.out.println("---------------------------");
		System.out.println(" 0. Exit");
		System.out.println("***************************");
		System.out.println("What would you like to update?");
		try {
		option = sc.nextLine();
		return Integer.parseInt(option);
		}
		catch (Exception e) {
			e.printStackTrace();
			updateMenu();
		}
		} while(Integer.parseInt(option) != 0);
		return 0;
	}

	
}
