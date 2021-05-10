package com.mitrais.atm;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mitrais.atm.ATM;
import com.mitrais.atm.bank.AccountRepository;


public class StudyCase1Test {


	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	private final PrintStream standardOut = System.out;
	
	LoginService loginService;
	
	AccountRepository bank = new AccountRepository();
	
	@Before
	public void setUp() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	    loginService = new LoginService(bank);
	}
	
	@Test
	public void testLoginValidAcccountNo_length() {
		
		boolean accountIsValid = loginService.validateAccountNo("11");
	    Assert.assertEquals("Account Number should have 6 digits length", outputStreamCaptor.toString()
	    	      .trim());
	    
		Assert.assertFalse(accountIsValid);
		
	}
	
	@Test
	public void testLoginValidAcccountNo_number() {
		
		boolean accountIsValid = loginService.validateAccountNo("abc");
	    Assert.assertEquals("Account Number should only contains numbers", outputStreamCaptor.toString()
	    	      .trim());
	    
		Assert.assertFalse(accountIsValid);
	}
	
	@Test
	public void testLoginValidPIN_length() {
		
		boolean accountIsValid = loginService.validatePIN("1237");
	    Assert.assertEquals("PIN should have 6 digits length", outputStreamCaptor.toString()
	    	      .trim());
	    
		Assert.assertFalse(accountIsValid);
	}
	
	public void testLoginValidPIN_number() {
		
		boolean accountIsValid = loginService.validatePIN("abcd");
	    Assert.assertEquals("PIN should only contains numbers", outputStreamCaptor.toString()
	    	      .trim());
	    
		Assert.assertFalse(accountIsValid);
	}
	
	
	@After
	public void tearDown() {
	    System.setOut(standardOut);
	    loginService = null;
	}

}
