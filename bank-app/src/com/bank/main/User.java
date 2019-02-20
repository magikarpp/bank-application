package com.bank.main;

public class User {

	private String username;
	private String email;
	private String password;
	private double balance;
	private boolean active;
	
	public User(String username, String email, String password, double balance, boolean active) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.balance = balance;
		this.active = active;
	}
	
	public double depositBalance(double amount) {
		balance += amount;
		return balance;
	}
	
	public double withdrawBalance(double amount) {
		balance -= amount;
		return balance;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
		
}
