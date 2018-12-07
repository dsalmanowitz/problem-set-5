/**
 * Just like last time, the ATM class is responsible for managing all
 * of the user interaction. This means login procedures, displaying the
 * menu, and responding to menu selections. In the enhanced version, the
 * ATM class will have the added responsibility of interfacing with the
 * Database class to write and read information to and from the database.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

import java.util.Scanner;
import java.io.*;

public class ATM {

	static Scanner in = new Scanner(System.in);
	BankAccount account;
	private Database database;
	
	public void start() throws IOException {
		System.out.println("What would you like to do?\n1. Open Account\n2. Login\n3. Quit");
		boolean repeat = false;
		do {
			int m = in.nextInt();
			in.nextLine();
			switch (m) {
			case 1 :
				System.out.println("What is your PIN?");
				int pin = in.nextInt();
				while (Integer.toString(pin).length() != 4) {
					System.out.println("Invalid PIN number. Please try again.");
					pin = in.nextInt();
				}
				in.nextLine();
				System.out.println("What is your first name?");
				String fname = in.nextLine();
				if (fname.length() > 15) {
					fname = fname.substring(0, 15);
				}
				System.out.println("What is your last name?");
				String lname = in.nextLine();
				if (lname.length() > 20) {
					lname = lname.substring(0, 20);
				}
				System.out.println("What is your date of birth (YYYYMMDD)?");
				int dob = in.nextInt();
				in.nextLine();
				System.out.println("What is your phone number?");
				int phone = in.nextInt();
				in.nextLine();
				System.out.println("What is your street address?");
				String address = in.nextLine();
				System.out.println("What city do you live in?");
				String city = in.nextLine();
				System.out.println("What state do you live in?");
				String state = in.nextLine();
				System.out.println("What is your postal code?");
				String postalCode = in.nextLine();
				User user = new User(pin, fname, lname, dob, phone, address, city, state, postalCode);
				account = new BankAccount(0.00, database.getMaxAccountNumber()+1, user, 'Y');
				database.updateAccount(account, null);
				menu();
				repeat = false;
				break;
			case 2 : 
				System.out.println("What is your account number?");
				long acc = in.nextLong();
				in.nextLine();
				System.out.println("What is your PIN?");
				pin = in.nextInt();
				in.nextLine();
				account = database.getAccount(acc);
				if (pin == this.account.getUser().getPIN() && acc == this.account.getAccountNumber()) {
					menu();
				} else {
					System.out.println("Incorrect login information.");
				}
				repeat = true;
				break;
			case 3 :
				System.out.println("Have a nice day.");
				repeat = false;
				break;
			default : 
				System.out.println("Invalid input.");
				repeat = true;
			}
		} while (repeat == true);
	}
	
	public void menu() throws IOException{
		boolean again = true;
		while (again) {
			System.out.println("\nWhat would you like to do?\n\n1. Deposit\n2. Withdraw\n3. Check Balance\n4. Transfer Funds\n5. View Personal Information\n6. Update Personal Information\n7. Close Account\n8. Logout");
			int n = in.nextInt();
			in.nextLine();
			switch (n) {
			case 1 : 
				System.out.println("How much would you like to deposit?");
				double b = in.nextDouble();
				in.nextLine();
				switch (this.account.deposit(b)) {
				case 0 : System.out.println("Cannot deposit negative number.");
				break;
				case 1 : System.out.println("Transaction complete.");
				break;
				}
				break;
			case 2 :
				System.out.println("How much would you like to withdraw?");
				double w = in.nextDouble();
				in.nextLine();
				switch (this.account.withdraw(w)) {
					case 0 :
						System.out.println("Cannot withdraw more than balance.");
						break;
					case 1 :
						System.out.println("Cannot withdraw non-positive number.");
						break;
					case 2 : System.out.println("Transaction complete.");
				break;
				}
			case 3 :
				System.out.println("Current Balance: " + this.account.getBalance());
				break;
			case 4 :
				System.out.println("How much would you like to transfer?");
				double t = in.nextDouble();
				in.nextLine();
				System.out.println("What is the number of the account you would like to transfer funds to?");
				long tAccountNumber = in.nextLong();
				in.nextLine();
				
				if (database.getAccount(tAccountNumber) != null) {
					
				switch (this.account.transfer(t, database.getAccount(tAccountNumber))) {
					case 0 :
						System.out.println("Cannot transfer more than balance.");
						break;
					case 1 :
						System.out.println("Cannot transfer non-positive number.");
						break;
					case 2 : System.out.println("Transaction complete.");
					database.updateAccount(account, database.getAccount(tAccountNumber));
					break;
				}} else {
					System.out.println("Invalid account number.");
				}
			case 5 : 
				System.out.println("Account Number: " + account.getAccountNumber() + "\nName: " + account.getUser().getLName() + ", " + account.getUser().getFName() + "\nDOB: " + account.getUser().getDOB() + "\nAddress: " + account.getUser().getAddress() + " " + account.getUser().getCity() + ", " + account.getUser().getState() + " " + account.getUser().getPostalCode());
				break;
			case 6 :
				System.out.println("What would you like to update?\n\n1. Address\n2. Phone Number\n 3. PIN Number");
				int x = in.nextInt();
				in.nextLine();
				switch (x) {
					case 1 :
						System.out.println("Please state your new address in the following format:\nStreet Address\nCity\nState\nPostal Code");
						account.getUser().setAddress(in.nextLine());
						account.getUser().setCity(in.nextLine());
						account.getUser().setState(in.nextLine());
						account.getUser().setPostalCode(in.nextLine());
					case 2 :
						System.out.println("What is your new phone number?");
						account.getUser().setPhone(in.nextInt());
						in.nextLine();
					case 3 :
						System.out.println("What is your current PIN?");
						int oldPIN = in.nextInt();
						in.nextLine();
						System.out.println("What would you like your new PIN to be?");
						int PIN = in.nextInt();
						in.nextLine();
						account.getUser().setPIN(oldPIN, PIN);
				}
				break;
			case 7 :
				account.setStatus('N');
				break;
			case 8 :
				System.out.println("Have a nice day.");
				database.updateAccount(account, null);
				again = false;
				break;
			default :
				System.out.println("Invalid input.");
				break;
			}
		}
		if (!again) {
			start();
		}
	}
}
