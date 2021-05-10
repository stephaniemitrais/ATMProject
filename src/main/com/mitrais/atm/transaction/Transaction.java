package com.mitrais.atm.transaction;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import com.mitrais.atm.ATM;
import com.mitrais.atm.Login;


public abstract class Transaction {
	

	protected Transaction(Login loginUser, ATM atm) {
		this.accountNo = loginUser.getAccountNo();
		this.atm = atm;
		this.transactionDate = new Date();
		this.id = idSequence.incrementAndGet();
	}
	
	private static AtomicInteger idSequence = new AtomicInteger();
	
	protected int id;
	
	protected String accountNo;
	
	protected ATM atm;
	
	protected String transactionName;
	
	protected double transactionAmount;
	
	protected Date transactionDate;

	abstract public void performTransaction() throws Cancel;
	
	public abstract String getTransactionName();
	
	public abstract double getTransactionAmount();
	
	public int getId() {
		return this.id;
		
	}
	
	public String getAccountNo() {
		return this.accountNo;
	}
		
	public Date getTransactionDate()
	{
		return this.transactionDate;
		
	}
	
	public static class Cancel extends Exception
    {
        public Cancel()
        {
            super("Cancel and back to Transaction Menu");
        }
    }
	

	
}
	
