                                                                                                                                  package main;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import data.Bank;


public class ATM {

	private static Scanner input ;
	
	private static String loginAccount;
	
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

	    String accountNumberInput = null;
	    
	    String pinInput = null;
	    
	    boolean isAccountValid = false;
	    
	    while (!isAccountValid) {
	    	System.out.println("Enter account number:");
    		accountNumberInput = input.nextLine(); 
    		    
    		isAccountValid = validateAccountNo(accountNumberInput);
    	    		
    	    
	    }
	    
	    boolean isPINValid = false;
	    
	    while (!isPINValid) {
    		System.out.println("Enter PIN:");
    	    pinInput = input.nextLine(); 
    	       	    
    	    isPINValid = validatePIN(pinInput);
	    	
	    }
	    
        
    	if (Bank.getBank().getAccount(accountNumberInput, pinInput) == null) {
    		System.out.println("Invalid Account Number/PIN");

    		return;
    		
    	} else {
    		loginAccount = accountNumberInput;
    		
    		transactionmenu();
    	}
    	

	    
	}
 
	public static boolean validateAccountNo(String accountNumberInput) {
		
	    if(accountNumberInput.length() != 6) {
	    	System.out.println("Account Number should have 6 digits length");
	    	return false;
	    } 
	    
    	if (!accountNumberInput.chars().allMatch(Character::isDigit)) {
    		System.out.println("Account Number should only contains numbers");
    		return false;
    	}
 	    return true;
	}
	    
	
	public static boolean validatePIN(String pinInput) {
		
		if(pinInput.length() != 6) {
 	    	System.out.println("PIN should have 6 digits length");
 	    	return false;
 	    } 
 	    if (!pinInput.chars().allMatch(Character::isDigit)) {
 	    	System.out.println("PIN should only contains numbers");
 	    	return false;
 	    }
 	    
 	    return true;
	}
	    
	
	private static void transactionmenu() {
		try {
			Runtime.getRuntime().exec("cls");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			fundtransfermenu();
			break;
		case "3"://exit
			break;
		default :
			welcome();
		}
	}
	
	private static void withdrawmenu() {
		
		while(true)
		{
			System.out.println("1. $10");
			System.out.println("2. $50");
			System.out.println("3. $100");
			System.out.println("4. Other");
			System.out.println("5. Back");
			
			System.out.print("Please choose option[5]:");
			String money = input.nextLine();
			
			switch (money) {
			case "1": //$10
				if(checkMoney(10)) {
					withdraw(Double.valueOf(10));
				} else {
					continue;
				}
				break;
			case "2"://$50
				if(checkMoney(50)) {
					withdraw(Double.valueOf(10));
				}  else {
					continue;
				}
				break;
			case "3"://$100
				if(checkMoney(100)) {
					withdraw(Double.valueOf(10));
				} else {
					continue;
				}
				break;
			case "4"://other withdraw
				otherwithdrawmenu();
				break;
			case "5"://back
				transactionmenu();
				break;
			default://exit
				transactionmenu();
			}
			
			break;
		}

		
		
	}


	
	private static void withdraw(double money) {

		Bank.getBank().getAccount(loginAccount).setBalance(Bank.getBank().getAccount(loginAccount).getBalance()-money);
		System.out.println("Withdraw successful");
		summary(money);
	
	}
	
	private static boolean checkMoney(double money) {

		Double balance = Bank.getBank().getAccount(loginAccount).getBalance();
		if(money > 1000) {
			System.out.println("Maximum amount to withdraw is $1000");
			return false;
		}
		
		if(balance >= money) {
			return true;
		} else {
			System.out.println("Insufficient balance" + "$" + balance);
			return false;
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
		    	
		    	if(checkMoney(Double.parseDouble(money)))
		    	{
					withdraw(Double.parseDouble(money));
		    		break;
		    	} else {
		    		continue;
		    	}
		    	
		    	
		 }
		 

		
	}
	
	
	private static void fundTransfer(String destinationAccount, double money, String referenceNo) {

		Bank.getBank().getAccount(loginAccount).setBalance(Bank.getBank().getAccount(loginAccount).getBalance()-money);
		System.out.println("Withdraw successful");
		fundSummary(destinationAccount, money, referenceNo);
	
	}
	
	private static void fundSummary(String destinationAccount, double money, String referenceNo) {
		System.out.println("=====================");
		System.out.println("Fund Transfer Summary");
		System.out.println("=====================");
		System.out.println("Destination Account: " + destinationAccount);
		System.out.println("Transfer Amount :" + money );
		System.out.println("Reference Number :" + referenceNo);
		System.out.println("Balance:" + "$" + Bank.getBank().getAccount(loginAccount).getBalance());
		System.out.println();
		System.out.println("1. Transaction");
		System.out.println("2. Exit");
		
		System.out.print("Please choose option [2] :");
		
		String option = input.nextLine();
		
		switch (option) {
		case "1"://Transaction
			transactionmenu();
			break;
		case "2"://exit
			break;
		default :
			welcome();
		}
	}
	
	private static void fundtransfermenu() {
		
		String accountInput;
		String amountInput;
		
		transfer:
		while(true) {
			while (true) {
		    		System.out.println("Please enter destination account and press enter to continue or \r\n"
		    				+ "press enter to go back to Transaction: ");
		    	    accountInput = input.nextLine();
		    	    
		    	    
		    	    if (accountInput.length() == 0) {
		    	    	break transfer;
		    	    }
		    	    
			    	if (accountInput.length() != 0 && !accountInput.chars().allMatch(Character::isDigit)) {
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
		    	    amountInput = input.nextLine();
		    	    
		    	    if (amountInput.length() == 0) {
		    	    	break transfer;
		    	    }
		    	    
			    	if (!amountInput.chars().allMatch(Character::isDigit)) {
			    		System.out.println("Invalid Amount");
			    		continue;
			    	}
			    	
			    	if(checkMoney(Double.parseDouble(amountInput)))
			    	{
			    		fundTransfer(accountInput, Double.parseDouble(amountInput), String.valueOf(System.currentTimeMillis()));
			    		break;
			    	} else {
			    		continue;
			    	}

			    	
			 }
		 
		}

 
		
		 
		 transactionmenu();

	}
	
	

	private static void summary(Double withdrawnMoney) {
		LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formatDateTime = now.format(formatter);

                
		System.out.println("=======");
		System.out.println("Summary");
		System.out.println("=======");
		System.out.println("Date: " + formatDateTime);
		System.out.println("Withdraw: " + "$" + withdrawnMoney);
		System.out.println("Balance: " + "$" + Bank.getBank().getAccount(loginAccount).getBalance());
		System.out.println();
		System.out.println("1. Transaction");
		System.out.println("2. Exit");
		
		System.out.print("Please choose option [2] :");
		
		String option = input.nextLine();
		
		switch (option) {
		case "1"://Transaction
			transactionmenu();
			break;
		case "2"://exit
			break;
		default :
			welcome();
		}
	}
	
	
}

