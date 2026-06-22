package com.bank.bankingApp.entity;

public class Account {
 

	    private String accountNumber;
	    private String accountHolderName;
	    private String password;
	    private double balance;
	    
	    public Account() {
			// TODO Auto-generated constructor stub
		}

	    // Constructor
	    public Account(String accountNumber, String accountHolderName, String password, double balance) {
	        this.accountNumber = accountNumber;
	        this.accountHolderName = accountHolderName;
	        this.password = password;
	        this.balance = balance;
	    }

	    // Getters and Setters
	    public String getAccountNumber() { return accountNumber; }
	    public String getAccountHolderName() { return accountHolderName; }
	    public String getPassword() { return password; }
	    public double getBalance() { return balance; }
	    
	    public void setBalance(double balance) { this.balance = balance; }
	}

