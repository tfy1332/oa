package com.hbsy.oa.domain;

import java.io.Serializable;
import java.util.Set;

public class Post implements Serializable{
	private Long pid;
	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	private String pname;
	private String description;
	
	private Set<User> users;
}
