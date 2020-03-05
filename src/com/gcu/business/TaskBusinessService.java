package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.Task;

public class TaskBusinessService implements BusinessInterface<Task>{
	
	@Autowired
	private DataAccessInterface<Task> doa;
	
	/**
	 * @see BusinessService
	 */
	@Override
	public int create(Task task) {
		return doa.create(task);
	}

	/**
	 * @see BusinessService
	 */
	@Override
	public int update(Task task) {
		return doa.update(task);
	}

	/**
	 * @see BusinessService
	 */
	@Override
	public int delete(int id) {
		return doa.delete(id);
	}

	/**
	 * @see BusinessService
	 */
	@Override
	public List<Task> viewAll() {
		return doa.viewAll();
	}

	/**
	 * @see BusinessService
	 */
	@Override
	public Task viewById(int id) {
		return doa.viewById(id);
	}

	/**
	 * @see BusinessService
	 */
	@Override
	public List<Task> viewByParentId(int parentId) {
		return doa.viewByParentId(parentId);
	}

	/**
	 * @see BusinessService
	 */
	@Override
	public int viewByObject(Task task) {
		return doa.viewByObject(task);
	}

}
