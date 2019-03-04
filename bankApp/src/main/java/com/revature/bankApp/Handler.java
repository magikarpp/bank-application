package com.revature.bankApp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Handler {
	
	// Grab user by email from TABLE BankUser (adds all associated accounts to grabbed user).
	public User grabUserByEmail(String email) {
		
		User tempUser = new User(this);
		Account tempAccount = null;
		
		// Create the user
		String sql = "SELECT * FROM BankUser WHERE UserEmail = ?";	
		
		try(Connection con = ConnectionUtil.getConnectionFromFile();
			PreparedStatement ps = con.prepareStatement(sql);){
			
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String userEmail = rs.getString("UserEmail");
				String userName = rs.getString("UserName");
				String userPassword = rs.getString("UserPassword");
				tempUser = new User(userEmail, userName, userPassword, this);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// Create the accounts associated with the user
		String sql2 = "SELECT a.* FROM BankAccount a, BankUser u, UserAccount ua WHERE a.accountname = ua.accountname AND ua.useremail = u.useremail AND u.useremail = ?";
		
		try(Connection con = ConnectionUtil.getConnectionFromFile();
			PreparedStatement ps = con.prepareStatement(sql2);){
			
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String accName = rs.getString("AccountName");
				String accType = rs.getString("AccountType");
				Double accBalance = rs.getDouble("AccountBalance");
				tempAccount = new Account(accName, accType, accBalance, this);
				tempUser.addAccount(tempAccount);
	
			}
				
				
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// SetOwners for each account in user
		for(int i = 0; i < tempUser.getAccounts().size(); i++) {
			setOwnersOfAccount(tempUser.getAccounts().get(i));
		}
		
		return tempUser;
	}
	
	private void setOwnersOfAccount(Account account) {
		
		User tempUser = null;
		String sql = "SELECT u.* FROM BankAccount a, BankUser u, UserAccount ua WHERE a.accountname = ua.accountname AND ua.useremail = u.useremail AND a.accountname = ?";
		
		try(Connection con = ConnectionUtil.getConnectionFromFile();
			PreparedStatement ps = con.prepareStatement(sql);){
			
			ps.setString(1, account.getName());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String userEmail = rs.getString("UserEmail");
				String userName = rs.getString("UserName");
				String userPassword = rs.getString("UserPassword");
				tempUser = new User(userEmail, userName, userPassword, this);
				account.addOwner(tempUser);
				
			}

				
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public boolean createNewUser(User user, Account account) {
		updateUser(user);
		updateAccount(account);
		connect(user, account);
		
		return true;
	}
	
	// Update user in TABLE BankUser with input user (maybe).
	//			(sign up) If user not in database, add user.
	public int updateUser(User user) {
		int userUpdated = 0;
		
		boolean exists = accountExists(user.getEmail());
		
		if(exists) {
			String sql = "UPDATE BankUser SET UserEmail = ?, UserName = ?, UserPassword = ? WHERE UserEmail = ?";
			
			try(Connection con = ConnectionUtil.getConnectionFromFile();
				PreparedStatement ps = con.prepareStatement(sql);){
				
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getUsername());
				ps.setString(3, user.getpass());
				ps.setString(4, user.getEmail());
				userUpdated = ps.executeUpdate();
					
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} else {
			
			String sql = "INSERT INTO BankUser VALUES (?, ?, ?)";
			
			try(Connection con = ConnectionUtil.getConnectionFromFile();
				PreparedStatement ps = con.prepareStatement(sql);){
				
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getUsername());
				ps.setString(3, user.getpass());
				userUpdated = ps.executeUpdate();
					
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		return userUpdated;
		
	}
	
	// (sign up/new account) updates TABLE UserAccount with PK of user and account
	public int connect(User user, Account account) {
		int userAccountUpdated = 0;
		
		String sql = "INSERT INTO UserAccount VALUES (?, ?)";
		
		try(Connection con = ConnectionUtil.getConnectionFromFile();
			PreparedStatement ps = con.prepareStatement(sql);){
			
			ps.setString(1, user.getEmail());
			ps.setString(2, account.getName());
			userAccountUpdated = ps.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		return userAccountUpdated;
		
	}
	
	// (deposit/withdraw) Updates TABLE BankAccount with the current information in specified account.
	public int updateAccount(Account account) {
		int accountsUpdated = 0;
		
		boolean exists = accountExists(account.getName());
		
		if(exists) {
			String sql = "UPDATE BankAccount SET AccountName = ?, AccountType = ?, AccountBalance = ? WHERE AccountName = ?";
			
			try(Connection con = ConnectionUtil.getConnectionFromFile();
				PreparedStatement ps = con.prepareStatement(sql);){
				
				ps.setString(1, account.getName());
				ps.setString(2, account.getType());
				ps.setDouble(3, account.getBalance());
				ps.setString(4, account.getName());
				accountsUpdated = ps.executeUpdate();
					
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} else {
			
			String sql = "INSERT INTO BankAccount VALUES (?, ?, ?)";
			
			try(Connection con = ConnectionUtil.getConnectionFromFile();
				PreparedStatement ps = con.prepareStatement(sql);){
				
				ps.setString(1, account.getName());
				ps.setString(2, account.getType());
				ps.setDouble(3, account.getBalance());
				accountsUpdated = ps.executeUpdate();
					
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		return accountsUpdated;
	}
	
	// Check to see if user exists in TABLE BankUser
	public boolean userExists(String email) {
		boolean exists = false;
		
		String sql = "SELECT * FROM BankUser WHERE UserEmail = ?";	
		
		try(Connection con = ConnectionUtil.getConnectionFromFile();
			PreparedStatement ps = con.prepareStatement(sql);){
			
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				exists = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return exists;
	}
	
	// Check to see if user and password combo in TABLE BankUser
	public boolean passwordValidation(String email, String pass) {
		boolean validate = false;
		
		String sql = "SELECT * FROM BankUser WHERE UserEmail = ? AND UserPassword = ?";	
		
		try(Connection con = ConnectionUtil.getConnectionFromFile();
			PreparedStatement ps = con.prepareStatement(sql);){
			
			ps.setString(1, email);
			ps.setString(2, pass);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				validate = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return validate;
	}
	
	// Check to see if account exists in TABLE BankAccount
	public boolean accountExists(String accountName) {
		boolean exists = false;
		
		String sql = "SELECT * FROM BankAccount WHERE AccountName = ?";	
		
		try(Connection con = ConnectionUtil.getConnectionFromFile();
			PreparedStatement ps = con.prepareStatement(sql);){
			
			ps.setString(1, accountName);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				exists = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return exists;
	}
	
	public boolean transferFunds(Account account1, Account account2, double amount) {
		boolean success;
		
		if(!account1.withdrawBalance(amount) || !account2.depositBalance(amount)) {
			success = false;
		} else {
			account1.withdrawBalance(amount);
			account2.depositBalance(amount);
			success = true;
		}
		
		return success;
	}

}
