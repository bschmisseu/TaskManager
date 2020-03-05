package com.gcu.business;

import java.util.List;

import com.gcu.model.User;
import com.gcu.model.UserCredentials;

/**
 * @author Bryce Schmisseur and Holland Aucoin
 * TaskManager 2.0
 * CredntialsBusinessInterface.java  2.0
 * February 16 2020
 *
 * Business Interface for an outline of the user business method 
 */

public interface CredentialsBusinessInterface {
	
	/**
	 * Method to authenticate a user. Checks for match in database, returns true or false
	 * @param userCred - UserCredentials: The user's login credentials to enter the website
	 * @return validUser - Boolean: Checks the validity of a user (if they are in database)
	 */
	public int authenticateUser(UserCredentials user);
	
	/**
	 * Method to register a new user
	 * @param user - User: The user's account information from registering
	 * @return registered - Integer: The number of rows affected by a user registration
	 */
	public int registerUser(User user);
	
	/**
	 * Method to view all users
	 * @return users - List<User>: A list of users
	 */
	public List<User> viewAllUsers();
	
	/**
	 * Method to view a user be a specific user id
	 * @param id - int: the id number for the user within the database
	 * @return user - User: User model containing the users information
	 */
	public User viewUserById(int id);
	
}
