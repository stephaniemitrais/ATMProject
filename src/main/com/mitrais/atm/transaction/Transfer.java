package com.mitrais.atm.transaction;

import java.util.Scanner;

import com.mitrais.atm.ATM;
import com.mitrais.atm.Login;

public class Transfer extends Transaction {
	
    public Transfer(Login loginUser, ATM atm) {
		super(loginUser, atm);
		// TODO Auto-generated constructor stub
	}
    
	@Override
	public String getTransactionName() {
		return "transfer";
	}

    @Override
	public double getTransactionAmount() {
		return transferredAmount;
	}

    private double transferredAmount;
    
	private static Scanner input = new Scanner(System.in);  
	
	@Override
	public void performTransaction() throws Cancel {
		

		String destinationAccountInput;
		String amountInput = null;
		
		boolean transferIsProcessed = false;
		
		
		transfer:
		while(true) {
			while (true) {
		    		System.out.println("Please enter destination account and press enter to continue or \r\n"
		    				+ "press enter to go back to Transaction: ");
		    	    destinationAccountInput = input.nextLine();
		    	    
		    	    
		    	    if (destinationAccountInput.length() == 0) {
		    	    	break transfer;
		    	    }
		    	    
			    	if (destinationAccountInput.length() != 0 && !destinationAccountInput.chars().allMatch(Character::isDigit)) {
			    		System.out.println("Invalid Account");
			    		continue;
			    	}
			    	
			    	if (!atm.getTransactionService().destinationAccountIsValid(super.accountNo)) {
			    		System.out.println("Invalid Account");
			    		continue;
			    	}
			    	
			    	break;
			 }
			
			
			 while (true) {
		    		System.out.println("Please enter transfer amount and press enter to continue \r\n"
		    				+ "or press enter to go back to Transaction:");
		    	    amountInput = input.nextLine();
		    	    
		    	    if (amountInput.length() == 0) {
		    	    	break transfer;
		    	    }
		    	    
			    	if (!amountInput.chars().allMatch(Character::isDigit)) {
			    		System.out.println("Invalid Amount");
			    		continue;
			    	}
			    	
			    	if(atm.getTransactionService().checkMoney(super.accountNo,Double.valueOf(amountInput), getTransactionName(), 1000))
			    	{
			    		transferIsProcessed = true;
			    		break transfer;

			    	} else {
			    		continue;
			    	}

			    	
			 }
		 
		}
		
		
		if(transferIsProcessed) {
			this.transferredAmount = Double.valueOf(amountInput);
			atm.getTransactionService().fundTransfer(this, super.accountNo, destinationAccountInput, Double.parseDouble(amountInput));
			summary(destinationAccountInput, Double.valueOf(amountInput), String.valueOf(System.currentTimeMillis()));
			
		} else {
			
			throw new Cancel();
		}

	
		
		
	}
	

	
	private void summary(String destinationAccount, double money, String referenceNo) {
		System.out.println("=====================");
		System.out.println("Fund Transfer Summary");
		System.out.println("=====================");
		System.out.println("Destination Account: " + destinationAccount);
		System.out.println("Transfer Amount :" + money );
		System.out.println("Reference Number :" + referenceNo);
		System.out.println("Balance:" + "$" + atm.getTransactionService().getBalance(super.accountNo));
		
	}
	

}
