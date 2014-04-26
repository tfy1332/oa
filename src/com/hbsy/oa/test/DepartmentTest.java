package com.hbsy.oa.test;

import org.junit.Test;

import com.hbsy.oa.domain.Department;
import com.hbsy.oa.service.DepartmentService;


public class DepartmentTest extends BaseSpring{
	@Test
	public void testQuery(){
		DepartmentService departmentService = (DepartmentService)context.getBean("departmentService");
		departmentService.getAllDepartment();
	}
	
	@Test
	public void testDelete(){
		DepartmentService departmentService = (DepartmentService)context.getBean("departmentService");
		departmentService.deleteDepartment(1L);
	}
	
	@Test
	public void testSave(){
		DepartmentService departmentService = (DepartmentService)context.getBean("departmentService");
		Department department = new Department();
		department.setDname("集团部");
		department.setDescription("里面有C什么O");
		departmentService.saveDepartment(department);
	}
}
