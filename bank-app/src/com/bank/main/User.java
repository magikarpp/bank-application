package com.bank.main;

public class User {

	private String username;
	private String email;
	private String password;
	private double balance;
	private boolean active;
	private Handler handler;
	
	public User(String email, String username, String password, double balance, boolean active, Handler handler) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.active = active;
		this.handler = handler;
	}
	
	public User(String email, String username, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.balance = 0;
		this.active = false;
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
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
		handler.updateUser(this);
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
