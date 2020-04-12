package com.gcu.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Bryce Schmisseur and Holland Aucoin
 * TaskManager 2.0
 * TaskList.java  2.0
 * February 16 2020
 *
 * This is a model that stores a task list's information, with getters and setters to access that information
 */

public class TaskList {

	private int id;
	
	private int userId; 
	
	// Create list of tasks called taskList
	private List<Task> taskList;
	
	// Variable to store the task list name. Required and has size limit
	@NotNull (message = "Please enter a task list name.")
	@Size (min=4, max=45)
	private String name;
	
	// Variable to store the task list's color. Required
	@NotNull (message = "Please select a color.")
	private String color;
	
	/**
	 * Constructor for a task list
	 * @param id - int: the id number of the Task List within the database
	 * @param name - String: The name a specific task list
	 * @param color - Color: The color associated with that list
	 */
	public TaskList(int id, int userId, String name) {
		this.id = id;
		this.userId = userId; 
		this.name = name;
	}

	/**
	 * Getter method for the id of the task list
	 * @return id - int: the id number of the Task List within the database 
	 */
	public int getId()
	{
		return this.id;
	}
	
	/**
	 * Setter method for the id of the task list
	 * @param id - int: the id number of the Task List within the database 
	 */
	public void setId(int id)
	{
		this.id = id; 
	}
	
	/**
	 * Getter method for the user id
	 * @return userId - int: the id of the user that the link is linked to
	 */
	public int getUserId()
	{
		return this.userId;
	}
	
	/**
	 * Setter method for the user id
	 * @param userId - int: the id of the user that the link is linked to
	 */
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	
	/**
	 * Getter for a TaskList
	 * @return taskList - List<Task>: A full task list will be returned
	 */
	public List<Task> getTaskList() {
		return taskList;
	}

	/**
	 * Setter for a TaskList
	 * @param taskList - List<Task>: A full task list
	 */
	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	/**
	 * Getter for name
	 * @return name - String: The name of a taskList
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name
	 * @param name - String: The name of a taskList
	 */
	public void setName(String name) {
		this.name = name;
	} 
	
	/**
	 * Getter for color
	 * @return color - Color: The color associated with that task list
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Setter for color
	 * @param color - Color: The color associated with that task list
	 */
	public void setColor(String color) {
		this.color = color;
	}
}
