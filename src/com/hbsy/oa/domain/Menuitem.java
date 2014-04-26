package com.hbsy.oa.domain;

import java.io.Serializable;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

public class Menuitem implements Serializable{
	private Long mid;//涓婚敭锛屾爲涓婂敮涓�殑鍚嶇О鑺傜偣
	private String name;//瑕佹樉绀虹殑鏍戠殑鑺傜偣鐨勫悕绉�
	private String url;//鐐瑰嚮璇ヨ妭鐐硅杩炴帴鍒扮殑椤甸潰
	private Long pid;//鐖惰妭鐐�
	
	private Boolean checked;
	
	private String target;
	
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}





	private Set<User> users;
	
	@JSON(serialize=false)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}





	private String icon;
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	
	
	
	
	private Boolean isParent;//鏄惁涓虹埗鑺傜偣


}
