package com.hbsy.oa.domain;

import java.io.Serializable;

public class Person implements Serializable{
	private Long pid;
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String name;
}
