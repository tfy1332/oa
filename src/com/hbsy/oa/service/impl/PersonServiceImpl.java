package com.hbsy.oa.service.impl;

import com.hbsy.oa.dao.PersonDao;
import com.hbsy.oa.domain.Person;
import com.hbsy.oa.service.PersonService;


public class PersonServiceImpl implements PersonService{
	private PersonDao personDao;

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public void savePerson(Person person) throws Exception {
		if(person==null){
			throw new RuntimeException("person为null");
		}
		this.personDao.savePerson(person);
	}

	public Person getPerson(Long id) {
		// TODO Auto-generated method stub
		return this.personDao.getPerson(id);
	}
}
