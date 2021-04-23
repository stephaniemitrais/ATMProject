                                                                                                                                  package com.mitrais.atm;

import com.mitrais.atm.bank.Bank;


public class ATM {

	// private static Scanner input ;
	
	private static Console console ;
	
	private static Session session;
	
	private static Bank bank;
	
	private static boolean userIsAuthenticated;
	  
    private static final int WELCOME_STATE = 1;
    
    private static final int SERVING_STATE = 2;
	
    private static final int FINAL_STATE = 3;
    
    private int state;
    
    private static String accountNumberInput = null;
    
    private static String pinInput = null;
    
	public ATM() {
		userIsAuthenticated = false;
		console = new Console();
		bank = new Bank();
		state = WELCOME_STATE;
	}
	
	
	public Console getConsole() {
		return console;
	}


	public Bank getBank() {
		return bank;
	}

	
	public static void main(String[] args) {

      
		ATM atm = new ATM();
		atm.run();

	}
	
	private void run() {
		boolean atmIsRunning = true;
		
		while (atmIsRunning) {
    	   

		    
			 switch(state)
	            {
	                case WELCOME_STATE:
	                
	                	accountNumberInput = null;
	                	pinInput = null;
	                			
	        			while(!userIsAuthenticated) {
	        			    
	        				welcome();
	        			}
	        			
	        			state = SERVING_STATE;
                                            
	                    break;
	                                
	                case SERVING_STATE:
	                                    
	                	session = new Session(this, accountNumberInput, pinInput);
	                    session.performSession();
	                        
	                    state = FINAL_STATE;
	                    break;
	                    
	                case FINAL_STATE:
                                     
	                	userIsAuthenticated = false;
	                    session = null;
	                    
	                    state = WELCOME_STATE;
	                    
	                    break;
	                
	            }


		}
	}
	


	private static void welcome(){
		System.out.println("===========================");
	    System.out.println("Welcome to Mitrais Bank ATM");
	    System.out.println("===========================");


	    
	    boolean isAccountValid = false;
	    
	    while (!isAccountValid) {
	    	System.out.println("Enter account number:");
    		accountNumberInput = console.getStringInput(); 
    		    
    		isAccountValid = validateAccountNo(accountNumberInput);
    	    		
    	    
	    }
	    
	    boolean isPINValid = false;
	    
	    while (!isPINValid) {
    		System.out.println("Enter PIN:");
    	    pinInput = console.getStringInput(); 
    	       	    
    	    isPINValid = validatePIN(pinInput);
	    	
	    }
	    
        
    	if (bank.getAccount(accountNumberInput, pinInput) == null) {
    		System.out.println("Invalid Account Number/PIN");

    	} else {

    		userIsAuthenticated = true;
    	}
    	
		return;
	    
	}
 
	
    public static class InvalidLogin extends Exception
    {

        public InvalidLogin()
        {
            super("Invalid Account Number/PIN");
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
	    
	/*
	public int transactionmenu() {
		System.out.println("================");
	    System.out.println("Transaction Menu");
	    System.out.println("================");
		
		System.out.println("1. Withdraw");
		System.out.println("2. Fund Transfer");
		System.out.println("3. Exit");
		
		System.out.print("Please choose option [3] :");
		int result = console.getNumberInput();
		
		return result;
		
	}
	*/
	
	


}

