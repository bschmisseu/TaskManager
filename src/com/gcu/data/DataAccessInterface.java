package com.gcu.data;

import java.util.List;

/**
 * @author Bryce Schmisseur and Holland Aucoin
 * TaskManager 2.0
 * DataAccessInterface.java  2.0
 * February 16 2020
 *
 * Interface used for all Data Services that outline all the classes that the services should implement
 */

public interface DataAccessInterface<T> {

	/**
	 * CRUD: This is method to add a object to the database
	 * @param T - t: object model
	 * @return returnNum - Integer: The number of rows affected by the create
	 */
	public int create(T t);
	
	/**
	 * CRUD: This is method to update a object within the database database
	 * @param t - T: object model
	 * @return returnNum - int: The number of rows affected by the create
	 */
	public int update(T t);
	
	/**
	 * CRUD: This is method to delete a object within the database database
	 * @param id - int: The id number that the object is under in the database
	 * @return returnNum - int: The number of rows affected by the create
	 */
	public int delete(int id);
	
	/**
	 * CRUD: This is a method to read all object from the database
	 * @return list - List<T>:  A list of objects
	 */
	public List<T> viewAll();
	
	/**
	 * CRUD: This is a method to read and object based on the id number within the database
	 * @param id - int: The id number that the object is under in the database
	 * @return object - Object: object model
	 */
	public T viewById(int id);
	
	/**
	 * CRUD: This is a method to read and object based on the foreign key 
	 * @param parentId - int: foreign key linked to the object
	 * @return list - List<T>:  A list of objects
	 */
	public List<T> viewByParentId(int parentId);
	
	/**
	 * CRUD: This is a method to read and object based the object itself
	 * @param t - T: object model
	 * @return id - int: the id of the object
	 */
	public int viewByObject(T t);
}
