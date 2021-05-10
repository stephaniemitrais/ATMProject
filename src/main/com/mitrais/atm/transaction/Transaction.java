package com.mitrais.atm.transaction;

import com.mitrais.atm.ATM;
import com.mitrais.atm.Login;


public abstract class Transaction {
	
	protected Transaction(Login loginUser, ATM atm) {
		this.accountNo = loginUser.getAccountNo();
		
		this.atm = atm;
	}
	
	
	protected String accountNo;
	
	protected ATM atm;
	
	protected String transactionName;
	
	protected double transactionAmount;

	abstract public void performTransaction() throws Cancel;
	
	public abstract String getTransactionName();
	
	public abstract double getTransactionAmount();
	
	public String getAccountNo() {
		return this.accountNo;
	}
		

	public static class Cancel extends Exception
    {
        public Cancel()
        {
            super("Cancel and back to Transaction Menu");
        }
    }
	

	
}
	
