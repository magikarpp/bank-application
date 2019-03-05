package com.revature.bankApp;

import java.io.Serializable;
import java.sql.Timestamp;

public class Transaction implements Serializable{

	private static final long serialVersionUID = -3575431010942249380L;
	
	private String message;
	private Timestamp date;
	private String accountName;
	
	public Transaction(String message, Timestamp date, String accountName) {
		this.message = message;
		this.date = date;
		this.setAccountName(accountName);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
