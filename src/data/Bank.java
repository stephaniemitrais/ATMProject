package data;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Bank {

    private final List<Account> accounts = getAccounts();

    List<Account> getAccounts() {
        return Arrays.asList(new Account("John Doe","112233", "012108", 100),
                new Account("Jane Doe", "112244", "932012", 30));
    }


    private static Bank bank = null;
    
    public static Bank getBank()
    {
        if (bank == null)
            bank = new Bank();
  
        return bank;
    }
    

    
    public Account getAccount(String accountNo, String password) {

    	for (Account account : accounts) {
    	    if (account.getAccountNo().equals(accountNo) && account.getPassword().equals(password)) {
    	         return account;
    	    }
    	}
    	
    	return null;
    }
    
    public Account getAccount(String accountNo) {

    	for (Account account : accounts) {
    	    if (account.getAccountNo().equals(accountNo)) {
    	         return account;
    	    }
    	}
    	
    	return null;
    }
}
