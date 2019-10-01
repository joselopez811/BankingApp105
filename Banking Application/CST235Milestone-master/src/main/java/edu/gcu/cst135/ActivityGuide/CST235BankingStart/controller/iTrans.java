package edu.gcu.cst135.ActivityGuide.CST235BankingStart.controller;
/**
 * Used as a controller class
 * Interface used to add transactions
 * @author max
 *
 */
public interface iTrans {
	
	void addTransaction(double amount, String description);
	void ListTransaction();

}
