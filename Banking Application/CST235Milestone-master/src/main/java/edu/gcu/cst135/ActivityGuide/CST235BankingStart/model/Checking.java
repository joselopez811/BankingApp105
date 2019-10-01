package edu.gcu.cst135.ActivityGuide.CST235BankingStart.model;

import java.util.ArrayList;
import java.util.List;

import edu.gcu.cst135.ActivityGuide.CST235BankingStart.view.Menus;
/**
 * Checking Account
 * @author max
 *
 */
public class Checking extends Account {

	private double overDraft;
	
	List<Transaction> trans = new ArrayList<>();
	/**
	 * Constructor
	 * sets overdraft to 25
	 * @param account account number auto generated
	 * @param amount amount specified on creation of account
	 */
	public Checking(String account, double amount) {
		super(account, amount);
		this.overDraft = 25.00;
	}
	/**
	 * Overides doCredit from Account 
	 * to apply overdraft fee if applies
	 * and add transaction for checking
	 */
	public void doCredit(double amt) {
		if (this.getAccountBalance() < amt) {
			Menus.printOut("Insufficient funds! Overdraft fee of $" + overDraft + " applied");
			this.setAccountBalance(this.getAccountBalance() - overDraft);
			addTransaction(overDraft, "Overdraft fee applied");			
		}
		this.setAccountBalance(this.getAccountBalance() - amt);
		addTransaction(amt, "Checking Withdrawal");

	}
	/**
	 * overrides doDebit from account
	 * to add transaction for checking
	 */
	public void doDebit(double amt) {
		this.setAccountBalance(this.getAccountBalance() + amt);
		addTransaction(amt, "Checking Deposit");

	}
	/**
	 * Getters  and Setters
	 * @return the overdraft fee
	 */
	public double getOverDraft() {
		return overDraft;
	}

	public void setOverDraft(double overDraft) {
		this.overDraft = overDraft;
	}
	/**
	 * returns the super class toString() method
	 */
	public String toString() {
		return super.getAccountNumber() + "  " + super.getAccountBalance();
	}
	/**
	 * Overrides addTransaction()
	 * to fit checking account
	 */
	@Override
	public void addTransaction(double amount, String description) {
		trans.add(new Transaction(this.getAccountNumber(), amount, description));
	}
	/**
	 * Overrides ListTransaction() to 
	 * display all checking transactions
	 */
	@Override
	public void ListTransaction() {
		Menus.printOut("########################");
		Menus.printOut(" Checking Transactions");
		Menus.printOut("########################");
		for(Transaction t : trans)
			System.out.println(t.toString());
		Menus.printOut("########################\n\n");
	}
	/**
	 * Overrides doEndMonth() from account
	 * if the balance is overdrafted by the 
	 * end of the month an extra fee will apply
	 */
	@Override
	public void doEndOfMonth() {
		// Nothing to do at this time
		Menus.printBalance(this);
		ListTransaction();	
	}

}
