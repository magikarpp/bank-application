package com.bank.main;

public class Handler {
	
	// TODO: Add user to database with a balance of 0
	public void addUser(String email, String username, String password) {
		
		
	}
	
	public void removeUser(String email) {
		
		
	}
	
	// TODO: Updates the database with the current information in "user"
	public void updateUser(User user) {
		
	}
	
	// TODO: Check to see if user exists in database
	public boolean userExists(String email) {
		return true;
	}
	
	// TODO: Check to see if user and password combo in database
	public boolean userExists(String email, String pass) {
		return true;
	}
	
	// TODO: Get user from database using primary key: email
	public User getUser(String email) {
		User tempUser = new User(this);
		return tempUser;
	}

}
