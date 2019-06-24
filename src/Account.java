import java.text.DecimalFormat;

public class Account {
	
	static DecimalFormat format = new DecimalFormat("$0,000,000.00");
//	static NumberFormat money = new NunmberFormat()
	
	private String account;
	private String firstName, lastName;
	private double balance;
	private String accountNumber;
	
	Account(String account, double balance, String accountNumber, String firstName, String lastName) {
		this.account = account;
		this.balance = balance;
		this.firstName = firstName;
		this.lastName = lastName;
		this.setAccountNumber(accountNumber);
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public double getBalance(){
		return this.balance;
	}
	public void setBalance(double balance){
		this.balance = balance;
	}
	public String getAccount() {
		return this.account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void doWithdraw(double amount) {
		if(this.getBalance() > amount){
			this.setBalance(this.getBalance() - amount);
			System.out.println("Withdraw from " + this.account + " (" + this.getAccountNumber() + ")\nYour balance was " 
					+ format.format(this.getBalance()) + "\nWithdraw amount: $" + amount
					+ "\nBalance: " + format.format(this.getBalance()));
		}
		else
			System.out.println("Cannot Withdraw more than available: " + format.format(this.getBalance()));
		
	}
	public void doDeposit(double amount) {
		System.out.println("Deposit into " + this.account + " (" + this.getAccountNumber() + ")");
		System.out.println("Your balance was " + format.format(this.getBalance()));
		System.out.println("Deposit amount $" + amount);
		this.setBalance(amount + this.getBalance());
		System.out.println("New balance " + format.format(this.getBalance()));
	}
}
