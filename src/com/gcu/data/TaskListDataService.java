package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.business.TaskBusinessService;
import com.gcu.exception.DatabaseException;
import com.gcu.model.Task;
import com.gcu.model.TaskList;

/**
 * @author Bryce Schmisseur and Holland Aucoin
 * TaskManager 2.0
 * TaskListDataService.java  2.0
 * February 16 2020
 *
 * Implementation of DataAccessInterface of a TaskList model to have CRUD functionality 
 */

public class TaskListDataService implements DataAccessInterface<TaskList>
{
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject; 
	
	@Autowired
	TaskBusinessService taskService;

	/**
	 * @see DataAccessInterface.create();
	 */
	@Override
	public int create(TaskList taskList) 
	{
		//Initialize variable to count the number of rows affected
		int returnNum =  0;
		
		try 
		{
			//SQL Statement used to insert task list
			String sqlInset = "INSERT INTO `LISTS_TABLE` (`ID`, `NAME`, `USERS_TABLE_ID`) VALUES (NULL, ?, ?);";
			
			//Query to the Database
			returnNum += jdbcTemplateObject.update(sqlInset, taskList.getName(), taskList.getUserId());
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
	public int update(TaskList taskList) 
	{
		//Initialize variable to count the number of rows affected
		int returnNum = 0;
		
		//SQL Statement used to update task list
		String sqlUpdate = "UPDATE `LISTS_TABLE` SET `NAME` = ? WHERE `LISTS_TABLE`.`ID` = 1;";
		
		try
		{
			//Query to the database
			returnNum += jdbcTemplateObject.update(sqlUpdate, taskList.getName());
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
		
		//Gets a list of all task that are apart of the task list
		TaskList currentList = this.viewById(id);
		
		//Loop to iterate through all the tasks to delete them from the database
		for(int i = 0; i < currentList.getTaskList().size(); i ++)
		{
			taskService.delete(currentList.getTaskList().get(i).getId());
		}
		
		//SQL Statement that deletes task list from the database
		String sqlDelete = "DELETE FROM `LISTS_TABLE` WHERE `LISTS_TABLE`.`ID` = ?";
		
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
	public List<TaskList> viewAll() 
	{
		//SQL Statement to Select all task list from the database
		String sqlQuery = "SELECT * FROM LISTS_TABLE";
		
		//Initializes an array list to add all the task into
		List<TaskList> taskLists = new ArrayList<TaskList>();
		
		try
		{
			//Query the database
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sqlQuery);
			
			//While loop to iterate through the results
			while(srs.next())
			{
				int listId = srs.getInt("ID");
				
				TaskBusinessService taskService = new TaskBusinessService(); 
				
				List<Task> taskList = taskService.viewByParentId(listId);
				
				taskLists.add(new TaskList(srs.getInt("ID"), srs.getInt("USERS_TABLE_ID"), taskList, srs.getString("NAME")));
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return taskLists; 
	}

	/**
	 * @see DataAccessInterface.viewById();
	 */
	@Override
	public TaskList viewById(int id) {
		//SQL Statement to Select all task list from the database with a certian id
		String sqlQuery = "SELECT * FROM LISTS_TABLE WHERE ID = ?";
		
		//Initialize a task list pbject to be later filled by the database
		TaskList currentTaskList = null;
		
		try
		{
			//Quering the database
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sqlQuery, id);
			
			//Loops through the results set
			while(srs.next())
			{
				int listId = srs.getInt("ID");
				
				List<Task> taskList = taskService.viewByParentId(listId);
				
				currentTaskList = new TaskList(srs.getInt("ID"), srs.getInt("USERS_TABLE_ID"), taskList, srs.getString("NAME"));
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return currentTaskList;
	}

	/**
	 * @see DataAccessInterface.viewByParentId();
	 */
	@Override
	public List<TaskList> viewByParentId(int userId) 
	{
		String[] colors = {"#a98aff",  "#8aecff", "#a1ff8a", "#ebff8a", "#ffd48a", "#ff8a8a"};
		int colorNum = 0;
		
		//SQL Statement to Select all task list from the database based on the list they are in
		String sqlQuery = "SELECT * FROM LISTS_TABLE WHERE USERS_TABLE_ID = ?";
		
		//Initializes an array task list to add all the task into
		List<TaskList> taskLists = new ArrayList<TaskList>();
		
		try
		{
			//Query the database
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sqlQuery, userId);
			
			//While loop to iterate through the resutls set
			while(srs.next())
			{
				int listId = srs.getInt("ID");
				
				List<Task> taskList = taskService.viewByParentId(listId);
				
				TaskList currentList = new TaskList(srs.getInt("ID"), srs.getInt("USERS_TABLE_ID"), taskList, srs.getString("NAME"));
				
				currentList.setColor(colors[colorNum]);
				
				taskLists.add(currentList);
				
				if(colorNum >= 5)
				{
					colorNum = 0; 
				}
				
				else
				{
					colorNum++;
				}
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return taskLists; 
	}

	/**
	 * @see DataAccessInterface.viewByObject();
	 */
	@Override
	public int viewByObject(TaskList taskList) {
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
