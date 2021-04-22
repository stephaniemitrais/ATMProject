package data;

import data.Transaction.Exit;
import main.ATM;

public class Session {
	
	private ATM atm;
	private String accountNo;
	private String PIN;
    private int state;
	
	public Session (ATM atm, String accountNo, String PIN) {
		this.atm = atm;
		this.accountNo = accountNo;
		this.PIN = PIN;
		state = CHOOSING_TRANSACTION_STATE;
	}
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getPIN() {
		return PIN;
	}
	public void setPIN(String pIN) {
		PIN = pIN;
	}
	

    private static final int CHOOSING_TRANSACTION_STATE = 1;
    
    private static final int PERFORMING_TRANSACTION_STATE = 2;
    
    private static final int ASK_TO_DO_AGAIN_TRANSACTION = 3;
    
    private static final int FINAL_STATE = 4;

    


	public void performSession()
    {

        Transaction currentTransaction = null;
        
        while (state != FINAL_STATE)
        {
            switch(state)
            {
                
                case CHOOSING_TRANSACTION_STATE:
                
                    try
                    {
                        currentTransaction = 
                            Transaction.makeTransaction(this, atm);
                        state = PERFORMING_TRANSACTION_STATE;
                    }
                    catch(Exit e)
                    {
                        state = FINAL_STATE;
                    }
                    break;
                    
                case PERFORMING_TRANSACTION_STATE:

					try {
						currentTransaction.performTransaction();
						
						state = ASK_TO_DO_AGAIN_TRANSACTION;
						
					} catch (Exit e) {
						state = CHOOSING_TRANSACTION_STATE;
					}


					break;
					
                case ASK_TO_DO_AGAIN_TRANSACTION:
                	
                	if(currentTransaction.doAnotherTransaction()) {
                		state = CHOOSING_TRANSACTION_STATE;
                		
                	} else {
                		state = FINAL_STATE;
                		
                	}
                    break;
                   

            }
        }
    }
	
}
