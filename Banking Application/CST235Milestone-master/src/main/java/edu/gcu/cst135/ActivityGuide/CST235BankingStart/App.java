package edu.gcu.cst135.ActivityGuide.CST235BankingStart;

import CST235BankingStart.src.main.java.utility.dbConnect;
import edu.gcu.cst135.ActivityGuide.CST235BankingStart.controller.Bank;
/**
 * Starts the application
 * @author max
 *
 */
public class App 
{
	// Starting point for banking application
    public static void main( String[] args )
    {
    	// Create an object and set the name
        Bank bank = new Bank("Grand Canyon Credit Union");
        
        // Start the banking application
        bank.login();
        dbConnect.close();
    }
}
