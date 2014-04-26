package com.hbsy.oa.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

public class Version implements Serializable{
	private Long vid;
	private Long versionid;//鐗堟湰鍙�
	private Date updatetime;
	private String title;
	private String content;
	
	private Boolean state;
	
	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	private Kynamic kynamic;

	public Long getVid() {
		return vid;
	}

	public void setVid(Long vid) {
		this.vid = vid;
	}

	public Long getVersionid() {
		return versionid;
	}

	public void setVersionid(Long versionid) {
		this.versionid = versionid;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@JSON(serialize=false)
	public Kynamic getKynamic() {
		return kynamic;
	}

	public void setKynamic(Kynamic kynamic) {
		this.kynamic = kynamic;
	}
}
