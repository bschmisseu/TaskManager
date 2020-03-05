package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Bryce Schmisseur and Holland Aucoin
 * TaskManager 2.0
 * Task.java  2.0
 * February 16 2020
 *
 * This is a model that stores a task's information, with getters and setters to access that information
 */

public class Task {

	private int id;
	
	private int listId;
	
	// Variable to store the task description. Required and has size limit
	@NotNull (message = "Please enter a description. This is a required field.")
	@Size (min=2, max=50, message="Sorry, the description has to be between 2 and 50 characters.")
	private String description;
	
	// Variable to store the task date. Required
	@NotNull (message = "Please enter a date. This is a required field.")
	private String completedBy;
	
	// Variable to store the task completed status. Required
	private boolean completed;

	private String completedString = "";

	/**
	 * Constructor for a task
	 * @param id - int: id number for the task within the database
	 * @param description - String: A description of the task
	 * @param completedBy - Date: The target date that a user wants the task completed by
	 * @param completed - Boolean: The status of the task, completed or not
	 */
	public Task(int id, int listId, String description, String completedBy, boolean completed) {
		this.id = id; 
		this.listId = listId;
		this.description = description;
		this.completedBy = completedBy;
		this.completed = completed;
		
		if(completed)
		{
			completedString = "checked";
		}
	}
	
	/**
	 * Empty constructor for a task
	 */
	public Task() {
		this.id = 0; 
		this.listId = -1;
		this.description = "";
		this.completedBy = "";
		this.completed = false;
	}

	/**
	 * Getter method for the task's id
	 * @return id - int: id number for the task within the database
	 */
	public int getId()
	{
		return this.id; 
	}
	
	/**
	 * Setter method for the task's id
	 * @param id - int: id number for the task within the database
	 */
	public void setId(int id)
	{
		this.id = id;
	}
	
	/**
	 * Getter method for the task list id
	 * @return listId - int: the id number of the list 
	 */
	public int getListId()
	{
		return this.listId;
	}
	
	/**
	 * Setter method for the task list id
	 * @param listId - int: the id number of the list
	 */
	public void setListId(int listId)
	{
		this.listId = listId;
	}
	
	/**
	 * Getter for description
	 * @return description - String: The description of the task
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Setter for description
	 * @param description - String: The description of the task
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Getter for completedBy
	 * @return completedBy - Date: The date that the user wants to complete the task by
	 */
	public String getCompletedBy() {
		return completedBy;
	}
	
	/**
	 * Setter for completedBy
	 * @param completedBy - Date: The date that the user wants to complete the task by
	 */
	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}
	
	/**
	 * Getter for completed
	 * @return completed - Boolean: The true or false of if the task has been completed
	 */
	public boolean isCompleted() {
		return completed;
	}
	
	/**
	 * Setter for completed
	 * @param completed - Boolean: The true or false of if the task has been completed
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	} 
	
	/**
	 * Getter method for the completed String variable
	 * @return completedString - string: String used to mark the check box
	 */
	public String getCompletedString() {
		return completedString;
	}

	/**
	 * Setter method for the completed String variable
	 * @param completedString - string: String used to mark the check box
	 */
	public void setCompletedString(String completedString) {
		this.completedString = completedString;
	}
}
