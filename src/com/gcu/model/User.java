package com.gcu.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Bryce Schmisseur and Holland Aucoin
 * TaskManager 2.0
 * User.java  2.0
 * February 16 2020
 *
 * User object model to store some of the users information
 */

public class User 
{
	private int id;
	
	@NotNull (message = "Please enter a First Name. This is a required field.")
	@Size (min=2, max=100, message="Sorry, First Name has to be between 2 and 100 characters.")
	private String firstName;
	
	@NotNull (message = "Please enter a Last Name. This is a required field.")
	@Size (min=2, max=100, message="Sorry, Last Name has to be between 2 and 100 characters.")
	private String lastName;
	
	@NotNull (message = "Please enter a Phone Number. This is a required field.")
	@Pattern(regexp="([0-9]{3}+-[0-9]{3}+-[0-9]{4})|([0-9]{10})", message="Please enter a valid phone number.")
	private String phoneNumber;
	
	@NotNull (message = "Please enter a Email. This is a required field.")
	@Pattern(regexp="[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message="Please enter a valid email.")
	private String email;
	
	@Valid
	private UserCredentials usercredentials;
	
	
	/**
	 * Constructor for the user
	 * @param id - int: the users id number within the databse
	 * @param firstName - String: The user's first name
	 * @param lastName - String: The user's last name
	 * @param username - String: The user's username used to login
	 * @param password - String: The user's password used to login
	 * @param phoneNumber - String: The user's phoneNumber
	 * @param email - String: The user's email address
	 */
	public User(int id, String firstName, String lastName, String phoneNumber, String email, String username, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		
		UserCredentials userCred = new UserCredentials(username, password);
		this.setUsercredentials(userCred);
	}
	
	/**
	 * Empty constructor for the user
	 */
	public User() 
	{
		this.id = 0; 
		this.firstName = "";
		this.lastName = "";
		this.phoneNumber = "";
		this.email = "";
		this.usercredentials = new UserCredentials();
	}
	
	/**
	 * Getter for the users id number
	 * @return id - int: The users id number within the database
	 */
	public int getId()
	{
		return this.id;
	}
	
	/**
	 * Setter for the users id number
	 * @param id - int: The users id number within the database
	 */
	public void setId(int id)
	{
		this.id = id; 
	}
	
	/**
	 * Getter for first name
	 * @return firstName - String: The first name of a specific user
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Setter for first name
	 * @param firstName - String: The first name of a specific user
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Getter for last name
	 * @return lastName - String: The last name of a specific user
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Setter for last name
	 * @param lastName - String: The last name of a specific user
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Getter for phone number
	 * @return phoneNumber - String: The phone number of a specific user
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * Setter for phone number
	 * @param phoneNumber - String: The phone number of a specific user
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Getter for email
	 * @return email - String: The email of a specific user
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Setter for email
	 * @param email - String: The email of a specific user
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter for a user's credentials
	 * @return usercredentials - String: The user credentials of a specific user
	 */
	public UserCredentials getUsercredentials() {
		return usercredentials;
	}

	/**
	 * Setter for a user's credentials
	 * @param usercredentials - String: The user credentials of a specific user
	 */
	public void setUsercredentials(UserCredentials usercredentials) {
		this.usercredentials = usercredentials;
	}
}
