package com.bank.main;

public class Bank {
	
	private static Bank bank;
	
	private Handler handler;
	private Application app;
	
	private Bank() {
		
		handler = new Handler();
		app = new Application(handler);
		
		app.start();
	}
	
	public static Bank getInstance() {
		if(bank == null) {
			bank = new Bank();
		}
		return bank;
	}

	public static void main(String[] args) {
		Bank bank = Bank.getInstance();
	}

}
