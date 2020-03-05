package com.gcu.business;

import java.util.List;

/**
 * @author Bryce Schmisseur and Holland Aucoin
 * TaskManager 2.0
 * BusinessInterface.java  2.0
 * February 16 2020
 *
 * Business Interface for an outline of the models business method 
 */

public interface BusinessInterface<T> {
	
	/**
	 * Method to object a new task list
	 * @param object - object: A full object
	 * @return created - Integer: The number of rows affected by the creation
	 */
	public int create(T t);
	
	/**
	 * Method to update a object, given the object and the corresponding ID
	 * @param taskList - TaskList: A full object
	 * @param Id - Integer: The ID number of a given object
	 * @return updated - Integer: The number of rows affected by the update
	 */
	public int update(T t);
	
	/**
	 * Method to delete a object, given the id
	 * @param Id - Integer: The ID number of a given object
	 * @return deleted - Integer: The number of rows affected by the deletion
	 */
	public int delete(int id);
	
	/**
	 * Method to view all object
	 * @return object - List: A full list of all the objects
	 */
	public List<T> viewAll();
	
	/**
	 * Method to view all object by id
	 * @return object - List: A full list of all the objects
	 */
	public T viewById(int id);
	
	/**
	 * Method to view all object based on the parent id
	 * @return object - List: A full list of all the objects
	 */
	public List<T> viewByParentId(int parentId);
	
	/**
	 * Method to view all object based on the object
	 * @return object - List: A full list of all the objects
	 */
	public int viewByObject(T t);
}
