package com.mitrais.atm;

import com.mitrais.atm.bank.AccountRepository;

public class LoginService {

	public LoginService(AccountRepository bank) {
		this.bank = bank;
	}
	
	private AccountRepository bank;

	public boolean validateAccountNo(String accountNumberInput) {
		
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
	    
	
	public boolean validatePIN(String pinInput) {
		
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
	
	public boolean authenticateUser(String accountNumberInput, String pinInput) {
		
		if (bank.getAccount(accountNumberInput, pinInput) == null) {
			System.out.println("Invalid Account Number/PIN");
			return false;
		}
 	    
 	    return true;
	}
}
