package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.Task;
import com.gcu.model.TaskList;

@Qualifier("listBusinessService")
public class ListBusinessService implements BusinessInterface<TaskList> {

	@Autowired
	@Qualifier("taskListDataService")
	private DataAccessInterface<TaskList> doa;
	
	@Autowired
	@Qualifier("taskDataService")
	private DataAccessInterface<Task> taskDOA;
	
	/**
	 * @see BusinessInterface.create()
	 */
	@Override
	public int create(TaskList taskList) {
		return doa.create(taskList);
	}

	/**
	 * @see BusinessInterface.update()
	 */
	@Override
	public int update(TaskList taskList) {
		return doa.update(taskList);
	}

	/**
	 * @see BusinessInterface.delete()
	 */
	@Override
	public int delete(int id) {
		
		TaskList currentList = doa.viewById(id);
		List<Task> tasks = new ArrayList<Task>();
		
		List<TaskList> allList = this.viewByParentId(currentList.getUserId());
		
		for(int i = 0; i < allList.size(); i++)
		{
			if(allList.get(i).getId() == id)
			{
				tasks = allList.get(i).getTaskList();
				break;
			}
		}
		
		for(int i = 0; i < tasks.size(); i++)
		{
			taskDOA.delete(tasks.get(i).getId());
		}
		
		return doa.delete(id);
	}

	/**
	 * @see BusinessInterface.viewAll()
	 */
	@Override
	public List<TaskList> viewAll() {
		List<TaskList> lists = doa.viewAll();
		
		for(int i = 0; i < lists.size(); i++)
		{	
			List<Task> tasks = taskDOA.viewByParentId(lists.get(i).getId());
			
			lists.get(i).setTaskList(tasks);
			
			lists.set(i, lists.get(i));
		}
		
		return lists;
	}

	/**
	 * @see BusinessInterface.viewById()
	 */
	@Override
	public TaskList viewById(int id) {
		TaskList currentList = doa.viewById(id);
		
		List<Task> tasks = taskDOA.viewByParentId(id);
		
		currentList.setTaskList(tasks);
		
		return currentList;
	}

	/**
	 * @see BusinessInterface.viewByParent()
	 */
	@Override
	public List<TaskList> viewByParentId(int parentId) {
		List<TaskList> lists = doa.viewByParentId(parentId);
		
		for(int i = 0; i < lists.size(); i++)
		{	
			List<Task> tasks = taskDOA.viewByParentId(lists.get(i).getId());
			
			lists.get(i).setTaskList(tasks);
			
			lists.set(i, lists.get(i));
		}
		
		return lists;
	}

	/**
	 * @see BusinessInterface.viewByObject()
	 */
	@Override
	public int viewByObject(TaskList taskList) {
		return doa.viewByObject(taskList);
	}
}
