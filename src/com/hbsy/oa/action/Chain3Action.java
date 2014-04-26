package com.hbsy.oa.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Chain3Action extends ActionSupport{
	public String cc(){
		ActionContext.getContext().put("ccc", "ccccc");
		return SUCCESS;
	}
}
