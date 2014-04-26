package com.hbsy.oa.action;

import com.opensymphony.xwork2.ActionChainResult;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Chain1Action extends ActionSupport{
	public String aa(){
		ActionContext.getContext().put("aaa", "aaaaa");
		return SUCCESS;
	}
}
