package com.hbsy.oa.test;

import org.junit.Test;

import com.hbsy.oa.dao.PersonDao;
import com.hbsy.oa.domain.Person;
import com.hbsy.oa.service.PersonService;


public class PersonTest extends BaseSpring{
	@Test
	public void testPresonService() throws Exception{
		PersonService personService = (PersonService)context.getBean("personService");
		Person person = new Person();
		person.setName("aaa");
		personService.savePerson(person);
	}
	
	@Test
	public void testPresonDao_get() throws Exception{
		PersonDao personDao = (PersonDao)context.getBean("personDao");
		Person person = new Person();
		person.setName("aaa");
		personDao.getPerson(1L);
	}
}
