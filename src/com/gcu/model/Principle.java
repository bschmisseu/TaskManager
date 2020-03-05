package com.gcu.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Bryce Schmisseur and Holland Aucoin
 * TaskManager 2.0
 * Principle.java  2.0
 * February 16 2020
 *
 * This is a model used as a session to be able to store information throught a session scoped time
 */

@Component
@Scope("session")
public class Principle {
	
	//The current users id
	private User currentUser;
	
	//The current list that is selected 
	private int currentListId;
	
	//The current list based on the index of the users list
	private int currentListIdByUser;
	
	/**
	 * Default Constructor
	 */
	public Principle()
	{
		currentUser = new User();
	}

	/**
	 * Getter method for the currentUser property
	 * @return currentUser - int: The current users id
	 */
	public User getCurrentUser() 
	{
		return currentUser;
	}
	
	/**
	 * Setter method for the currentUser property
	 * @param currentUser - int: The current users id
	 */
	public void setCurrentUser(User currentUser) 
	{
		this.currentUser = currentUser;
	}
	
	/**
	 * Getter method for the currentListIdByUser property
	 * @return currentListIdByUser- int: the current list based on the index of the users list
	 */
	public int getCurrentListIdByUser() {
		return currentListIdByUser;
	}

	/**
	 * Setter method for the currentListIdByUser property
	 * @param currentListIdByUser- int: the current list based on the index of the users list
	 */
	public void setCurrentListIdByUser(int currentListIdByUser) {
		this.currentListIdByUser = currentListIdByUser;
	}

	/**
	 * Getter method for the currentListId property
	 * @return currentListId - int: the current list that is selected 
	 */
	public int getCurrentListId() {
		return currentListId;
	}

	/**
	 * Setter method for the currentListId property
	 * @param currentListId - int: the current list that is selected 
	 */
	public void setCurrentListId(int currentListId) {
		this.currentListId = currentListId;
	}
}
