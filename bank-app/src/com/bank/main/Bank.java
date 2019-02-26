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
	
	private static Bank getInstance() {
		if(bank == null) {
			bank = new Bank();
		}
		return bank;
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Bank bank = Bank.getInstance();
	}

}
