package com.bank.main;

import java.util.Scanner;

public class Application {
	
	private Handler handler;
	private static Scanner scan = new Scanner(System.in);
	
	public Application(Handler handler) {
		this.handler = handler;
		
	}
	
	public void start() {
		
		boolean isLoggedIn = false;
		boolean exit = false;
		
		handler.updateUsers();
		
		for(int i = 0; i < handler.users.size(); i++) {
			if(handler.users.get(i).isActive()) isLoggedIn = true;
		}
		
		while(!exit) {
			
			while(!isLoggedIn) {
				System.out.println("##### Bank of Jaemin Shim #####");
				System.out.println("Please enter 'login', 'signup', or 'exit'");
				boolean next = false;
				
				String riddle = scan.nextLine().toLowerCase();
				
				if(riddle.equals("login")) {
					System.out.println();
					System.out.println("Please enter your email:");
					String email = scan.nextLine();
	
					for(int i = 0; i < handler.users.size(); i++) {
						if(handler.users.get(i).getEmail().equals(email)) next = true;
					}
					
					if(next) {
						next = false;
						
						System.out.println("Please enter your password:");
						String password = scan.nextLine();
						
						for(int i = 0; i < handler.users.size(); i++) {
							if(handler.users.get(i).getEmail().equals(email) && handler.users.get(i).getPassword().equals(password)) next = true;;
						}
						
						if(next) {
							for(int i = 0; i < handler.users.size(); i++) {
								if(handler.users.get(i).getEmail().equals(email) && handler.users.get(i).getPassword().equals(password)) {
									handler.users.get(i).setActive(true);
								}
							}
							System.out.println("Login Successful.");
							isLoggedIn = true;
						} else {
							System.out.println("Incorrect Password. Please try again.\n");
						}
						
					} else {
						System.out.println("No user registered under given email. Please try again.\n");
					}
					
				} else if(riddle.equals("signup")) {
					System.out.println("Please enter new email:");
					String email = scan.nextLine();
					
					System.out.println("Please enter new username:");
					String username = scan.nextLine();
					
					System.out.println("Please enter new password:");
					String password = scan.nextLine();
					
					handler.addUser(email, username, password);
					
					
				} else if(riddle.equals("exit")) {
					System.out.println("See ya later Alligator!");
					exit = true;
					break;
					
				} else {
					System.out.println("Yo, you need a better input homie.\n");
				}
				
			}
			
			
			while(isLoggedIn) {
				clearScreen();
				User currentUser = null;
				for(int i = 0; i < handler.users.size(); i++) {
					if(handler.users.get(i).isActive()) currentUser = handler.users.get(i);
				}
				
				System.out.println("Username:   " + currentUser.getUsername());
				System.out.println("Email:      " + currentUser.getEmail());
				System.out.println("Balance:    " + currentUser.getBalance());
				
				System.out.println();
				System.out.println("Actions: 'deposit', 'withdraw', or 'logout'");
				String action = scan.nextLine().toLowerCase();
				
				if(action.equals("logout")) {
					isLoggedIn = false;
					currentUser.setActive(false);
					System.out.println("User Logged Out Successfully.\n");
				} else if(action.equals("deposit")) {
					System.out.println("How much to deposit?");
					double amount = scan.nextDouble();
					currentUser.depositBalance(amount);
					
				} else if(action.equals("withdraw")) {
					System.out.println("How much to withdraw?");
					double amount = scan.nextDouble();
					currentUser.withdrawBalance(amount);
				}
			}
		}
		
		
	}
	
	private void clearScreen() {
		for(int i = 0; i<50; i++) {
			System.out.println();
		}
	}
	
	
}
