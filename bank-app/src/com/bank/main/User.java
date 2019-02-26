package com.bank.main;

public class User {

	private String email;
	private String username;
	private String pass;
	private double balance;
	
	private Handler handler;
	
	public User(Handler handler) {
		super();
		email = "";
		username = "";
		pass = "";
		balance = 0;
		this.handler = handler;
	}
	
	public User(String email, String username, String pass, double balance, Handler handler) {
		super();
		this.email = email;
		this.username = username;
		this.pass = pass;
		this.balance = balance;
		this.handler = handler;
	}
	
	public User(String email, String username, String pass) {
		super();
		this.email = email;
		this.username = username;
		this.pass = pass;
		this.balance = 0;
	}
	
	public double depositBalance(double amount) {
		balance += amount;
		handler.updateUser(this);
		return balance;
	}
	
	public double withdrawBalance(double amount) {
		balance -= amount;
		handler.updateUser(this);
		return balance;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
		handler.updateUser(this);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
		handler.updateUser(this);
	}
	public String getpass() {
		return pass;
	}
	public void setpass(String pass) {
		this.pass = pass;
		handler.updateUser(this);
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
		handler.updateUser(this);
	}

}
