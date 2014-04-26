package com.hbsy.oa.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hbsy.oa.dao.PersonDao;
import com.hbsy.oa.domain.Person;


public class PersonDaoImpl extends HibernateDaoSupport implements PersonDao{

	public void savePerson(Person person) throws Exception {
		this.getHibernateTemplate().save(person);
	}

	public Person getPerson(Long id) {
		Person person = (Person)this.getHibernateTemplate().load(Person.class, id);
		return person;
	}

}
