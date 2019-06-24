import java.util.Scanner;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;

public class BankingMain {

	private String name;
	Date today = new Date();
	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy");
	String date = DATE_FORMAT.format(today);
	
	static DecimalFormat format = new DecimalFormat("$0.00");
	
	static Savings savings = new Savings("Savings", 1000000.0, "191923", "John", "Doe");
	static Checkings checkings = new Checkings("Checkings", 100000, "991773", "John", "Doe");
	static Scanner sc = new Scanner(System.in);
	
	BankingMain(String name){
		this.name = name;
	}

	public static void main(String[] args)throws InterruptedException  {
		BankingMain bank = new BankingMain("GCU Credit Union");
		int option;
		do {
			Thread.sleep(4500);
			bank.displayMenu();
			option = sc.nextInt();			
			actionMenu(option);
			System.out.println("");
		} while(option != 9);
		
		
		
	}
	// outside of Main. |
	// 		    v
	private void displayMenu() {
		System.out.println("\n===================================");
		System.out.println(" MAIN MENU");
		System.out.println(name.toUpperCase());
		System.out.println("Hello " + checkings.getFirstName() + " " + checkings.getLastName());
		System.out.println("===================================");
		System.out.println("Pick an option: ");
		System.out.println("---------------------");
		System.out.println(" 1: : Deposit to Checking");
		System.out.println(" 2: : Deposit to Savings");
		System.out.println(" 3: : Write a Check");
		System.out.println(" 4: : Withdraw from Savings");
		System.out.println(" 5: : Get balance");
		System.out.println(" 6: : Close month");
		System.out.println("---------------------");
		System.out.println(" 9: : Exit");
		System.out.println("");
		
	}
	
	private static void actionMenu(int selection) {
		if(selection == 1)
			displayDepositChecking();
		if(selection == 2)
			displayDepositSaving();
		if(selection == 3)
			displayWithdrawCheckings();
		if(selection == 4)
			displayWithdrawSavings();
		if(selection == 5)
			displayBalanceScreen();
		if(selection == 6)
			doEndMonth();
		if(selection == 9)
			displayExitScreen();
		
	}
	private static void displayDepositChecking() {
		System.out.print("How much do you want to deposit into checking account: $");
		try {
		double amount = sc.nextDouble();
		checkings.doDeposit(amount);	
		}
		catch(InputMismatchException e) {
			sc.nextLine();
			System.out.println("Numbers only!");
			displayDepositChecking();
		}
	}
	private static void displayDepositSaving() {
		System.out.print("How much do you want to deposit into saving account: $");
		try {
		double amount = sc.nextDouble();
		savings.doDeposit(amount);
		}
		catch(InputMismatchException e) {
			sc.nextLine();
			System.out.println("Numbers only!");
			displayDepositSaving();
		}
	}
	private static void displayWithdrawCheckings() {
		
		System.out.println("You will have a $45.00 overdraft fee if check amount is greater"
				+ " than balance. " + format.format(checkings.getBalance()));
		System.out.print("Check amount: $");
		try {
		double amount = sc.nextDouble();
		checkings.doWithdraw(amount);
		}
		catch(InputMismatchException e) {
			sc.nextLine();
			System.out.println("Numbers only!");
			displayWithdrawCheckings();
		}
	}
	private static void displayWithdrawSavings() {
		
		System.out.print("Withdraw amount: $");
		try {
		double amount = sc.nextDouble();
		savings.doWithdraw(amount);
		}
		catch(InputMismatchException e) {
			sc.nextLine();
			System.out.println("Numbers only!");
			displayWithdrawSavings();
		}
	}
	private static void displayBalanceScreen() {
		System.out.print("Balance available: Checking: " + format.format(checkings.getBalance()) 
		+ "\tSaving: " + format.format(savings.getBalance()));
	}
	private static void doEndMonth() {
		if(savings.getBalance()>0){
			System.out.println("Interest earned: " + format.format(savings.getAnnualInterestRate()));
			savings.setBalance(savings.getAnnualInterestRate() + savings.getBalance());
		}
			if(savings.getBalance() < 200) {
			System.out.println("Your account is below the minimum balance of $200.00.\nYou are being charged a service fee of $25.00");
			savings.setBalance(savings.getBalance() - savings.getMinBalance());
		}
		System.out.println("Checking Balance: " + format.format(checkings.getBalance())
						+ "\nSaving Balance: " + format.format(savings.getBalance()));
		
	}
	private static void displayExitScreen() {
		System.out.println("Have a great day.\n GoodBye!");
	}
	
	
	
	
	
}
