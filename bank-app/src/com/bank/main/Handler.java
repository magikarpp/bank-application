package com.bank.main;

import java.util.LinkedList;

public class Handler {
	
	LinkedList<User> users = new LinkedList<User>();
	
	public void addUser(User user) {
		this.users.add(user);
	}
	
	public void removeUser(User user) {
		this.users.remove(user);
	}
	
	public void updateUser() {
		
	}

}
