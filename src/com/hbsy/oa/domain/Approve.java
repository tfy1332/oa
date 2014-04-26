package com.hbsy.oa.domain;

import java.io.Serializable;
import java.util.Date;

public class Approve implements Serializable{
	private Long aid;
	private String approvename;//瀹℃壒浜�
	private String approvecomment;//瀹℃壒鎰忚
	private String isapprove;
	private Date approvetime;
	
	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	public String getApprovename() {
		return approvename;
	}

	public void setApprovename(String approvename) {
		this.approvename = approvename;
	}

	public String getApprovecomment() {
		return approvecomment;
	}

	public void setApprovecomment(String approvecomment) {
		this.approvecomment = approvecomment;
	}

	public String getIsapprove() {
		return isapprove;
	}

	public void setIsapprove(String isapprove) {
		this.isapprove = isapprove;
	}

	public Date getApprovetime() {
		return approvetime;
	}

	public void setApprovetime(Date approvetime) {
		this.approvetime = approvetime;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	private Form form;
}
