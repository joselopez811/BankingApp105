package edu.gcu.cst135.ActivityGuide.CST235BankingStart.model;

import java.util.ArrayList;
import java.util.List;

import edu.gcu.cst135.ActivityGuide.CST235BankingStart.view.Menus;
/**
 * Saving Account class
 * @author max
 *
 */
public class Saving extends Account{
	
	private double minBalance;
	private double intRate;
	
	List<Transaction> trans = new ArrayList<>();
	/**
	 * constructor sets interest rate to .01
	 * and minimum balance 250.00
	 * @param account auto generated
	 * @param amount created on creation of account
	 */
	public Saving(String account, double amount) {
		super(account, amount);
		this.setMinBalance(250.00);
		this.intRate = .01;
	}
	/**
	 * Overrides doCredit from account
	 * decrements balance by value parameter amount
	 * adds Transaction
	 */
	public void doCredit(double amt) {
		this.setAccountBalance(this.getAccountBalance() - amt);
		addTransaction(amt, "Savings Withdrawal");
	}
	/**
	 * Overrides doDebit from account 
	 * increments balance by value parameter value
	 * adds Transaction
	 */
	public void doDebit(double amt) {
		this.setAccountBalance(this.getAccountBalance() + amt);
		addTransaction(amt, "Savings Deposit");
	}
	/**
	 * returns the balances
	 */
	public String toString() {
		return super.getAccountNumber() + "  " + super.getAccountBalance();
	}
	/**
	 * getter and setter
	 * @return minBalance
	 */
	public double getMinBalance() {
		return minBalance;
	}

	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}
	/**
	 * Overrides addTransaction to saving account
	 */
	@Override
	public void addTransaction(double amount, String description) {
		trans.add(new Transaction(this.getAccountNumber(), amount, description));
	}
	/**
	 * list transactions of saving account
	 */
	@Override
	public void ListTransaction() {
		Menus.printOut("########################");
		Menus.printOut(" Savings Transactions");
		Menus.printOut("########################");
		for(Transaction t : trans)
			System.out.println(t.toString());
		Menus.printOut("########################\n\n");
	}
	/**
	 * determines if account will gain interest
	 * calculates interest, displays it and 
	 * adds transaction
	 */
	@Override
	public void doEndOfMonth() {
		if (this.getAccountBalance() < this.minBalance) {
				Menus.printOut("Account below minumum balance - No interest accrued!");
		}
		else {
			Menus.printOut("Account above minumum balance - Interest accrued!");
			double interest = this.getAccountBalance() * intRate;
			Menus.printOut("Interest Earned : $" + interest);
			this.setAccountBalance(this.getAccountBalance() + interest);
			addTransaction(interest, "Interested Added");	
		}
		Menus.printBalance(this);
		ListTransaction();
	}
}
