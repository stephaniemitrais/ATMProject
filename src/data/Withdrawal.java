package data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import main.ATM;

public class Withdrawal extends Transaction {

    public Withdrawal(Session session, ATM atm) {
		super(session, atm);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected String getTransactionName() {
		return "withdraw";
	}

	
    private double withdrawnAmount;

	@Override
	public void performTransaction() throws Exit {

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
			String money = atm.getConsole().getStringInput();
			
			switch (money) {
			case "1": //$10
				amountIsValid = checkMoney(10, getTransactionName(), 1000);
				withdrawnAmount = Double.valueOf(10);
				break;
				
			case "2"://$50
				amountIsValid = checkMoney(10, getTransactionName(), 1000);
				withdrawnAmount = Double.valueOf(50);
				break;
				
			case "3"://$100
				amountIsValid = checkMoney(10, getTransactionName(), 1000);
				withdrawnAmount = Double.valueOf(100);
				break;
				
			case "4"://other amount
				double amount = 0;
				try {
					amount = otherWithdrawnAmount();
					amountIsValid = checkMoney(amount, getTransactionName(), 1000);
					withdrawnAmount = amount;
					
					
				} catch (Cancelled e) {
		
				}

				break;
				
			case "5"://back
				throw new Exit();
				
			default://exit
				throw new Exit();
			}
			
		}
		
		withdraw(withdrawnAmount);

		summary(withdrawnAmount);
		
	}
	

	private void withdraw(double money) throws Exit {

		reduceBalance(money);
		
		System.out.println("Withdraw successful");

	
	}
	
	

		
	private Double otherWithdrawnAmount() throws Cancelled {
		
		
		 while (true) {
				System.out.println("==============");
				System.out.println("Other Withdraw");
				System.out.println("==============");
				System.out.println("Enter amount to withdraw:");

				String money = atm.getConsole().getStringInput();
	    	    
				if(money.length() == 0) {
					throw new Cancelled();
				}
				
		    	if (!money.chars().allMatch(Character::isDigit)) {
		    		System.out.println("Invalid ammount");
		    		continue;
		    	} 
		    	
		    	return Double.valueOf(money);
		    	
		    	
		 }
		 

		
	}
	
	
	private void summary(Double withdrawnMoney) throws Exit {
		LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formatDateTime = now.format(formatter);

                
		System.out.println("=======");
		System.out.println("Summary");
		System.out.println("=======");
		System.out.println("Date: " + formatDateTime);
		System.out.println("Withdraw: " + "$" + withdrawnMoney);
		System.out.println("Balance: " + "$" + getBalance());

	}
	
	public static class Cancelled extends Exception
    {
        public Cancelled()
        {
            super("Cancelled");
        }
    }
	
}
