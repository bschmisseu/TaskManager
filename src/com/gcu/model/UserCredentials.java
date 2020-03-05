package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Bryce Schmisseur and Holland Aucoin
 * TaskManager 2.0
 * UserCredentials.java  2.0
 * February 16 2020
 *
 * This is a model that stores the user's login information, with getters and setters to access that information
 */

public class UserCredentials
{
	// Variable to store the user's username, used for login. Required and ahs size limit
	@NotNull (message = "Please enter a Username. This is a required field.")
	@Size (min=2, max=25, message="Sorry, Username has to be between 2 and 25 characters.")
	private String username;
	
	// Variable to store the user's password, used for login. Required and ahs size limit
	@NotNull (message = "Please enter a Password. This is a required field.")
	@Size (min=6, max=25, message="Sorry, Password has to be between 6 and 25 characters.")
	private String password;
	
	/**
	 * Constructor for a user's credentials
	 */
	public UserCredentials() {
		username = "";
		password = "";
	}
	
	/**
	 * Constructor for a user's credentials
	 * @param username - String: The username a user has created to use to login
	 * @param password - String: The password a user has created to use to login
	 */
	public UserCredentials(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Getter for username
	 * @return username - String: The username for a user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter for username
	 * @param username - String: The username for a user
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Getter for password
	 * @return password - String: The password for a user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter for password
	 * @param password - String: The password for a user
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
