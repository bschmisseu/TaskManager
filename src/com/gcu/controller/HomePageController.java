package com.gcu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.business.BusinessInterface;
import com.gcu.business.ListBusinessService;
import com.gcu.business.TaskBusinessService;
import com.gcu.model.Principle;
import com.gcu.model.Task;
import com.gcu.model.TaskList;
import com.gcu.model.User;

/**
 * @author Bryce Schmisseur and Holland Aucoin
 * TaskManager 2.0
 * HomePageController.java  2.0
 * February 16 2020
 *
 * HomePageController connects the view to all business services of the Task and TaskList object models
 */

@Controller
@RequestMapping("/home")
public class HomePageController {
	
	BusinessInterface<TaskList> listBusinessService; 
	
	BusinessInterface<Task> taskBusinessService;
	
	@Autowired
	Principle session; 
	
	/**
	 * Method to display the homepage to the user
	 * @return ModelAndView: an model with object and location of a jsp
	 */
	@RequestMapping(path="/tasks", method=RequestMethod.GET)
	public ModelAndView displayHomePage()
	{
		//Grabs information from the session in order to put object in to the jsp
		User currentUser = session.getCurrentUser();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("homePage");
		modelAndView.addObject("currentUser", currentUser);
		modelAndView.addObject("lists", listBusinessService.viewByParentId(currentUser.getId()));
		try
		{
			modelAndView.addObject("currentList", listBusinessService.viewByParentId(currentUser.getId()).get(0));
			session.setCurrentListId(listBusinessService.viewByParentId(currentUser.getId()).get(0).getId());
			session.setCurrentListIdByUser(0);
		}
		catch (Exception e)
		{
			modelAndView.addObject("currentList", null);
		}
		
		modelAndView.addObject("taskModel", new Task());
		return modelAndView; 
	}
	
	/**
	 * Method to display the homepage to the user after a list change has happened
	 * @return ModelAndView: an model with object and location of a jsp
	 */
	@RequestMapping(path="/changeList", method=RequestMethod.GET)
	public ModelAndView changeList(int listId)
	{
		//Grabs information from the session in order to put object in to the jsp
		User currentUser = session.getCurrentUser();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("homePage");
		modelAndView.addObject("currentUser", currentUser);
		modelAndView.addObject("lists", listBusinessService.viewByParentId(currentUser.getId()));
		modelAndView.addObject("currentList", listBusinessService.viewByParentId(currentUser.getId()).get(listId));
		modelAndView.addObject("taskModel", new Task());
		session.setCurrentListId(listBusinessService.viewByParentId(currentUser.getId()).get(listId).getId());
		session.setCurrentListIdByUser(listId);
		return modelAndView; 
	}
	
	/**
	 * Method to display the homepage to the user after a list is added
	 * @return ModelAndView: an model with object and location of a jsp
	 */
	@RequestMapping(path="/addList", method=RequestMethod.POST)
	public ModelAndView addList(@RequestParam("listName") String listName)
	{
		//Gets the current user from the session
		User currentUser = session.getCurrentUser();
		
		//Creates an primitive object of a TaskList
		TaskList newList = new TaskList(0, currentUser.getId(), null, listName);
		
		//Adds the list to the database
		listBusinessService.create(newList);
		
		//Grabs information from the session in order to put object in to the jsp
		List<TaskList> usersList = listBusinessService.viewByParentId(currentUser.getId());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("homePage");
		modelAndView.addObject("currentUser", currentUser);
		modelAndView.addObject("lists", listBusinessService.viewByParentId(currentUser.getId()));
		modelAndView.addObject("currentList", usersList.get(usersList.size() - 1));
		session.setCurrentListId(usersList.get(usersList.size() - 1).getId());
		session.setCurrentListIdByUser(usersList.size() - 1);
		modelAndView.addObject("taskModel", new Task());
		return modelAndView;
	}
	
	/**
	 * Method to display the homepage to the user after a list has been deleted
	 * @return ModelAndView: an model with object and location of a jsp
	 */
	@RequestMapping(path="/deleteList", method=RequestMethod.GET)
	public ModelAndView deleteList(int listId)
	{	
		//Gets the current user from the session
		User currentUser = session.getCurrentUser();
		
		//Gets the current list of the user
		List<TaskList> usersList = listBusinessService.viewByParentId(currentUser.getId());
		
		//Grabs the list at the certian index
		TaskList currentList = usersList.get(listId);
		
		//Removes the list from the database 
		listBusinessService.delete(currentList.getId());
		
		//Grabs information from the session in order to put object in to the jsp
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("homePage");
		modelAndView.addObject("currentUser", currentUser);
		modelAndView.addObject("lists", listBusinessService.viewByParentId(currentUser.getId()));
		try
		{
			modelAndView.addObject("currentList", listBusinessService.viewByParentId(currentUser.getId()).get(0));
			session.setCurrentListId(listBusinessService.viewByParentId(currentUser.getId()).get(0).getId());
			session.setCurrentListIdByUser(0);
		}
		catch (Exception e)
		{
			modelAndView.addObject("currentList", null);
		}
		modelAndView.addObject("taskModel", new Task());
		return modelAndView; 
	}
	
