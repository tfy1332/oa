package com.hbsy.oa.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Chain2Action extends ActionSupport{
	public String bb(){
		ActionContext.getContext().put("bbb", "bbbbb");
		return SUCCESS;
	}
}
