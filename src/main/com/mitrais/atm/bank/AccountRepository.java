package com.mitrais.atm.bank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AccountRepository {

	
	public AccountRepository(String filePath) {
		this.filePath = filePath;
		accounts = getAccounts();
	}
	private String filePath;
    private static List<Account> accounts;

    /*
    List<Account> getAccounts() {
        return Arrays.asList(new Account("John Doe","112233", "012108", 100),
                new Account("Jane Doe", "112244", "932012", 30));
    }
    */
  
    private List<Account> getAccounts() {
    	
        String filePath = this.filePath;
        
        List<Account> accountList = new ArrayList<Account>();
        
        
        try{

            File accountFile = new File(filePath);
            InputStream inputFS = new FileInputStream(accountFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

            accountList = br.lines().skip(1)
            		.map(mapToPerson).collect(Collectors.toList());
            
            
            br.close();
            
          } catch (IOException e) {
            
          }

          return accountList ;
    }
    


	private static Function<String, Account> mapToPerson = (line) -> {
	  String[] p = line.split(";");
	  return new Account(p[0], p[3], p[1], Double.valueOf(p[2]));
	};
    

    public Account getAccount(String accountNo, String password) {

    	Account account = accounts.stream()
    			.filter(acc -> accountNo.equals(acc.getAccountNo()) && password.equals(acc.getPassword()))
    			.findAny()                                      
                .orElse(null);  

    	return account;
    }
    
    public Account getAccount(String accountNo) {

    	Account account = accounts.stream()
    			.filter(acc -> accountNo.equals(acc.getAccountNo()))
    			.findAny()                                      
                .orElse(null);  

    	return account;
    }
    

	public void setBalance(String accountNo, double balance) {

		getAccount(accountNo).setBalance(balance);
	}
	
	public double getBalance(String accountNo) {

		double balance = getAccount(accountNo).getBalance();
		
		return balance;
	}
	
	
	
		
}
