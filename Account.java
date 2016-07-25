package bankIS1017;

//Import Statements
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * Assignment6 Account
 * 
 * @author William O'Toole
 */
public class Account {

	// Declaring Instance Variables
	private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
	private static Random rand = new Random();
	private int number;
	private String accountType;
	private String accountStatus;
	private double balance;
	private Date dateOpened;
	private User openedBy;
	private Date lastTransaction;
	private int numberOfDeposits;
	private double interestRate;
	private boolean yearOldStatus;

	// User only parameter Constructer
	public Account(User opBy) {
		openedBy = opBy;
	}

	// + Account(accountType:String, initialBalance : double)
	public Account(String actType, double initialBal) {
		accountType = actType;
		balance = initialBal;
	}

	// + Account(initialBalance : double)
	public Account(double initialBal) {
		balance = initialBal;
	}

	// + Account(accountType:String, initialBalance : double, owner : User)
	public Account(String actType, double initialBal, User owner) {
		number = accountNumGenerator();
		openedBy = owner;
		accountType = actType;
		balance = initialBal;
		dateOpened = new Date();
		lastTransaction = new Date();
		accountStatus = "Active";
		interestRate = 1.05;
	}

	// + Account(initialBalance : double, owner : User)
	public Account(double initialBal, User owner) {
		balance = initialBal;
		openedBy = owner;
	}

	// Getter Only
	public int getNumber() {
		return number;
	}

	// Getter Only
	public String getAccountType() {
		return accountType;
	}

	// Setter
	public void setAccountStatus(String status) {
		accountStatus = status;
	}

	// Getter
	public String getAccountStatus() {
		return accountStatus;
	}

	// Getter Only
	public double getBalance() {
		return balance;
	}

	// Getter Only
	public Date getDateOpened() {
		return dateOpened;
	}

	// Getter Only
	public User getOpenedBy() {
		return openedBy;
	}

	// Getter Only
	public Date getLastTransaction() {
		return lastTransaction;
	}

	// Getter Only
	public int getNumberOfDeposits() {
		return numberOfDeposits;
	}

	// Getter interestRate
	public double getInterestRate() {
		return this.interestRate;
	}
	// Setter interestRate
	public void setInterestRate(double intRate) {
		this.interestRate = intRate;
	}
	// Method used in last assignment, I didnt use it but I kept it anyway.
	// createAccount(accountType:String initialBalance:double):int
	public int createAccount(String acctType, double initialBalance) {
		// Setting instance variables inside the method
		number = openedBy.getUserID();
		balance = initialBalance;
		accountType = acctType;
		accountStatus = "Active";
		dateOpened = new Date();
		lastTransaction = new Date();
		// Returning Account Number
		return number;
	}

	// Deposit Method
	public double deposit(double amount) throws ParseException {
		// Declaring Boolean Variable to store method return value
		boolean isValid = validateAccount();
		// if statement that checks if the account is valid
		if (isValid) {
			// Adding the deposit amount to the balance
			balance = balance + amount;
			// setting the lasttranaction to the moment it happens
			lastTransaction = new Date();
			// Checks if amount is over 100 NOT == to as Assignment objectives state
			// Using boolean return value from method getOneYearAgo
			if (amount > 100 && getOneYearAgo()) {
				// if both true calls method accrueInterest()
				accrueInterest();
			}
			// Increasing number of deposits by 1
			numberOfDeposits++;
		}
		// returning the balance
		return balance;
	}
	// Date has many deprecated methods
	@SuppressWarnings("deprecation")
	private boolean getOneYearAgo() throws ParseException {
		// need to add 11 because thats what the API says to do
		int day = dateOpened.getDay() + 11;
		// need to add 1 because thats what the API says January = 0
		int month = dateOpened.getMonth() + 1;
		// need to add 1900 because thats what the API says to do and minus one to get one year ago
		int year = dateOpened.getYear() + 1900 - 1;
		// Creating string with above values
		String oneYearAgo = day + "/" + month + "/" + year;
		// parsing format
		Date yearAgo = dateFormat.parse(oneYearAgo);
		// returning boolean if the date opened before one year ago
		return dateOpened.before(yearAgo);
	}

	// Withdraw Method
	public double withdraw(double amount) {
		// calling method to validate
		boolean isValid = validateAccount();
		if (isValid) {
			// storign a temp calculation
			double newBalance = balance - amount;
			// making sure there is enough
			if (newBalance < 0) {
				// if true Display Disapproved Message
				JOptionPane.showMessageDialog(null, "Disapproved" + " Insufficient Funds");
				// return the balance unchanged
				return balance;
			}
			// if valid sets balance to the newBalance
			balance = newBalance;
			// Setting last Transaction date
			lastTransaction = new Date();
		}
		// returning balance
		return balance;

	}

	// My Account Number Generator
	private static Integer accountNumGenerator() {
		// Used StringBuilder because it is super easy to use.
		StringBuilder strBuild = new StringBuilder();
		// The length of the number I wanted to identify the account
		int numLength = 9;
		// A for loop that loops 9 times
		for (int i = 0; i < numLength; i++) {
			// Using Random to get a number between 0 and 9
			int randNum = rand.nextInt(9);
			// Taking the randomly generated Number and Appending to the end of
			// the StringBuilder
			strBuild.append(randNum);
		}
		// Parsing the String to an
		return Integer.parseInt(strBuild.toString());
	}

	// - accrueInterest()
	private void accrueInterest() {
		balance = interestRate * balance;
	}

	// - validateAccount():boolean
	private boolean validateAccount() {
		// Checks balance before tranactions for positive amount
		if (!(balance > -1)) {
			// Displaying Message why tranaction failed.
			JOptionPane.showMessageDialog(null, "Disapproved" + " Insufficient Funds");
			return false;
		}
		if (!(accountStatus.equals("Active"))) {
			// Displaying Message why tranaction failed.
			JOptionPane.showMessageDialog(null, "Disapproved" + " Account Not Active");
			return false;
		}
		if (!(openedBy.getActive())) {
			// Displaying Message why tranaction failed.
			JOptionPane.showMessageDialog(null, "Disapproved" + " User Not Active");
			return false;
		}
		// if all checks pass then returns true
		return true;
	}

	// Override the toString to format my own print out of the Account Object
	@Override
	public String toString() {
		String result;
		result = "Number: " + number + "\nBalance: " + currencyFormat.format(balance) + "\nOpened by: "
				+ openedBy.getFirstName() + " " + openedBy.getLastName() + "\nType: " + accountType + "\nStatus: "
				+ accountStatus + "\nDate Opened: " + dateOpened.toString() + "\nNumber of Deposits: "
				+ numberOfDeposits + "\nLast Transaction: " + lastTransaction + "\nYear Old Status: " + yearOldStatus;
		return result;
	}
}