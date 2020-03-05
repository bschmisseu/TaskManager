package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.TaskList;

public class ListBusinessService implements BusinessInterface<TaskList> {

	@Autowired
	private DataAccessInterface<TaskList> doa;
	
	/**
	 * @see BusinessService
	 */
	@Override
	public int create(TaskList taskList) {
		return doa.create(taskList);
	}

	/**
	 * @see BusinessService
	 */
	@Override
	public int update(TaskList taskList) {
		return doa.update(taskList);
	}

	/**
	 * @see BusinessService
	 */
	@Override
	public int delete(int id) {
		return doa.delete(id);
	}

	
	@Override
	public List<TaskList> viewAll() {
		return doa.viewAll();
	}

	/**
	 * @see BusinessService
	 */
	@Override
	public TaskList viewById(int id) {
		return doa.viewById(id);
	}

	/**
	 * @see BusinessService
	 */
	@Override
	public List<TaskList> viewByParentId(int parentId) {
		return doa.viewByParentId(parentId);
	}

	/**
	 * @see BusinessService
	 */
	@Override
	public int viewByObject(TaskList taskList) {
		return doa.viewByObject(taskList);
	}


}
