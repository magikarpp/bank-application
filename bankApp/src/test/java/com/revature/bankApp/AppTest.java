package com.revature.bankApp;

import java.sql.Timestamp;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
	
/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
    {
	
	Handler handler = new Handler();
	
	Transaction trans1 = new Transaction("Hello from trans1", new Timestamp(new java.util.Date().getTime()), "account1");
	Transaction trans2 = new Transaction("Hello from trans2", new Timestamp(new java.util.Date().getTime()), "account2");
	
	Account account1 = new Account("account1", "checking", handler);
	Account account2 = new Account("account2", "savings", handler);
	
	User user1 = new User("tester1@tester.com", "tester1", "123", handler);
	User user2 = new User("tester2@tester.com", "tester2", "123", handler);
	
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( AppTest.class );
    }

    public void testPasswordValidation() {
    	if(!handler.userExists(user1.getEmail())) handler.createNewUser(user1, account1);

        assertTrue(handler.passwordValidation(user1.getEmail(), user1.getpass()));
    }
    
    public void testPasswordValidation2() {
    	if(!handler.userExists(user2.getEmail())) handler.createNewUser(user2, account2);
    
        assertTrue(handler.passwordValidation(user2.getEmail(), user2.getpass()));
    }
    
    public void testUserExists() {
    	if(!handler.userExists(user1.getEmail())) handler.createNewUser(user1, account1);

        assertTrue(handler.userExists(user1.getEmail()));
    }
    
    public void testWithdrawTooMuch() {
    	if(!handler.userExists(user1.getEmail())) handler.createNewUser(user1, account1);
    	account1.addOwner(user1);
    	user1.addAccount(account1);
    
        assertFalse(user1.getAccountByName(account1.getName()).withdrawBalance(100.00));
    }
    
    public void testDepositTooLarge() {
    	if(!handler.userExists(user1.getEmail())) handler.createNewUser(user1, account1);
    	account1.addOwner(user1);
    	user1.addAccount(account1);
    
        assertFalse(user1.getAccountByName(account1.getName()).depositBalance(9999999999999999.00));
    }
    
    public void testTransactionHistory() {
    	if(handler.getTransactionHistory(account1) == "") handler.updateTransaction(trans1);
    	
    	assertEquals("Hello from trans1\n", handler.getTransactionHistory(account1));
    }
}





