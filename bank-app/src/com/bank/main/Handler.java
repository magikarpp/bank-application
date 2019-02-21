package com.bank.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<User> users = new LinkedList<User>();
	
	private static String path = "src/com/bank/main/db.txt";
	
	public void addUser(String email, String username, String password) {
		
		updateUsers();
		
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getEmail().equals(email)) {
				System.out.println("Invalid Email: This email is already registered.\n");
				return;
			}
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))){
			User tempUser = new User(email, username, password, 0, false, this);
			bw.append(tempUser.getEmail() + " " + tempUser.getUsername() + " " + tempUser.getPassword() + " " + tempUser.getBalance() + " " + tempUser.isActive() + "\n");
			
			users.add(tempUser);
			System.out.println("User successfully created.\n");
			
			bw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		
	}
	
	public void removeUser(String email) {
		updateUsers();
		
		
	}
	
	// Updates the db.txt with the current information in linked list "user"
	public void updateUser(User user) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
			
			bw.write("");
			
			bw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))){
			for(int i = 0; i < users.size(); i++) {
				bw.append(users.get(i).getEmail() + " " + users.get(i).getUsername() + " " + users.get(i).getPassword() + " " + users.get(i).getBalance() + " " + users.get(i).isActive() + "\n");
			}
			
			bw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	// Updates the linked list "user" with the current information in db.txt
	public void updateUsers() {
		
		users.clear();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String userStr = br.readLine();
			
			while(userStr != null) {
				String[] userInfo = userStr.split(" ");
				users.add(new User(userInfo[0], userInfo[1], userInfo[2], Double.valueOf(userInfo[3]), Boolean.valueOf(userInfo[4]), this));
				userStr = br.readLine();
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
