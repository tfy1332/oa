package com.hbsy.oa.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hbsy.oa.dao.DepartmentDao;
import com.hbsy.oa.domain.Department;
import com.hbsy.oa.domain.User;


public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao{

	public void deleteDepartment(Serializable id) {
		Department department = this.getDepartmentById(id);
		Set<User> users = department.getUsers();
		for(User user:users){
			user.setDepartment(null);
		}
		this.getHibernateTemplate().delete(department);
	}

	@SuppressWarnings("unchecked")
	public Collection<Department> getAllDepartment() {
		return this.getHibernateTemplate().find("from Department");
	}

	public Department getDepartmentById(Serializable id) {
		return (Department)this.getHibernateTemplate().get(Department.class, id);
	}

	public void saveDepartment(Department department) {
		this.getHibernateTemplate().save(department);
	}

	public void updateDepartment(Department department) {
		this.getHibernateTemplate().update(department);
	}

}
