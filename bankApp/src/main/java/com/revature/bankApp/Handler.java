package com.revature.bankApp;

public class Handler {
	
	// TODO: (login) Get user by (email) from database
	public User grabUserByEmail(String email) {
		User tempUser = new User(this);
		return tempUser;
	}
	
	// TODO: Get account by (name) from database
	public Account grabAccountByName(String name) {
		Account tempAcc = new Account(this);
		return tempAcc;
	}
	
	// TODO: (sign up) update user in data base with input user. If user not in database, add user.
	public void updateUser(User user) {
		 for(int i = 0; i < user.getAccounts().size(); i++) {
			 updateAccount(user.getAccounts().get(i));
		 }
	}
	
	// TODO: (deposit/withdraw) Updates the database with the current information in sepcified account. If account not in database, add account.
	public void updateAccount(Account account) {
		
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
	
	// TODO: Check to see if account exists in database
	public boolean accountExists(String accountName) {
		return false;
	}

}
