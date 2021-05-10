package com.mitrais.atm.transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.mitrais.atm.ATM;
import com.mitrais.atm.Login;

public class Withdrawal extends Transaction {

    public Withdrawal(Login loginUser, ATM atm) {
		super(loginUser, atm);
		// TODO Auto-generated constructor stub
	}


	@Override
	public String getTransactionName() {
		return "withdraw";
	}

	
    @Override
	public double getTransactionAmount() {
		// TODO Auto-generated method stub
		return this.withdrawnAmount;
	}


	private double withdrawnAmount;

	private static Scanner input = new Scanner(System.in);  
	
	@Override
	public void performTransaction() throws Cancel {

		boolean amountIsValid = false;
		
		while(!amountIsValid)
		{
			System.out.println("=========");
		    System.out.println("Withdraw");
		    System.out.println("=========");
			System.out.println("1. $10");
			System.out.println("2. $50");
			System.out.println("3. $100");
			System.out.println("4. Other");
			System.out.println("5. Back");
			
			System.out.print("Please choose option[5]:");
			

			
			String money = input.nextLine();
			
			switch (money) {
			case "1": //$10
				amountIsValid = atm.getTransactionService().checkMoney(super.accountNo, 10, getTransactionName(), 1000);
				withdrawnAmount = Double.valueOf(10);
				break;
				
			case "2"://$50
				amountIsValid = atm.getTransactionService().checkMoney(super.accountNo, 10, getTransactionName(), 1000);
				withdrawnAmount = Double.valueOf(50);
				break;
				
			case "3"://$100
				amountIsValid = atm.getTransactionService().checkMoney(super.accountNo, 10, getTransactionName(), 1000);
				withdrawnAmount = Double.valueOf(100);
				break;
				
			case "4"://other amount
				double amount = 0;
				try {
					amount = otherWithdrawnAmount();
					amountIsValid = atm.getTransactionService().checkMoney(super.accountNo, amount, getTransactionName(), 1000);
					withdrawnAmount = amount;
					
					
				} catch (Cancel e) {
		
				}

				break;
				
			case "5"://back
				throw new Cancel();
				
			default://exit
				throw new Cancel();
			}
			
		}
		
		atm.getTransactionService().withdraw(this, super.accountNo, withdrawnAmount);

		summary(withdrawnAmount);
		
	}
	

		
	private Double otherWithdrawnAmount() throws Cancel {
		
		 while (true) {
				System.out.println("==============");
				System.out.println("Other Withdraw");
				System.out.println("==============");
				System.out.println("Enter amount to withdraw:");

				String money = input.nextLine();
	    	    

				
				if(money.length() == 0) {
					throw new Cancel();
				}
				
		    	if (!money.chars().allMatch(Character::isDigit)) {
		    		System.out.println("Invalid ammount");
		    		continue;
		    	} 
		    	
		    	return Double.valueOf(money);
		    	
		    	
		 }
		 

		
	}
	
	
	private void summary(Double withdrawnMoney) throws Cancel {
		LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formatDateTime = now.format(formatter);

                
		System.out.println("=======");
		System.out.println("Summary");
		System.out.println("=======");
		System.out.println("Date: " + formatDateTime);
		System.out.println("Withdraw: " + "$" + withdrawnMoney);
		System.out.println("Balance: " + "$" + atm.getTransactionService().getBalance(super.accountNo));

	}
	
	
}
