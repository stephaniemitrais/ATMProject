package com.mitrais.atm.transaction;

import com.mitrais.atm.ATM;
import com.mitrais.atm.Session;


public abstract class Transaction {
	
	protected Transaction(Session session, ATM atm) {
		this.session = session;
		this.atm = atm;
	}
	
	protected ATM atm;
	    
	protected Session session;

	protected void reduceBalance(Double money) {
		String accountNo = session.getAccountNo();
		atm.getBank().getAccount(accountNo).setBalance(atm.getBank().getAccount(accountNo).getBalance()-money);
	}

	protected double getBalance() {

		double balance = atm.getBank().getAccount(session.getAccountNo()).getBalance();
		
		return balance;
	}
		
	abstract public void performTransaction() throws Exit;
	
	protected abstract String getTransactionName();
		
	public static Transaction makeTransaction(Session session, ATM atm) throws Exit       
	{
		
		System.out.println("================");
	    System.out.println("Transaction Menu");
	    System.out.println("================");
		
		System.out.println("1. Withdraw");
		System.out.println("2. Fund Transfer");
		System.out.println("3. Exit");
		
		System.out.print("Please choose option [3] :");
		String choice = atm.getConsole().getStringInput();
		
		switch (choice) {
			case "1"://Withdraw
				return new Withdrawal(session, atm);
			case "2"://Fund Transfer
				return new Transfer(session, atm);
			case "3"://exit
				throw new Exit();
			default:
				throw new Exit();
		}
		
	}
	
	public boolean doAnotherTransaction() {
		System.out.println("1. Transaction");
		System.out.println("2. Exit");
		
		System.out.print("Please choose option [2] :");
		String choice = atm.getConsole().getStringInput();
		
		if(choice.equals("1")) {
			return true;
		}
		else {
			return false;
		
		}
	}

	public static class Exit extends Exception
    {
        public Exit()
        {
            super("Exit back to Transaction Menu");
        }
    }
	

	protected boolean checkMoney(double money, String transaction, double maximumAmount) {


		if(money > 1000) {
			System.out.println("Maximum amount to withdraw is $1000");
			return false;
		}
		
		Double balance = getBalance();
		
		if(balance >= money) {
			return true;
		} else {
			System.out.println("Insufficient balance" + "$" + balance);
			return false;
		}
		

	}
	
}
	
