package com.hbsy.oa.dao;

import com.hbsy.oa.domain.Person;


public interface PersonDao {
	public void savePerson(Person person) throws Exception;
	
	public Person getPerson(Long id);
}
