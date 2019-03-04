package com.revature.bankApp;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {

	private static final long serialVersionUID = -6505460802093854096L;
	
	private String name;
	private String type;
	private double balance;
	private ArrayList<User> owners = new ArrayList<User>();

	private Handler handler;
	
	Account(Handler handler){
		name = "Testing";
		type = "checking";
		balance = 0;
		owners = new ArrayList<User>();
		this.handler = handler;
	}
	
	Account(String name, String type, Handler handler) {
		super();
		this.name = name;
		this.type = type;
		balance = 0;
		this.owners = new ArrayList<User>();
		this.handler = handler;
	}

	public Account(String name, String type, double balance, Handler handler) {
		super();
		this.name = name;
		this.type = type;
		this.balance = balance;
		this.owners = new ArrayList<User>();
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
	}
	
	public boolean removeOwner(User user) {
		return this.owners.remove(user);
	}

	@Override
	public String toString() {
		String strOwners = "";
		for(int i = 0; i < owners.size(); i++) {
			strOwners += "(" + owners.get(i).getEmail() + ")";
		}
		return "Account [name=" + name + ", type=" + type + ", balance=" + balance + ", owners=" + strOwners + "]";
	}

}
