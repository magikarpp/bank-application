package com.revature.bankApp;

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
			
			String riddle = scan.nextLine().toLowerCase().replaceAll("\\s+","");
			
			// Action: Login
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
					
					while(!handler.passwordValidation(email, pass) && !pass.equals("!exit")) {
						System.out.println("\nPassword does not match.");
						System.out.println("Please enter password (or !exit):");
						pass = scan.nextLine();
					}
					if(email.equals("!exit")) {
						isLoggedIn = false;
						System.out.println("Successfully exited.\n");
					}
					else{
						currentUser = handler.grabUserByEmail(email);
						isLoggedIn = true;
						System.out.println("User Successfully Logged In.\n");
					}
				}
			
			// Action: Signup
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
							System.out.println("Please enter new type for new account (or !exit):");
							System.out.println("Checking or Savings?");
							String type = scan.nextLine().toLowerCase();
							
							while((!type.equals("checking") && !type.equals("savings")) && !type.equals("!exit")) {
								System.out.println("\nType not valid.");
								System.out.println("Please enter new type for new account (or !exit):");
								System.out.println("Checking or Savings?");
								type = scan.nextLine().toLowerCase();
							}
							if(pass.equals("!exit")) {
								isLoggedIn = false;
								System.out.println("Successfully exited.\n");
							} else {
								System.out.println("Please enter name for new account (or !exit):");
								String accountName = scan.nextLine();
								
								while((accountName.length() > 25 || handler.accountExists(accountName)) && !accountName.equals("!exit")) {
									System.out.println("\nAccount already exists or name format not valid.");
									System.out.println("Please enter name for new account (or !exit):");
									accountName = scan.nextLine();
								}
								if(pass.equals("!exit")) {
									isLoggedIn = false;
									System.out.println("Successfully exited.\n");
								} else {
									User newUser = new User(email, username, pass, handler);
									Account newAccount = new Account(accountName, type, handler);
									handler.createNewUser(newUser, newAccount);
									System.out.println("User Successfully Created!\n");
								}
							}
							
						}
					}
				}
				
			// Action: Exit
			} else if(riddle.equals("!exit")) {
				System.out.println("See ya later Alligator!");
				exit = true;
				break;
			
			// Action: Invalid
			} else {
				System.out.println("Invalid Action\n");
			}
			
		}
	}
	
	private void currentlyLoggedIn() {
		String message = "";
		boolean confirmed = false;

		while(isLoggedIn) {
			clearScreen();
			
			System.out.println("Username:\t" + currentUser.getUsername());
			System.out.println("Email:\t\t" + currentUser.getEmail());
			for(int i = 0; i < currentUser.getAccounts().size(); i++) {
				System.out.println("Account:\t[" + currentUser.getAccounts().get(i).getType() + "]\t" + currentUser.getAccounts().get(i).getName() + "\t$" + currentUser.getAccounts().get(i).getBalance());
			}
			
			System.out.println();
			System.out.println("Actions: 'deposit', 'withdraw', 'transfer', 'accounts', or 'logout'");
			System.out.println(message);
			
			String action = scan.nextLine().toLowerCase().replaceAll("\\s+","");
			
			// Action: Logout
			if(action.equals("logout")) {
				isLoggedIn = false;
				System.out.println("User Logged Out Successfully.\n");
			
			// Action: Deposit
			} else if(action.equals("deposit")) {
				System.out.println("\nChoose Account By Name (!exit):");
				for(int i = 0; i < currentUser.getAccounts().size(); i++) {
					System.out.println("[" + currentUser.getAccounts().get(i).getType() + "]\t" + currentUser.getAccounts().get(i).getName() + "\t$" + currentUser.getAccounts().get(i).getBalance());
				}
				String accountName = scan.nextLine();
				
				while(currentUser.getAccountByName(accountName) == null && !accountName.equals("!exit")) {
					System.out.println("\nAccount does not exist.");
					System.out.println("\nChoose Account By Name (!exit):");
					for(int i = 0; i < currentUser.getAccounts().size(); i++) {
						System.out.println("[" + currentUser.getAccounts().get(i).getType() + "]\t" + currentUser.getAccounts().get(i).getName() + "\t$" + currentUser.getAccounts().get(i).getBalance());
					}
					accountName = scan.nextLine();
				}
				if(accountName.equals("!exit")) {
					message = "Transaction Successfully Cancelled.";
				} else {
					System.out.println("How much to deposit? (!exit)");
					String stramount = scan.nextLine().toLowerCase().replaceAll("\\s+","");
					
					if(stramount.equals("!exit")) {
						message = "Transaction Successfully Cancelled.";
					} else {
						try {
							double amount = Double.valueOf(stramount);
							if(amount < 0) {
								throw new Exception();
							}
							confirmed = currentUser.getAccountByName(accountName).depositBalance(amount);
							if(confirmed) message = "$" + amount + " has been deposited to " + currentUser.getAccountByName(accountName).getName();
							else message = "Insufficient Funds. Please try again";
							
						} catch(Exception e) {
							message = "Transaction Failed: Invalid Numeric Input";
						}
						
					}
				}
				
			// Action: Withdraw
			} else if(action.equals("withdraw")) {
				System.out.println("\nChoose Account By Name (!exit):");
				for(int i = 0; i < currentUser.getAccounts().size(); i++) {
					System.out.println("[" + currentUser.getAccounts().get(i).getType() + "]\t" + currentUser.getAccounts().get(i).getName() + "\t$" + currentUser.getAccounts().get(i).getBalance());
				}
				String accountName = scan.nextLine();
				
				while(currentUser.getAccountByName(accountName) == null && !accountName.equals("!exit")) {
					System.out.println("\nAccount does not exist.");
					System.out.println("\nChoose Account By Name (!exit):");
					for(int i = 0; i < currentUser.getAccounts().size(); i++) {
						System.out.println("[" + currentUser.getAccounts().get(i).getType() + "]\t" + currentUser.getAccounts().get(i).getName() + "\t$" + currentUser.getAccounts().get(i).getBalance());
					}
					accountName = scan.nextLine();
				}
				if(accountName.equals("!exit")) {
					message = "Transaction Successfully Cancelled.";
				} else {
					System.out.println("How much to withdraw? (!exit)");
					String stramount = scan.nextLine().toLowerCase().replaceAll("\\s+","");
					
					if(stramount.equals("!exit")) {
						message = "Transaction Successfully Cancelled.";
					} else {
						try {
							double amount = Double.valueOf(stramount);
							if(amount < 0) {
								throw new Exception();
							}
							confirmed = currentUser.getAccountByName(accountName).withdrawBalance(amount);
							if(confirmed) message = "$" + amount + " has been withdrawn from " + currentUser.getAccountByName(accountName).getName();
							else message = "Insufficient Funds. Please try again";
							
						} catch(Exception e) {
							message = "Transaction Failed: Invalid Numeric Input";
						}
						
					}
					
				}
			
			// TODO: Action: Transfer
			} else if(action.equals("transfer")){
				System.out.println("\nTransfer Between:");
				System.out.println("'My Accounts' or 'Other Accounts'");
				String option = scan.nextLine().toLowerCase().replaceAll("\\s+","");
				
				if(option.equals("myaccounts")) {
					
				}
			
			// TODO: Action: Accounts
			} else if(action.equals("accounts")) {
				message = "Accounts not yet implemented";
				
			// Action: Invalid
			} else {
				message = "Invalid Action";
				
			}
		}
	}
	
	private void clearScreen() {
		for(int i = 0; i<50; i++) {
			System.out.println();
		}
	}
	
	// Meticulous email validation from Professor OverFlow
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	private static boolean validate(String emailStr) {
	        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
	        return matcher.find();
	}
	
}
