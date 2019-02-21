package com.bank.main;

public class Bank {
	
	private Handler handler;
	private Application app;
	
	public Bank() {
		
		handler = new Handler();
		app = new Application(handler);
		
		app.start();
	}

	public static void main(String[] args) {
		new Bank();
	}

}
