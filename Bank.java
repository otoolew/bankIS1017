package bankIS1017;

//Import Statements
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 * Assignment6 Bank
 * 
 * @author William O'Toole
 */
public class Bank {

	// Declaring Instance Variables
	// Must be static cause they are referenced in static methods
	// Placed above the main so they can me used in the whole class. Scope!
	private static User user;
	private static Account userAccount;
	private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

	// Main

	public static void main(String[] args) throws FileNotFoundException, ParseException, IOException {

		// Using a 'combo' of Boolean and While to handle the main state loop
		boolean cont = true;
		while (cont) {
			// Display and Input for Menu selection
			String menuChoice = JOptionPane.showInputDialog(
					"Welcome Bank Customer!" + "\n'L' = Login as Default User" + "\n'Q' = Exit Pogram");
			// Handling both Uppercase and Lowercase Input
			menuChoice = menuChoice.toUpperCase();
			// switch statement to handle multipul choices
			switch (menuChoice) {
			// Case L
			case "L":
				createDefaultUser();
				accountManage();
				break;
			// Case Q
			case "Q":
				// Displaying Goodbye Message
				JOptionPane.showMessageDialog(null, "Goodbye!");
				// Seeting continue variable to false so Program exits.
				cont = false;
				break;// needed after every case or the next case will execute.
			default:
				// If none of the choices are valid the program resets to the
				// main menu.
				JOptionPane.showMessageDialog(null, "Option not available. Please Choose a VALID Option.");
			}
		}
	}

	public static void createDefaultUser() throws ParseException {
		// Create an instance of User class with the following parameters:
		// First name: John
		String firstName = "John";
		// Last name: Doe
		String lastName = "Doe";
		// Date of birth: 12/17/1985
		String dateInput = "17/12/1985";
		// Using Date Format to accept a more simple Date input and outputiing
		// the Date() string
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// Try Catch to Validate Input
		Date dateOfBirth = dateFormat.parse(dateInput);
		// User type: customer
		String usertype = "Customer";
		// SSN: 234-54-7524
		String ssn = "234-54-7524";
		// User(firstName, lastName, dateOfBirth, userType, ssn)
		// Creating new User object instance
		user = new User(firstName, lastName, dateOfBirth, usertype, ssn);
	}

	public static void accountManage() {
		// Initializing Balance to 1000
		double initialBal = 1000;
		// Initializing account type to checking
		String actType = "Checking";
		// creating userAccount Object using proper constructor
		userAccount = new Account(actType, initialBal, user);
		// Message telling user that the account was created successfully
		JOptionPane.showMessageDialog(null, "Account Successfully Created" + "\n" + userAccount.toString());
		// Using a 'combo' of Boolean and While to handle the main state loop
		boolean cont = true;
		while (cont) {
			// Display and Input for Menu selection
			String menuChoice = JOptionPane.showInputDialog("Welcome " + user.getFirstName() + "\nAccount Balance: "
					+ currencyFormat.format(userAccount.getBalance()) + "\n" + "\n'D' = Make a Deposit"
					+ "\n'W' = Withdraw" + "\n'P' = Print Account Review" + "\n'Q' = Exit Pogram");
			// Handling both Uppercase and Lowercase Input
			menuChoice = menuChoice.toUpperCase();
			// switch statement to handle multipul choices
			switch (menuChoice) {
			// Case D
			case "D":
				String depositAmount = JOptionPane.showInputDialog("Enter the Amount you would like to Deposit.");
				// try statement will 'try' the code within the block.
				// If an error is thrown, the catch will execute and return
				// theuser to the Main Menu
				try {
					Double deposit = Double.parseDouble(depositAmount);
					userAccount.deposit(deposit);

					break;
				} // catch statement that will execute if an error occurs in the
					// try block.
				catch (Exception e) {
					// goodInput=false;
					JOptionPane.showMessageDialog(null, "Invalid Input Type Please Try Again. Going back to Main Menu");
				}
				break;
			// Case P
			case "P":
				// Display Properties of the userAccount
				JOptionPane.showMessageDialog(null, userAccount.toString());
				break;
			case "W":
				// Accepting Input for Withdraw
				String withdrawAmount = JOptionPane.showInputDialog("Enter the Amount you would like to Withdraw.");
				// try statement will 'try' the code within the block.
				// If an error is thrown, the catch will execute and return
				// theuser to the Main Menu
				try {
					// Trying to parse Input
					Double withdraw = Double.parseDouble(withdrawAmount);
					// Callin gmethod to withdraw money from account
					userAccount.withdraw(withdraw);
					break;
				} // catch statement that will execute if an error occurs in the
					// try block.
				catch (Exception e) {
					// goodInput=false;
					JOptionPane.showMessageDialog(null, "Invalid Input Type Please Try Again. Going back to Main Menu");
				}
				break;
			// Case Q
			case "Q":
				// Displaying Goodbye Message
				JOptionPane.showMessageDialog(null, "Returning to Main Menu");
				// Seeting continue variable to false so Program exits.
				cont = false;
				break;// needed after every case or the next case will execute.
			default:
				// If none of the choices are valid the program resets to the
				// main menu.
				JOptionPane.showMessageDialog(null, "Option not available. Please Choose a VALID Option.");
			}
		}
	}
}
