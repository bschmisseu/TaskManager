package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.Task;

@Qualifier("taskBusinessService")
public class TaskBusinessService implements BusinessInterface<Task>{
	
	@Autowired
	@Qualifier("taskDataService")
	private DataAccessInterface<Task> doa;
	
	/**
	 * @see BusinessInterface.create()
	 */
	@Override
	public int create(Task task) {
		return doa.create(task);
	}

	/**
	 * @see BusinessInterface.update()
	 */
	@Override
	public int update(Task task) {
		return doa.update(task);
	}

	/**
	 * @see BusinessInterface.delete()
	 */
	@Override
	public int delete(int id) {
		return doa.delete(id);
	}

	/**
	 * @see BusinessInterface.viewAll()
	 */
	@Override
	public List<Task> viewAll() {
		return doa.viewAll();
	}

	/**
	 * @see BusinessInterface.viewById()
	 */
	@Override
	public Task viewById(int id) {
		return doa.viewById(id);
	}

	/**
	 * @see BusinessInterface.viewByParent()
	 */
	@Override
	public List<Task> viewByParentId(int parentId) {
		return doa.viewByParentId(parentId);
	}

	/**
	 * @see BusinessInterface.viewByObject()
	 */
	@Override
	public int viewByObject(Task task) {
		return doa.viewByObject(task);
	}

}
