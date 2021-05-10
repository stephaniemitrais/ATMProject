                                                                                                                                  package com.mitrais.atm;

import java.util.List;
import java.util.Scanner;

import com.mitrais.atm.bank.AccountRepository;
import com.mitrais.atm.transaction.Transaction;
import com.mitrais.atm.transaction.Transaction.Cancel;
import com.mitrais.atm.transaction.TransactionRepository;
import com.mitrais.atm.transaction.TransactionService;
import com.mitrais.atm.transaction.Transfer;
import com.mitrais.atm.transaction.Withdrawal;


public class ATM {

	private static LoginService loginService;
	
	public TransactionService transactionService;
	
	private static Login loginUser;
	
	private static final AccountRepository bank = new AccountRepository();
	
	private static final TransactionRepository transactionRepo = new TransactionRepository();
	
	
	private static boolean userIsAuthenticated;
	  
    private static final int WELCOME_STATE = 1;
    
    private static final int TRANSACTION_STATE = 2;
	
    private static final int DO_ANOTHER_TRANSACTION_STATE = 3;
    
    private static final int FINAL_STATE = 4;
    
    private int state;
    
    private static Scanner input ;
    
	public ATM() {
		userIsAuthenticated = false;
		
		input = new Scanner(System.in);
		
		loginService = new LoginService(bank);
		
		transactionService = new TransactionService(bank, transactionRepo);
		
		state = WELCOME_STATE;
	}
	
	public TransactionService getTransactionService() {
		return this.transactionService;
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
	                
	        			while(!userIsAuthenticated) {
	        			    
	        				showLogin();
	        			}
	        			
	        			state = TRANSACTION_STATE;
                                            
	                    break;
	                                
	                case TRANSACTION_STATE:
	                                    
	                	
						try {
							showTransactionMenu();
							
							if(doAnotherTransaction()) {
		                		state = TRANSACTION_STATE;
		                	} else {
		                		state = FINAL_STATE;
		                	};
		                	
						} catch (Exit e) {
							state = FINAL_STATE;
						}
	                	

	                    break;
	                    
	                case DO_ANOTHER_TRANSACTION_STATE:
	            
	                	break;
	                
	                case FINAL_STATE:
                                     
	                	userIsAuthenticated = false;
	                    
	                    state = WELCOME_STATE;
	                    
	                    break;
	                
	            }


		}
	}
	


	private static void showLogin(){
		
	    String accountNumberInput = null;
	    
	    String pinInput = null;
	    
	    boolean isAccountValid = false;
	    
		System.out.println("===========================");
	    System.out.println("Welcome to Mitrais Bank ATM");
	    System.out.println("===========================");

	    
	    while (!isAccountValid) {
	    	System.out.println("Enter account number:");
    		accountNumberInput = input.nextLine();
    		    
    		isAccountValid = loginService.validateAccountNo(accountNumberInput);
    	    		
	    }
	    
	    boolean isPINValid = false;
	    
	    while (!isPINValid) {
    		System.out.println("Enter PIN:");
    	    pinInput = input.nextLine();
    	       	    
    	    isPINValid = loginService.validatePIN(pinInput);
	    	
	    }
        
    	if (loginService.authenticateUser(accountNumberInput, pinInput)) {
    		userIsAuthenticated = true;
    		
    		loginUser = new Login();
    		loginUser.setAccountNo(accountNumberInput);
    		loginUser.setPassword(pinInput);
    		
    	} else {
    		userIsAuthenticated = false;
    	}
    	
 
		return;
	    
	}
 
	
	private void showTransactionMenu() throws Exit       
	{
		
		System.out.println("================");
	    System.out.println("Transaction Menu");
	    System.out.println("================");
		
		System.out.println("1. Withdraw");
		System.out.println("2. Fund Transfer");
		System.out.println("3. Transaction History");
		System.out.println("4. Exit");
		
		System.out.print("Please choose option [4] :");
		
		String choice = input.nextLine();
	

			switch (choice) {
			case "1"://Withdraw
				
				try {
					Withdrawal withdrawalTransaction = new Withdrawal(loginUser, this);
					withdrawalTransaction.performTransaction();

				} catch (Cancel e) {
					//cancelled transaction
						
				}
				break;
			case "2"://Fund Transfer

				try {
					Transfer transferTransaction = new Transfer(loginUser, this);
					transferTransaction.performTransaction();

				} catch (Cancel e) {
					//cancelled transaction

				}
				break;
			case "3":
				getTransactionsFromThisATM(loginUser);
				
				break;
			case "4"://exit
				throw new Exit();
			default:
				throw new Exit();
			}
		
		
		
	}
	

	private void getTransactionsFromThisATM(Login loginUser)        
	{
		List<Transaction> transactions = transactionService.getLastTransactions(loginUser);
		System.out.println("Transaction Name"+ " | "+ "Amount");
		transactions.forEach(trans -> {
		    System.out.println(trans.getTransactionName() + " | "+ trans.getTransactionAmount());
		});
		
		System.out.println("Balance: " + transactionService.getBalance(loginUser.getAccountNo()));
		
	}
	
	public boolean doAnotherTransaction() {
		System.out.println("1. Transaction");
		System.out.println("2. Exit");
		
		System.out.print("Please choose option [2] :");
		
		String choice = input.nextLine();
		
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
            super("Logout from ATM");
        }
    }

}

