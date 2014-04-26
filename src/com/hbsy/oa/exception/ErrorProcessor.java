package com.hbsy.oa.exception;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

@SuppressWarnings("serial")
public class ErrorProcessor extends ActionSupport {
	private String ss = "";
	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}

	private Exception exception;
	
	@JSON(serialize=false)
	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	@Override
	public String execute(){
		try{
			this.ss = this.exception.getMessage();
			ServletActionContext.getResponse().setStatus(500);
		}catch(Exception e){
			
		}
		return SUCCESS;
	}

}
