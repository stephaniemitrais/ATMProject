package data;

import main.ATM;

public class Transfer extends Transaction {
    public Transfer(Session session, ATM atm)
    {
        super(session, atm);
    }

	@Override
	protected String getTransactionName() {
		return "transfer";
	}

	@Override
	public void performTransaction() throws Exit {
		

		String accountInput;
		String amountInput = null;
		
		boolean transferIsProcessed = false;
		
		transfer:
		while(true) {
			while (true) {
		    		System.out.println("Please enter destination account and press enter to continue or \r\n"
		    				+ "press enter to go back to Transaction: ");
		    	    accountInput = atm.getConsole().getStringInput();
		    	    
		    	    
		    	    if (accountInput.length() == 0) {
		    	    	break transfer;
		    	    }
		    	    
			    	if (accountInput.length() != 0 && !accountInput.chars().allMatch(Character::isDigit)) {
			    		System.out.println("Invalid Account");
			    		continue;
			    	}
			    	
			    	if (atm.getBank().getAccount(accountInput) == null) {
			    		System.out.println("Invalid Account");
			    		continue;
			    	}
			    	
			    	break;
			 }
			
			
			 while (true) {
		    		System.out.println("Please enter transfer amount and press enter to continue \r\n"
		    				+ "or press enter to go back to Transaction:");
		    	    amountInput = atm.getConsole().getStringInput();
		    	    
		    	    if (amountInput.length() == 0) {
		    	    	break transfer;
		    	    }
		    	    
			    	if (!amountInput.chars().allMatch(Character::isDigit)) {
			    		System.out.println("Invalid Amount");
			    		continue;
			    	}
			    	
			    	if(checkMoney(Double.valueOf(amountInput), getTransactionName(), 1000))
			    	{
			    		transferIsProcessed = true;
			    		break transfer;

			    	} else {
			    		continue;
			    	}

			    	
			 }
		 
		}
		
		if(transferIsProcessed) {
			fundTransfer(accountInput, Double.parseDouble(amountInput), String.valueOf(System.currentTimeMillis()));
			
		} else {
			
			throw new Exit();
		}

	
		
		
	}
	

	

	private void fundTransfer(String destinationAccount, double money, String referenceNo) {
		reduceBalance(money);
		System.out.println("Transfer successful");
		summary(destinationAccount, money, referenceNo);
	
	}
	
	private void summary(String destinationAccount, double money, String referenceNo) {
		System.out.println("=====================");
		System.out.println("Fund Transfer Summary");
		System.out.println("=====================");
		System.out.println("Destination Account: " + destinationAccount);
		System.out.println("Transfer Amount :" + money );
		System.out.println("Reference Number :" + referenceNo);
		System.out.println("Balance:" + "$" + getBalance());
		
	}
	

}
