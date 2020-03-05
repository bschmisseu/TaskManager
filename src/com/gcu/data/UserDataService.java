package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.exception.DatabaseException;
import com.gcu.model.User;

/**
 * @author Bryce Schmisseur and Holland Aucoin
 * TaskManager 2.0
 * UserDataService.java  2.0
 * February 16 2020
 *
 * Implementation of DataAccessInterface of a User model to have CRUD functionality 
 */

public class UserDataService implements DataAccessInterface<User> {

	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject; 
	
	/**
	 * @see DataAccessInterface.create();
	 */
	@Override
	public int create(User user) {
		int returnNum = 0;
		
		//Checks if the user is valid
		String sqlValidUser = "SELECT * FROM USER_CREDENTIAL WHERE USERNAME=?;";
		
		try
		{
			SqlRowSet srsFind = jdbcTemplateObject.queryForRowSet(sqlValidUser, user.getUsercredentials().getUsername());
			
			if (srsFind.next() == false)
			{
				//Creates SQL statements to be filled in later
				String sqlInsertCreds = "INSERT INTO USER_CREDENTIAL(USERNAME, PASSWORD, USERS_ID) VALUES(?, ?, ?)";
				String sqlInsertUser = "INSERT INTO USERS(ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER) "
						+ "VALUES (NULL, ?, ?, ?, ?)";
				
				try
				{
					//Inputs inforamtion into the database for both credentials and user information 
					int rows = jdbcTemplateObject.update(sqlInsertUser, user.getFirstName(), user.getLastName(), 
							user.getEmail(),user.getPhoneNumber());
					
					String sqlQuery = "SELECT LAST_INSERT_ID() AS LAST_ID FROM USERS";
					
					SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sqlQuery);
					srs.next();
					
					int userId = Integer.parseInt(srs.getString("LAST_ID"));
					
					rows += jdbcTemplateObject.update(sqlInsertCreds, user.getUsercredentials().getUsername(), 
							user.getUsercredentials().getPassword(), userId);
					
					returnNum = rows;
				}
				
				catch(Exception e)
				{
					e.printStackTrace();
					throw new DatabaseException(e);
				}
			}
			
			else
			{
				returnNum = -1;
			}
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
	public int update(User user) {
		int returnNum = 0;
		
		//Creates SQL statements to be filled in later
		String sqlUpdateCred = "UPDATE USER_CREDENTIAL SET USERNAME=?, PASSWORD=? WHERE ID=?";
		String sqlUpdateUser = "Update USERS SET FIRST_NAME=?, LAST_NAME=?, EMAIL=?, PHONE_NUMBER=? WHERE ID=?";
		
		try
		{	
			returnNum += jdbcTemplateObject.update(sqlUpdateCred, user.getUsercredentials().getUsername(), 
					user.getUsercredentials().getPassword(), user.getId());
			
			returnNum += jdbcTemplateObject.update(sqlUpdateUser, user.getFirstName(), user.getLastName(), user.getEmail(), 
					user.getPhoneNumber(), user.getId());	
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
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see DataAccessInterface.viewAll();
	 */
	@Override
	public List<User> viewAll() {
		//Creates a SQL statement to be filled in later
		String sql = "SELECT * FROM USERS INNER JOIN USER_CREDENTIAL ON USERS.ID = USER_CREDENTIAL.USERS_ID;";
		
		//Creates an ArrayList of users that will be filled with all the users from the database
		List<User> userList = new ArrayList<User>();
		
		try
		{
			//Access the database and Queries for all users and is given a results set with information of all users
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			while(srs.next())
			{
				userList.add(new User(srs.getInt("ID"), srs.getString("FIRST_NAME"), srs.getString("LAST_NAME"), 
						srs.getString("EMAIL"), srs.getString("PHONE_NUMBER"), srs.getString("USERNAME"), srs.getString("PASSWORD")));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return userList;
	}

	/**
	 * @see DataAccessInterface.viewById();
	 */
	@Override
	public User viewById(int id) {
		//Creates a SQL statement to be filled in later
		String sql = "SELECT * FROM USERS INNER JOIN USER_CREDENTIAL ON USERS.ID = USER_CREDENTIAL.USERS_ID AND USERS.ID = ?;";
		
		//Creates an ArrayList of users that will be filled with all the users from the database
		User currentUser = null;
		
		try
		{
			//Access the database and Queries for all users and is given a results set with information of all users
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql, id);
			while(srs.next())
			{
				currentUser = new User(srs.getInt("ID"), srs.getString("FIRST_NAME"), srs.getString("LAST_NAME"), 
						srs.getString("EMAIL"), srs.getString("PHONE_NUMBER"), srs.getString("USERNAME"), srs.getString("PASSWORD"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return currentUser;
	}

	/**
	 * @see DataAccessInterface.viewByParentId();
	 */
	@Override
	public List<User> viewByParentId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @see DataAccessInterface.viewByObject();
	 */
	@Override
	public int viewByObject(User user) {
		//Creates a SQL statement to be filled in later
		String sql = "SELECT * FROM USERS INNER JOIN USER_CREDENTIAL ON USERS.ID = USER_CREDENTIAL.USERS_ID AND "
				+ "USER_CREDENTIAL.USERNAME = ? AND USER_CREDENTIAL.PASSWORD = ?;";
		
		//Creates an ArrayList of users that will be filled with all the users from the database
		int currentId = 0;
		
		try
		{
			//Access the database and Queries for all users and is given a results set with information of all users
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql, user.getUsercredentials().getUsername(), 
					user.getUsercredentials().getPassword());
			while(srs.next())
			{
				currentId = srs.getInt("ID");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return currentId;
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
