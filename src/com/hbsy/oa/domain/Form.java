package com.hbsy.oa.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 濡傛灉灏嗘潵form浣滀负娴佺▼鍙橀噺瀛樻斁鍒癹bpm鐨勫紩鎿庝腑锛岄偅涔坔ibernate entity鐨勭被鍨嬩负锛歭ong,string
 * @author Think
 *
 */
public class Form implements Serializable{
	private Long fid;
	private String title;
	private String applicator;
	
	private Date applicatetime;
	
	private String state;//瀹℃壒涓�宸查�杩囥�鏈�杩�
	
	private String url;
	
	private FormTemplate formTemplate;
	
	private Set<Approve> approves;

	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getApplicator() {
		return applicator;
	}

	public void setApplicator(String applicator) {
		this.applicator = applicator;
	}

	public Date getApplicatetime() {
		return applicatetime;
	}

	public void setApplicatetime(Date applicatetime) {
		this.applicatetime = applicatetime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public FormTemplate getFormTemplate() {
		return formTemplate;
	}

	public void setFormTemplate(FormTemplate formTemplate) {
		this.formTemplate = formTemplate;
	}

	public Set<Approve> getApproves() {
		return approves;
	}

	public void setApproves(Set<Approve> approves) {
		this.approves = approves;
	}
}
