package com.hbsy.oa.service;

import com.hbsy.oa.domain.Person;


public interface PersonService {
	public void savePerson(Person person) throws Exception;
	
	public Person getPerson(Long id);
}
