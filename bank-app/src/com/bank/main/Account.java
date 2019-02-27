package com.bank.main;

import java.util.ArrayList;

public class Account {
	
	private String name;
	private String type;
	private double balance;
	private ArrayList<User> owners = new ArrayList<>();

	private Handler handler;
	
	Account(String name, String type, User user, Handler handler) {
		super();
		this.name = name;
		this.type = type;
		this.owners.add(user);
		this.handler = handler;
	}

	public Account(String name, String type, double balance, ArrayList<User> owners, Handler handler) {
		super();
		this.name = name;
		this.type = type;
		this.balance = balance;
		this.owners = owners;
		this.handler = handler;
	}
	
	public boolean depositBalance(double amount) {
		balance += amount;
		handler.updateAccount(this);
		return true;
	}
	
	public boolean withdrawBalance(double amount) {
		if(balance - amount < 0) {
			return false;
		}
		balance -= amount;
		handler.updateAccount(this);
		return true;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public ArrayList<User> getOwners() {
		return owners;
	}

	public void addOwner(User user) {
		this.owners.add(user);
	}
	
	public boolean removeOwner(User user) {
		return this.owners.remove(user);
	}

}
