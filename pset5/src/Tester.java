/**
 * This class has only one responsibility: start the ATM program!
 */
import java.io.*;
public class Tester {
	
	/**
	 * Main method.
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) throws IOException {
		
		ATM atm = new ATM();
		atm.start();
	}
		/*
		 * Rather than hard coding one or more BankAccount objects, you'll need to read them in
		 * from our very primitive database (i.e., a flat-file). After making changes, of course,
		 * you'll need to update the database accordingly.
		 */
		
}
