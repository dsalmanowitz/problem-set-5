/**
 * Just like last time, the BankAccount class is primarily responsible
 * for depositing and withdrawing money. In the enhanced version, there
 * will be the added requirement of transfering funds between accounts.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

import java.io.*;

public class BankAccount {
	private static long generatedAccountNumber = 100000001L;
	
	private double balance;
	private long accountNumber;
	private User user;
	private char status;
	
	public BankAccount (double balance, long accountNumber, User user, char status) {
		this.balance = balance;
		this.accountNumber = BankAccount.generatedAccountNumber++;
		this.user = user;
	}
	
	public BankAccount (String line) {
		accountNumber = Long.parseLong(line.substring(0, 9).trim());;
		int PIN = Integer.parseInt(line.substring(9, 13).trim());
		balance = Double.parseDouble(line.substring(13, 28).trim());
		String lname = line.substring(28, 48).trim();
		String fname = line.substring(48, 63).trim();
		int dob = Integer.parseInt(line.substring(63, 71).trim());
		int phone = Integer.parseInt(line.substring(71, 81).trim());
		String address = line.substring(81, 111).trim();
		String city = line.substring(111, 141).trim();
		String state = line.substring(141, 143).trim();
		String postalCode = line.substring(143, 148).trim();
		
		user = new User(PIN, fname, lname, dob, phone, address, city, state, postalCode);
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	public long getAccountNumber() {
		return accountNumber;
	}
	
	public char getStatus() {
		return status;
	}
	
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setStatus(char status) {
		this.status = status;
	}
	
	public int deposit(double amount) {
		if (amount <= 0) {
			return 0;
		} else {
			this.balance += amount;
			return 1;
		}
	}
	
	public int withdraw(double amount) {
		if (amount > balance) {
			return 0;
		} else if (amount <= 0) {
			return 1;
		} else {
			this.balance -= amount;
			return 2;
		}
	}
	
	public int transfer(double amount, BankAccount bankaccount) throws IOException {
		if (amount > this.balance) {
			return 0;
		} else if (amount <= 0) {
			return 1;
		} else if () {
			return 2;
		} else {
			this.balance -= amount;
			bankaccount.balance += amount;
			return 3;
		}
		database.updateAccount(bankaccount, null);
	}
}