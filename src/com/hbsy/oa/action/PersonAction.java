package com.hbsy.oa.action;

import org.apache.struts2.ServletActionContext;

import com.hbsy.oa.domain.Person;
import com.hbsy.oa.service.PersonService;
import com.opensymphony.xwork2.ActionSupport;

public class PersonAction extends ActionSupport{
	private PersonService personService;
	public PersonService getPersonService() {
		return personService;
	}
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	public String savePerson() throws Exception{
		Person person = new Person();
		person.setName("阿斯顿");
		this.personService.savePerson(person);
		return SUCCESS;
	}
	
	public String getPerson() throws Exception{
		Person person = this.personService.getPerson(1L);
		ServletActionContext.getRequest().setAttribute("person", person);
		return SUCCESS;
	}
}
