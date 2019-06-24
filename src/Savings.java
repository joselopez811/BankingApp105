import java.text.DecimalFormat;

public class Savings extends Account{
	
	static DecimalFormat format = new DecimalFormat("$0.00");
	
	private double serviceFee = 25;
	private double minBalance = 200;
	
	Savings(String account, double balance, String accountNumber, String first, String last) {
		super(account, balance, accountNumber, first, last);
	}
	
	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}
	public double getMinBalance() {
		return this.minBalance;
	}
	public void setServiceFee(double serviceFee){
		this.serviceFee = serviceFee;
	}
	public double getServiceFee() {
		return this.serviceFee;
	}

	public double getAnnualInterestRate() {
		double interest = this.getBalance() * 0.06 /12;
		return interest;
	}	

}
