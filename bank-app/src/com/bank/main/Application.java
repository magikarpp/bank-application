package com.bank.main;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
	
	private Handler handler;
	private static Scanner scan = new Scanner(System.in);
	private User currentUser = null;
	private boolean isLoggedIn = false;
	private boolean exit = false;
	
	public Application(Handler handler) {
		this.handler = handler;
		
	}
	
	public void start() {
		
		while(!exit) {
			
			notLoggedIn();
			
			if(isLoggedIn) currentlyLoggedIn();
			
		}
		
		
	}
	
	private void notLoggedIn() {
		while(!isLoggedIn) {
			System.out.println("##### Bank of Jaemin Shim #####");
			System.out.println("Please enter 'login', 'signup', or '!exit'");
			
			String riddle = scan.nextLine().toLowerCase().replaceAll("\\s+","");;
			
			if(riddle.equals("login")) {
				System.out.println();
				System.out.println("Please enter email (or !exit):");
				String email = scan.nextLine();
				
				while((!validate(email) || !handler.userExists(email)) && !email.equals("!exit")) {
					System.out.println("\nEmail does not exist or email input not valid.");
					System.out.println("Please enter email (or !exit):");
					email = scan.nextLine();
				}
				
				if(email.equals("!exit")) {
					isLoggedIn = false;
					System.out.println("Successfully exited.\n");
				} else {
					System.out.println("Please enter password (or !exit):");
					String pass = scan.nextLine();
					
					while(!handler.userExists(email, pass) && !pass.equals("!exit")) {
						System.out.println("\nPassword does not match.");
						System.out.println("Please enter password (or !exit):");
						pass = scan.nextLine();
					}
					if(email.equals("!exit")) {
						isLoggedIn = false;
						System.out.println("Successfully exited.\n");
					}
					else{
						currentUser = handler.getUser(email);
						isLoggedIn = true;
						System.out.println("User Successfully Logged In.\n");
					}
				}
				
			} else if(riddle.equals("signup")) {
				System.out.println("Please enter new email (or !exit):");
				String email = scan.nextLine();
				
				while((!validate(email) || handler.userExists(email)) && !email.equals("!exit")) {
					System.out.println("\nEmail already a user or email format not valid.");
					System.out.println("Please enter new email (or !exit):");
					email = scan.nextLine();
				}
				if(email.equals("!exit")) {
					isLoggedIn = false;
					System.out.println("Successfully exited.\n");
				} else {
					System.out.println("Please enter new username (or !exit):");
					String username = scan.nextLine();
					
					while(username.length() > 25 && !username.equals("!exit")) {
						System.out.println("\nUsername too long");
						System.out.println("Please enter new username (or !exit):");
						username = scan.nextLine();
					}
					if(username.equals("!exit")) {
						isLoggedIn = false;
						System.out.println("Successfully exited.\n");
					} else {
						System.out.println("Please enter new password (or !exit):");
						String pass = scan.nextLine();
						
						while(pass.length() > 25 && !pass.equals("!exit")) {
							System.out.println("\nPassword format not valid.");
							System.out.println("Please enter new password (or !exit):");
							pass = scan.nextLine();
						}
						if(pass.equals("!exit")) {
							isLoggedIn = false;
							System.out.println("Successfully exited.\n");
						} else {
							handler.addUser(email, username, pass);
							System.out.println("User Successfully Created!\n");
						}
					}
				}
				
				
			} else if(riddle.equals("!exit")) {
				System.out.println("See ya later Alligator!");
				exit = true;
				break;
				
			} else {
				System.out.println("Invalid Action\n");
			}
			
		}
	}
	
	private void currentlyLoggedIn() {
		String message = "";
		while(isLoggedIn) {
			clearScreen();
			
			System.out.println("Username:   " + currentUser.getUsername());
			System.out.println("Email:      " + currentUser.getEmail());
			System.out.println("Balance:    " + currentUser.getBalance());
			
			System.out.println();
			System.out.println("Actions: 'deposit', 'withdraw', or 'logout'");
			System.out.println(message);
			
			String action = scan.nextLine().toLowerCase();
			
			if(action.equals("logout")) {
				isLoggedIn = false;
				System.out.println("User Logged Out Successfully.\n");
				
			} else if(action.equals("deposit")) {
				System.out.println("How much to deposit?");
				double amount = scan.nextDouble();
				currentUser.depositBalance(amount);
				message = "$" + amount + " has been deposited to balance.";
				
			} else if(action.equals("withdraw")) {
				System.out.println("How much to withdraw?");
				double amount = scan.nextDouble();
				currentUser.withdrawBalance(amount);
				message = "$" + amount + " has been withdrawn from balance.";
				
			} else {
				// TODO: [BUG] Message always printing out "Invalid Action", no matter what action.
				message = "Invalid Action";
			}
		}
	}
	
	private void clearScreen() {
		for(int i = 0; i<50; i++) {
			System.out.println();
		}
	}
	
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	private static boolean validate(String emailStr) {
	        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
	        return matcher.find();
	}
	
	
}
