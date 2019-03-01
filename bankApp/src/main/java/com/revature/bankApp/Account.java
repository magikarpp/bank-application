package com.revature.bankApp;

import java.util.ArrayList;

public class Account {

	private String name;
	private String type;
	private double balance;
	private ArrayList<User> owners = new ArrayList<User>();
	private String history;

	private Handler handler;
	
	Account(Handler handler){
		name = "Testing";
		type = "checking";
		balance = 0;
		owners = new ArrayList<User>();
		history = "";
		this.handler = handler;
	}
	
	Account(String name, String type, User user, Handler handler) {
		super();
		this.name = name;
		this.type = type;
		balance = 0;
		this.owners.add(user);
		this.history = "";
		this.handler = handler;
	}

	public Account(String name, String type, double balance, ArrayList<User> owners, String history, Handler handler) {
		super();
		this.name = name;
		this.type = type;
		this.balance = balance;
		this.owners = owners;
		this.history = history;
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
		handler.updateAccount(this);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		handler.updateAccount(this);
		this.type = type;
	}

	public double getBalance() {
		return balance;
	}
	
	public ArrayList<User> getOwners() {
		return owners;
	}

	public void addOwner(User user) {
		this.owners.add(user);
		handler.updateAccount(this);
	}
	
	public boolean removeOwner(User user) {
		handler.updateAccount(this);
		return this.owners.remove(user);
	}

	public String getHistory() {
		return history;
	}

	public void addHistory(String history) {
		this.history += history;
		handler.updateAccount(this);
	}
	
	public void deleteHistory() {
		history = "";
		handler.updateAccount(this);
	}

}
