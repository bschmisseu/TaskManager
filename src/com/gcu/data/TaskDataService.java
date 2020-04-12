package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.exception.DatabaseException;
import com.gcu.model.Task;

/**
 * @author Bryce Schmisseur and Holland Aucoin
 * TaskManager 2.0
 * TaskDataService.java  2.0
 * February 16 2020
 *
 * Implementation of DataAccessInterface of a Task model to have CRUD functionality 
 */

@Qualifier("taskDataService")
public class TaskDataService implements DataAccessInterface<Task> {
	
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject; 

	/**
	 * @see DataAccessInterface.create();
	 */
	@Override
	public int create(Task task) 
	{
		//Initialize variable to count the number of rows affected
		int returnNum =  0;
		
		try 
		{
			//SQL Statement used to insert task 
			String sqlInset = "INSERT INTO `TASKS_TABLE` (`ID`, `DESCRIPTION`, `COMPLETED_BY`, `COMPLETED`, `LISTS_TABLE_ID`) "
					+ "VALUES (NULL, ?, ?, ?, ?);";
			
			//Query to the Database
			returnNum += jdbcTemplateObject.update(sqlInset, task.getDescription(), task.getCompletedBy(), 
					task.isCompleted(), task.getListId());
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return returnNum; 
	}

	/**
	 * @see DataAccessInterface.update();
	 */
	@Override
	public int update(Task task) 
	{
		//Initialize variable to count the number of rows affected
		int returnNum = 0;
		
		//SQL Statement used to update task 
		String sqlUpdate = "UPDATE `TASKS_TABLE` SET `DESCRIPTION` = ?, `COMPLETED_BY` = ?, `COMPLETED` = ? "
				+ "WHERE ID = ?;";
		
		try
		{
			//Query to the database
			returnNum += jdbcTemplateObject.update(sqlUpdate, task.getDescription(), task.getCompletedBy(), 
					task.isCompleted(), task.getId());
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return returnNum;
	}

	/**
	 * @see DataAccessInterface.delete();
	 */
	@Override
	public int delete(int id) 
	{
		//Initialize variable to count the number of rows affected
		int returnNum = 0;
		
		//SQL Statement that deletes task from the database
		String sqlDelete = "DELETE FROM `TASKS_TABLE` WHERE `TASKS_TABLE`.`ID` = ?";
		
		try
		{
			//Query the Database
			returnNum += jdbcTemplateObject.update(sqlDelete, id);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return returnNum;
	}

	/**
	 * @see DataAccessInterface.viewAll();
	 */
	@Override
	public List<Task> viewAll() 
	{
		//SQL Statement to Select all task from the database
		String sqlQuery = "SELECT * FROM TASKS_TABLE";
		
		//Initializes an array list to add all the task into
		List<Task> taskList = new ArrayList<Task>();
		
		try
		{
			//Query the database
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sqlQuery);
			
			//While loop to iterate through the results
			while(srs.next())
			{
				taskList.add(new Task(srs.getInt("ID"), srs.getInt("LISTS_TABLE_ID"), srs.getString("DESCRIPTION"), 
						srs.getString("COMPLETED_BY"), srs.getBoolean("COMPLETED")));
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return taskList; 
	}

	/**
	 * @see DataAccessInterface.viewById();
	 */
	@Override
	public Task viewById(int id) {
		//SQL Statement to Select all task from the database with a certian id
		String sqlQuery = "SELECT * FROM TASKS_TABLE WHERE ID = ?";
		
		//Initialize a task object to be later filled by the database
		Task currentTask = null;
		
		try
		{
			//Quering the database
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sqlQuery, id);
			
			//Loops through the results set
			while(srs.next())
			{
				currentTask = new Task(srs.getInt("ID"), srs.getInt("LISTS_TABLE_ID"), srs.getString("DESCRIPTION"), 
						srs.getString("COMPLETED_BY"), srs.getBoolean("COMPLETED"));
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return currentTask;
	}

	/**
	 * @see DataAccessInterface.viewByParentId();
	 */
	@Override
	public List<Task> viewByParentId(int listId) 
	{
		//SQL Statement to Select all task from the database based on the list they are in
		String sqlQuery = "SELECT * FROM TASKS_TABLE WHERE LISTS_TABLE_ID = ?";
		
		//Initializes an array list to add all the task into
		List<Task> taskList = new ArrayList<Task>();
		
		try
		{
			//Query the database
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sqlQuery, listId);
			
			//While loop to iterate through the resutls set
			while(srs.next())
			{
				taskList.add(new Task(srs.getInt("ID"), srs.getInt("LISTS_TABLE_ID"), srs.getString("DESCRIPTION"), 
						srs.getString("COMPLETED_BY"), srs.getBoolean("COMPLETED")));
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return taskList; 
	}

	/**
	 * @see DataAccessInterface.viewByObject();
	 */
	@Override
	public int viewByObject(Task task) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * setDataSouce takes in a DataSource from our web.xml in order to create a dataSource and JDBC Template Object used to 
	 * connect and perform CRUD action to the database
	 * @param ds - DataSource - to connect the sql command to the databases
	 */
	public void setDataSource(DataSource ds)
	{
		this.dataSource = ds;
		this.jdbcTemplateObject = new JdbcTemplate(ds);
	}
}