	/**
	 * Method to display the homepage to the user once a list is added
	 * @return ModelAndView: an model with object and location of a jsp
	 */
	@RequestMapping(path="/addTask", method=RequestMethod.POST)
	public ModelAndView addTask(Task task)
	{	
		try
		{
			//Sets the properties of the task
			task.setCompleted(false);
			task.setListId(session.getCurrentListId());
			
			//adds the task to the database
			taskBusinessService.create(task);
			
			//Grabs information from the session in order to put object in to the jsp
			User currentUser = session.getCurrentUser();
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("homePage");
			modelAndView.addObject("currentUser", currentUser);
			modelAndView.addObject("lists", listBusinessService.viewByParentId(currentUser.getId()));
			modelAndView.addObject("currentList", listBusinessService.viewByParentId(currentUser.getId()).get(session.getCurrentListIdByUser()));
			modelAndView.addObject("taskModel", new Task());
			return modelAndView;
		}
		
		catch(Exception e)
		{
			//Grabs information from the session in order to put object in to the jsp
			User currentUser = session.getCurrentUser();
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("homePage");
			modelAndView.addObject("currentUser", currentUser);
			modelAndView.addObject("lists", listBusinessService.viewByParentId(currentUser.getId()));
			try
			{
				modelAndView.addObject("currentList", listBusinessService.viewByParentId(currentUser.getId()).get(0));
				session.setCurrentListId(listBusinessService.viewByParentId(currentUser.getId()).get(0).getId());
				session.setCurrentListIdByUser(0);
			}
			catch (Exception ex)
			{
				modelAndView.addObject("currentList", null);
			}
			
			modelAndView.addObject("taskModel", new Task());
			return modelAndView; 
		}
		
	}
	
	/**
	 * Method to display the homepage to the user after the task has been check completed or not
	 * @return ModelAndView: an model with object and location of a jsp
	 */
	@RequestMapping(path="/toggleCompleted", method=RequestMethod.GET)
	public ModelAndView toggleCompleted(int taskNumber)
	{
		//Grabs the currentList
		TaskList currentList = listBusinessService.viewById(session.getCurrentListId());
		
		//Gets the current Task of
		Task currentTask = currentList.getTaskList().get(taskNumber);
		
		//Toggles the completed flag within the model
		currentTask.setCompleted(!currentTask.isCompleted());
		
		//updates the task within the database
		taskBusinessService.update(currentTask);
		
		//Grabs information from the session in order to put object in to the jsp
		User currentUser = session.getCurrentUser();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("homePage");
		modelAndView.addObject("currentUser", currentUser);
		modelAndView.addObject("lists", listBusinessService.viewByParentId(currentUser.getId()));
		modelAndView.addObject("currentList", listBusinessService.viewByParentId(currentUser.getId()).get(session.getCurrentListIdByUser()));
		modelAndView.addObject("taskModel", new Task());
		return modelAndView;
	}
	
	/**
	 * Method to display the homepage to the user after a task has been deleted
	 * @return ModelAndView: an model with object and location of a jsp
	 */
	@RequestMapping(path="/deleteTask", method=RequestMethod.GET)
	public ModelAndView deleteTask(int taskId)
	{
		//Gets the current list
		TaskList currentList = listBusinessService.viewById(session.getCurrentListId());
		
		//Grabs the current task
		Task currentTask = currentList.getTaskList().get(taskId);
		
		//Delete the task based on the id
		taskBusinessService.delete(currentTask.getId());
		
		//Grabs information from the session in order to put object in to the jsp
		User currentUser = session.getCurrentUser();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("homePage");
		modelAndView.addObject("currentUser", currentUser);
		modelAndView.addObject("lists", listBusinessService.viewByParentId(currentUser.getId()));
		modelAndView.addObject("currentList", listBusinessService.viewByParentId(currentUser.getId()).get(session.getCurrentListIdByUser()));
		modelAndView.addObject("taskModel", new Task());
		return modelAndView;
	}

	/**
	 * Setter method for the ListBusinessService
	 * @param listBusinessService - ListBusinessService: Business Service for the Task List Model
	 */
	@Autowired
	public void setListBusinessService(ListBusinessService listBusinessService) {
		this.listBusinessService = listBusinessService;
	}
	
	/**
	 * Setter method for the TaskBusinessService
	 * @param taskBusinessService - TaskBusinessService: Business Service for the Task Model
	 */
	@Autowired
	public void setTaskBusinessService(TaskBusinessService taskBusinessService)
	{
		this.taskBusinessService = taskBusinessService;
	}
	
	
}
