import java.text.DecimalFormat;

public class Checkings extends Account{
	
	static DecimalFormat format = new DecimalFormat("$0.00");

	
	private double overDraft = 45;
	private String type = "Checking";
	
	Checkings(String account, double balance, String accountNumber, String first, String last) {
		super(account, balance, accountNumber, first, last);
	}

	public double getOverDraft() {
		return overDraft;
	}

	public void setOverDraft(double overDraft) {
		this.overDraft = overDraft;
	}
	
	public void doWithdraw(double amount) {
		System.out.println("Withdraw from " + this.type + " (" + this.getAccount() + ")");
		System.out.println("Your balance was " + format.format(this.getBalance()));
		System.out.println("Withdraw amount: $" + amount);
		this.setBalance(this.getBalance() - amount);
		if(this.getBalance() < 0) {
			System.out.println("OVERDRAFT NOTICE: $45.00 fee assessed!");
			this.setBalance(this.getBalance() - 45);
		}
		System.out.println("Balance: " + format.format(this.getBalance()));
	}
}
