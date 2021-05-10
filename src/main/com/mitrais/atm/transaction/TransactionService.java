package com.mitrais.atm.transaction;

import java.util.List;

import com.mitrais.atm.Login;
import com.mitrais.atm.bank.AccountRepository;
import com.mitrais.atm.transaction.Transaction.Cancel;

public class TransactionService {

	private AccountRepository bank;
	
	private TransactionRepository transactionRepo;

	public TransactionService(AccountRepository bank, TransactionRepository transactionRepo) {
		
		this.bank = bank;
		this.transactionRepo = transactionRepo;
		
	};
	
	public void withdraw(Transaction transaction, String accountNo, double money) throws Cancel {

		reduceBalance(accountNo, money);

		System.out.println("Withdraw successful");
		
		transactionRepo.storeTransaction(transaction);

	}
	
	public void fundTransfer(Transaction transaction, String accountNo, String destinationAccountNo, double money) {
		reduceBalance(accountNo, money);
		addBalance(destinationAccountNo, money);
		
		System.out.println("Transfer successful");
		transactionRepo.storeTransaction(transaction);
	}

	public boolean checkMoney(String accountNo, double money, String transaction, double maximumAmount) {


		if(money > 1000) {
			System.out.println("Maximum amount to withdraw is $1000");
			return false;
		}
		
		Double balance = bank.getBalance(accountNo);
		
		if(balance >= money) {
			return true;
		} else {
			System.out.println("Insufficient balance" + "$" + balance);
			return false;
		}
		

	}
	
	public void reduceBalance(String accountNo, double money) {

		double balance = bank.getBalance(accountNo);
		
		balance = balance - money;
		
		bank.setBalance(accountNo, balance);
	}

	
	public void addBalance(String accountNo, double money) {

		double balance = bank.getBalance(accountNo);
		
		balance = balance + money;
		
		bank.setBalance(accountNo, balance);
	}

	
	public double getBalance(String accountNo) {

		double balance = bank.getBalance(accountNo);
		
		return balance;

	}
	
	public boolean destinationAccountIsValid(String accountNo) {
		if(bank.getAccount(accountNo) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public List<Transaction> getLastTransactions(Login loginUser) {
		
		List<Transaction> transactionList = transactionRepo.findLastTransactions(loginUser);

    	return transactionList;
	} 

}
