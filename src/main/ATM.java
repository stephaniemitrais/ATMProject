                                                                                                                                  package main;

import java.util.Scanner;

import data.Bank;


public class ATM {

	private static Scanner input ;
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	    input = new Scanner(System.in);
		boolean atmIsRunning = true;
		
		while (atmIsRunning) {
    	   
    	   welcome();

        }
       


	}

	

	private static void welcome(){
		System.out.println("===========================");
	    System.out.println("Welcome to Mitrais Bank ATM");
	    System.out.println("===========================");

	    String accountNumberInput;
	    
	    String pinInput;
	    
	    
	    while (true) {
		    System.out.println("Enter account number:");
		    accountNumberInput = input.nextLine(); 
		    

		    if(accountNumberInput.length() != 6) {
		    	System.out.println("Account Number should have 6 digits length");
		    	continue;
		    } 
		    
	    	if (!accountNumberInput.chars().allMatch(Character::isDigit)) {
	    		System.out.println("Account Number should only contains numbers");
	    		continue;
	    	}
	    		
	    	break;
	    	
	    }
	    
	    
	    
	    while (true) {
    		System.out.println("Enter PIN:");
    	    pinInput = input.nextLine(); 
    	    
    	    if(pinInput.length() != 6) {
    	    	System.out.println("PIN should have 6 digits length");
    	    	continue;
    	    } 
    	    if (!pinInput.chars().allMatch(Character::isDigit)) {
    	    	System.out.println("PIN should only contains numbers");
    	    	continue;
    	    }
	    	
	    	break;
	    	
	    }
	    
        
    	if (Bank.getBank().getAccount(accountNumberInput, pinInput) == null) {
    		System.out.println("Invalid Account Number/PIN");
    		welcome();
    	} else {
    		transactionmenu();
    	}
    	
	    
	}
 
	      
	    
	
	private static void transactionmenu() {
		System.out.println("1. Withdraw");
		System.out.println("2. Fund Transfer");
		System.out.println("3. Exit");
		
		System.out.print("Please choose option [3] :");
		String result = input.nextLine();
		
		switch (result) {
		case "1"://Withdraw
			withdrawmenu();
			break;
		case "2"://Fund Transfer
			break;
		case "3"://exit
			break;
		default :
			welcome();
		}
	}
	
	private static void withdrawmenu() {
		System.out.println("1. $10");
		System.out.println("2. $50");
		System.out.println("3. $100");
		System.out.println("4. Other");
		System.out.println("5. Back");
		
		System.out.print("Please choose option[5]:");
		String money = input.nextLine();
		
		switch (money) {
		case "1": //$10
	
			break;
		case "2"://$50
	
			break;
		case "3"://$100
			
			break;
		case "4"://other withdraw
			break;
		case "5"://back
			transactionmenu();
			break;
		default://drop out 
			transactionmenu();
		}
		
		
	}
	
	private static void otherwithdrawmenu() {
		
		
		 while (true) {
				System.out.println("==============");
				System.out.println("Other Withdraw");
				System.out.println("==============");
				System.out.println("Enter amount to withdraw:");

				String money = input.nextLine();   
	    	    
		    	if (!money.chars().allMatch(Character::isDigit)) {
		    		System.out.println("Invalid ammount");
		    		continue;
		    	}
		    	
		    	
		    	break;
		    	
		 }
		 

		

		
		
	}
	
	/*
	
	private static void fundtransfermenu() {
		
		 while (true) {
	    		System.out.println("Please enter destination account and press enter to continue or \r\n"
	    				+ "press enter to go back to Transaction: ");
	    	    String accountInput = input.nextLine();
	    	    
	    	    
		    	if (!accountInput.chars().allMatch(Character::isDigit)) {
		    		System.out.println("Invalid Account");
		    		continue;
		    	}
		    	
		    	if (Bank.getBank().getAccount(accountInput) == null) {
		    		System.out.println("Invalid Account");
		    		continue;
		    	}
		    	
		    	
		    	break;
		    	
		 }
		 
		 while (true) {
	    		System.out.println("Please enter transfer amount and \r\n"
	    				+ "press enter to continue or \r\n"
	    				+ "press enter to go back to Transaction:");
	    	    String accountInput = input.nextLine();
	    	    
	    	    
		    	if (!accountInput.chars().allMatch(Character::isDigit)) {
		    		System.out.println("Invalid Account");
		    		continue;
		    	}
		    	
		    	if (Bank.getBank().getAccount(accountInput) == null) {
		    		System.out.println("Invalid Account");
		    		continue;
		    	}
		    	
		    	
		    	break;
		    	
		 }
		 

	}
	


	
	private static void summary() {

		System.out.println("your balance is");

	}
	
	*/
}

