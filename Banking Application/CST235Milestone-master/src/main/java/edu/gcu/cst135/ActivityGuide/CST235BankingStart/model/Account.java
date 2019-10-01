package edu.gcu.cst135.ActivityGuide.CST235BankingStart.model;

import edu.gcu.cst135.ActivityGuide.CST235BankingStart.controller.iTrans;

/**
 * Account Class abstract implements iTrans
 * holdes accountNumber and accountBalance
 * defines simple method to debit and creidt
 * accounts
 * @author max
 *
 */
public abstract class Account implements iTrans {
	private String accountNumber;
	private double accountBalance;
	
	public abstract void doEndOfMonth();
	/**
	 * 
	 * @return returns account number
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	/**
	 * decreases the balance of a given account
	 * by the amount defined in the parameter
	 * @param amt the amount being credited
	 */
	public void doCredit(double amt) {			
		this.setAccountBalance(this.getAccountBalance() - amt);
	}
	/**
	 * Increases the balance of a given account
	 * by the amount defined in the parameter
	 * @param amt the amount being added to account
	 */
	public void doDebit(double amt) {
		this.setAccountBalance(this.getAccountBalance() + amt);
	}
	/**
	 * Formats the variables account number and account balance
	 * in the given structure
	 */
	public String toString() {
		return accountNumber + " $" + accountBalance;
	}
	/**
	 * Constructor
	 * @param accountNumber auto generated
	 * @param accountBalance auto generated
	 */
	public Account(String accountNumber, double accountBalance) {
		
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
	}
}
