package edu.gcu.cst135.ActivityGuide.CST235BankingStart.model;

import java.util.ArrayList;
import java.util.List;

import edu.gcu.cst135.ActivityGuide.CST235BankingStart.view.Menus;
/**
 * Loan Account class
 * @author max
 *
 */
public class Loan extends Account {

	private double lateFee;
	private boolean isPaid = false;
	private double intRate;
	
	List<Transaction> trans = new ArrayList<>();
	
	public Loan(String account, double amount) {
		super(account, amount);
		this.lateFee =25.00;
		this.intRate = .02;
	}
	/**
	 * Overrides doCredit() to set proper balance
	 * switch the boolean isPaid to true 
	 * and add transaction to Loan payment
	 */
	public void doCredit(double amt) {
		this.setAccountBalance(this.getAccountBalance() - amt);
		isPaid = true;
		addTransaction(amt, "Loan Payment");
	}
	/**
	 * Overrides doDebit() to set proper balance
	 * adds transaction
	 */
	public void doDebit(double amt) {
		this.setAccountBalance(this.getAccountBalance() + amt);
		addTransaction(amt, "Loan Withdrawal");
	}
	/**
	 * returns account number and account balance
	 */
	public String toString() {
		return super.getAccountNumber() + "  " + super.getAccountBalance();
	}
	/**
	 * Overrides addTransaction()
	 */
	@Override
	public void addTransaction(double amount, String description) {
		trans.add(new Transaction(this.getAccountNumber(), amount, description));
	}
	/**
	 * Overrides ListTransaction()
	 * prints transactions that are 
	 * associated to the Loan account
	 */
	@Override
	public void ListTransaction() {
		Menus.printOut("########################");
		Menus.printOut(" Loan Transactions");
		Menus.printOut("########################");
		for(Transaction t : trans)
			System.out.println(t.toString());
		Menus.printOut("########################\n\n");
	}
	/**
	 * Overrides doEndOfMonth()
	 * calculates interest
	 * adds transaction
	 * adds late fee if isPaid == false
	 * and adds that transaction if so
	 */
	@Override
	public void doEndOfMonth() {
		double interest = this.getAccountBalance() * intRate;
		Menus.printOut("Interest charged : $" + interest);
		this.setAccountBalance(this.getAccountBalance() + interest);
		addTransaction(interest, "Monthly Interest Added");
		if (!isPaid) {
			Menus.printOut("No Payment this month - Late fee added!");
			this.setAccountBalance(this.getAccountBalance() + lateFee);
			addTransaction(lateFee, "Late Fee Added");
		}
		Menus.printBalance(this);
		ListTransaction();
	}
}
