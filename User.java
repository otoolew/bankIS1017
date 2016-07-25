package bankIS1017;

import java.util.Date;

/**
 * Assignment6 User
 *
 * @author William O'Toole
 */
public class User {

	// Declaring Instance Variables
	private int userID;
	private String lastName;
	private String firstName;
	private Date dateOfBirth;
	private String address;
	private String userType;
	private String ssn;
	private boolean isUserActive;

	// User(firstName, lastName, dateOfBirth, userType, ssn)
	public User(String fn, String ln, Date dob, String ut, String socNum) {
		this.firstName = fn;
		this.lastName = ln;
		this.dateOfBirth = dob;
		this.userType = ut;
		this.ssn = socNum;
		this.isUserActive = true;
	}

	// Constructor with All Parameters
	public User(int uid, String ln, String fn, Date dob, String add, String ut, String socNum, boolean userAct) {
		this.userID = uid;
		this.lastName = ln;
		this.firstName = fn;
		this.dateOfBirth = dob;
		this.address = add;
		this.userType = ut;
		this.ssn = socNum;
		this.isUserActive = userAct;
	}

	// Getter UserID
	public int getUserID() {
		return userID;
	}

	// Setter UserID
	public void setUserID(int userID) {
		this.userID = userID;
	}

	// Getter Last Name
	public String getLastName() {
		return lastName;
	}

	// Setter Last Name
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// Getter First Name
	public String getFirstName() {
		return firstName;
	}

	// Setter First Name
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// Getter DOB
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	// Setter DOB
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	// Getter Address
	public String getAddress() {
		return address;
	}

	// Setter Address
	public void setAddress(String address) {
		this.address = address;
	}

	// Getter UserType
	public String getUserType() {
		return userType;
	}

	// Setter UserType
	public void setUserType(String userType) {
		this.userType = userType;
	}

	// Getter SSN
	public String getSsn() {
		return ssn;
	}

	// Setter SSN
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	// Getter Active Status
	public boolean getActive() {
		return this.isUserActive;
	}

	// Setter Active Status
	public void setActive(boolean active) {
		this.isUserActive = active;
	}

	// Override the toString to format my own print out of the Account Object
	@Override
	public String toString() {
		String result;
		result = "User ID " + getUserID() + "\nName: " + getFirstName() + " " + getLastName() + "\nDate of Birth: "
				+ getDateOfBirth() + "\nAddress: " + getAddress() + "\nUser Type: " + userType + "\nSSN: " + getSsn();
		return result;

	}
}
