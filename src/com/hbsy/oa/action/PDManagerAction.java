package com.hbsy.oa.action;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hbsy.oa.service.PDManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("pdManagerAction")
@Scope("prototype")
public class PDManagerAction extends ActionSupport{
	@Resource(name="pdManager")
	private PDManager pdManager;
	
	private File resource;
	
	private InputStream inputStream;
	
	private String deploymentId;
	
	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	private String key;
	
	public File getResource() {
		return resource;
	}

	public void setResource(File resource) {
		this.resource = resource;
	}

	public String getLasterVersions(){
		Collection<ProcessDefinition> pdList = this.pdManager.getLasterVersions();
		ActionContext.getContext().put("pdList", pdList);
		return "pdList";
	}
	
	public String deployUI(){
		return "deployUI";
	}
	
	public String deploy() throws Exception{
		this.pdManager.deploy(this.resource);
		return "action2action";
	}
	
	public String deletePD(){
		this.pdManager.deletePDByKEY(key);
		return "action2action";
	}
	
	public String showImage(){
		this.inputStream = this.pdManager.showImage(this.deploymentId);
		return SUCCESS;
	}
}
