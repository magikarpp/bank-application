package com.revature.bankApp;

import java.util.ArrayList;

public class User {
	private String email;
	private String username;
	private String pass;
	private ArrayList<Account> accounts = new ArrayList<Account>();
	
	private Handler handler;
	
	public User(Handler handler) {
		super();
		email = "";
		username = "";
		pass = "";
		accounts.add(new Account(handler));
		this.handler = handler;
	}
	
	public User(String email, String username, String pass, Handler handler) {
		super();
		this.email = email;
		this.username = username;
		this.pass = pass;
		this.accounts = new ArrayList<Account>();
		this.handler = handler;
	}
	
	public User(String email, String username, String pass, ArrayList<Account> accounts, Handler handler) {
		super();
		this.email = email;
		this.username = username;
		this.pass = pass;
		this.accounts = accounts;
		this.handler = handler;
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
	
	public Account getAccountByName(String name) {
		Account tempAccount;
		for(int i = 0; i < accounts.size(); i++) {
			tempAccount = accounts.get(i);
			if(tempAccount.getName().equals(name)) return tempAccount;
		}
		return null;
	}
	
	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void addAccount(Account account) {
		handler.updateAccount(account);
		this.accounts.add(account);
	}
	public boolean removeAccount(Account account) {
		handler.updateAccount(account);
		return this.accounts.remove(account);
	}

}
