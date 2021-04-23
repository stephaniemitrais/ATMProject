package com.mitrais.atm;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mitrais.atm.ATM;


public class StudyCase1Test {


	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	private final PrintStream standardOut = System.out;
	
	ATM atm;
	
	@Before
	public void setUp() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	    atm = new ATM();
	}
	
	@Test
	public void testLoginValidAcccountNo_length() {
		
		boolean accountIsValid = atm.validateAccountNo("11");
	    Assert.assertEquals("Account Number should have 6 digits length", outputStreamCaptor.toString()
	    	      .trim());
	    
		Assert.assertFalse(accountIsValid);
		
	}
	
	@Test
	public void testLoginValidAcccountNo_number() {
		
		boolean accountIsValid = atm.validateAccountNo("abc");
	    Assert.assertEquals("Account Number should only contains numbers", outputStreamCaptor.toString()
	    	      .trim());
	    
		Assert.assertFalse(accountIsValid);
	}
	
	@Test
	public void testLoginValidPIN_length() {
		
		boolean accountIsValid = atm.validatePIN("1237");
	    Assert.assertEquals("PIN should have 6 digits length", outputStreamCaptor.toString()
	    	      .trim());
	    
		Assert.assertFalse(accountIsValid);
	}
	
	public void testLoginValidPIN_number() {
		
		boolean accountIsValid = atm.validatePIN("abcd");
	    Assert.assertEquals("PIN should only contains numbers", outputStreamCaptor.toString()
	    	      .trim());
	    
		Assert.assertFalse(accountIsValid);
	}
	
	
	@After
	public void tearDown() {
	    System.setOut(standardOut);
	    atm = null;
	}

}
