package com.hbsy.oa.service.impl;

import java.io.Serializable;
import java.util.Collection;

import com.hbsy.oa.dao.DepartmentDao;
import com.hbsy.oa.domain.Department;
import com.hbsy.oa.service.DepartmentService;


public class DepartmentServiceImpl implements DepartmentService{
	private DepartmentDao departmentDao;

	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public void deleteDepartment(Serializable id) {
		this.departmentDao.deleteDepartment(id);
	}

	public Collection<Department> getAllDepartment() {
		return this.departmentDao.getAllDepartment();
	}

	public Department getDepartmentById(Serializable id) {
		return this.departmentDao.getDepartmentById(id);
	}

	public void saveDepartment(Department department) {
		this.departmentDao.saveDepartment(department);
	}

	public void updateDepartment(Department department) {
		this.departmentDao.updateDepartment(department);
	}
	
}
