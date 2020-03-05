package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.User;
import com.gcu.model.UserCredentials;

/**
 * @author Bryce Schmisseur and Holland Aucoin
 * TaskManager 2.0
 * CredntialsBusinessService.java  2.0
 * February 16 2020
 *
 * Implementation of CredentialsBusinessInterface for the user model
 */

public class CredentialsBusinessService implements CredentialsBusinessInterface {

	@Autowired
	private DataAccessInterface<User> doa; 
	
	/**
	 * @see CredentialsBusinessService.authenticateUser();
	 */
	@Override
	public int authenticateUser(UserCredentials user) 
	{
		//Initialize variable to set the user valid or not
		int userID = 0;
		
		//Gets the full list of users from the Database
		List<User> userList = doa.viewAll();
		
		
		//For loop to iterate through the list of users to match the credentials
		for(int i = 0; i < userList.size(); i ++)
		{
			if(userList.get(i).getUsercredentials().getUsername().equals(user.getUsername()) && 
					userList.get(i).getUsercredentials().getPassword().equals(user.getPassword()))
			{
				userID = userList.get(i).getId();
				break;
			}
		}
	
		return userID;
	}

	/**
	 * @see CredentialsBusinessService.registerUser();
	 */
	@Override
	public int registerUser(User user) 
	{
		return doa.create(user);
	}

	/**
	 * @see CredentialsBusinessService.viewAllUsers();
	 */
	@Override
	public List<User> viewAllUsers() 
	{
		return doa.viewAll();
	}

	/**
	 * @see CredentialsBusinessService.viewUserById();
	 */
	@Override
	public User viewUserById(int id) 
	{
		return doa.viewById(id);
	}
	
	
	
}
