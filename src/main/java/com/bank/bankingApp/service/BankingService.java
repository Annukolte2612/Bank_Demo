
	
	package com.bank.bankingApp.service;

	import com.bank.bankingApp.entity.Account;
	import org.springframework.stereotype.Service;
	import java.util.HashMap;
	import java.util.Map;

	@Service
	public class  BankingService {
	    private Map<String, Account> accounts = new HashMap<>();

	    public BankingService() {
	        // Account Number: 12345, Password: password123
	        accounts.put("12345", new Account("12345", " ", "password123", 5000.0));
	    }

	    public Account authenticate(String accountNumber, String password) {
	        Account account = accounts.get(accountNumber);
	        if (account != null && account.getPassword().equals(password)) {
	            return account;
	        }
	        return null;
	    }

	    // (Deposit)
	    public void deposit(Account account, double amount) {
	        if (amount > 0) {
	            account.setBalance(account.getBalance() + amount);
	        }
	    }

	  

		public boolean withdraw(Account account, double amount) {
			// TODO Auto-generated method stub
			 if (amount > 0 && account.getBalance() >= amount) {
		            account.setBalance(account.getBalance() - amount);
		            return true;
		        }
			return false;
		}
	}

