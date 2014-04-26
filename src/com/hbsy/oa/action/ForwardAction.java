package com.hbsy.oa.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ForwardAction extends ActionSupport{
	private String forward;

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}
	
	/**
	 * 璺宠浆鍒扮浉搴旂殑椤甸潰
	 * @return
	 */
	public String forward(){
		ActionContext.getContext().put("forward", this.forward);
		return "forward";
	}
	
	public String kynamic(){
		return "kynamic";
	}
}
