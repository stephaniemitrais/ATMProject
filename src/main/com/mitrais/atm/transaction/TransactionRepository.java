package com.mitrais.atm.transaction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.mitrais.atm.Login;

public class TransactionRepository {

	private static List<Transaction> transactions = new ArrayList<Transaction>();
	
	
	public List<Transaction> findLastTransactions(Login loginUser) {
		
		List<Transaction> transactionList = transactions.stream()
		.filter(trans -> loginUser.getAccountNo().equals(trans.getAccountNo()))
		.limit(10)
		.sorted(Comparator.comparingInt(Transaction::getId).reversed())
		.collect(Collectors.toList());

    	return transactionList;
	}
	
	public List<Transaction> findLastTransactions(Login loginUser, int transactionNo) {
		
		List<Transaction> transactionList = transactions.stream()
		.filter(trans -> loginUser.getAccountNo().equals(trans.getAccountNo()))
		.limit(transactionNo)
		.sorted(Comparator.comparingInt(Transaction::getId).reversed())
		.collect(Collectors.toList());

    	return transactionList;
	}
	
	public void storeTransaction(Transaction transaction) {
		
		transactions.add(transaction);
		
	}
	
}
