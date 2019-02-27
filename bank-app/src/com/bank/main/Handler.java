package com.bank.main;

public class Handler {
	
	// TODO: (login) Get user from database using primary key: email
	public User getUser(String email) {
		User tempUser = new User(this);
		return tempUser;
	}
	
	// TODO: (sign up) Add user to database with a balance of 0
	public void addUser(User user) {
		System.out.println("Created User: " + user.getEmail() + " " + user.getUsername() + " " + user.getpass() + " " + user.getAccounts().get(0).getName());
		
	}
	
	public void updateUser(User user) {
		
	}
	
	// TODO: (deposit/withdraw) Updates the database with the current information in sepcified account
	public void updateAccount(Account account) {
		
	}
	
	// TODO: grab account of specific user
	public void getAccount(User user, String string) {
		
	}
	
	// TODO: Check to see if user exists in database
	public boolean userExists(String email) {
		if(email.equals("login@login.com")) return true;
		else return false;
	}
	
	// TODO: Check to see if user and password combo in database
	public boolean userExists(String email, String pass) {
		return true;
	}

}
