package com.hbsy.oa.dao;

import java.io.Serializable;
import java.util.Collection;

import com.hbsy.oa.domain.Department;


public interface DepartmentDao {
	public Collection<Department> getAllDepartment();
	
	public Department getDepartmentById(Serializable id);
	
	public  void saveDepartment(Department department);
	
	public void updateDepartment(Department department);
	
	public void deleteDepartment(Serializable id);
}
