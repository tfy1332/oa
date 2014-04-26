package com.hbsy.oa.action;

import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hbsy.oa.action.base.BaseAction;
import com.hbsy.oa.domain.Kynamic;
import com.hbsy.oa.domain.Version;
import com.hbsy.oa.service.KynamicService;


@Controller("kynamicAction")
@Scope("prototype")
public class KynamicAction extends BaseAction<Kynamic>{
	@Resource(name="kynamicService")
	private KynamicService kynamicService;
	
	private Collection<Kynamic> kynamicList;
	
	private Collection<Version> versionList;
	
	public Collection<Version> getVersionList() {
		return versionList;
	}

	private String message;
	
	private Long id;
	
	/**
	 * 鍏充簬鐗堟湰鐨則itle鍜宑ontent
	 */
	private String title;
	private String content;
	
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

	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public Collection<Kynamic> getKynamicList() {
		return kynamicList;
	}

	public String showKynamics(){
		this.kynamicList = this.kynamicService.getKynamics();
		return SUCCESS;
	}
	
	public String showkynamicByName(){
		Kynamic kynamic = this.kynamicService.getKynamicByName(this.getModel().getName());
		if(kynamic==null){
			this.message = "1";//鍙敤
		}else{
			this.message = "0";// 涓嶅彲鐢�
		}
		return SUCCESS;
	}
	
	public String saveKynamic(){
		Kynamic kynamic = new Kynamic();
		BeanUtils.copyProperties(this.getModel(), kynamic);
		this.kynamicService.saveKynamic(kynamic);
		this.id = kynamic.getKid();
		return SUCCESS;
	}
	
	public String updateKynamic(){
		Kynamic kynamic = this.kynamicService.getKynamicById(this.getModel().getKid());
		kynamic.setKid(this.getModel().getKid());
		kynamic.setName(this.getModel().getName());
		this.kynamicService.updateKynamic(kynamic);
		return SUCCESS;
	}
	
	public String delete(){
		this.kynamicService.deleteKynamicByID(this.getModel().getKid());
		return SUCCESS;
	}
	
	public String showVersionsByKid(){
		this.versionList = this.kynamicService.getVersionsByKid(this.getModel().getKid());
		return SUCCESS;
	}
	
	public String saveVersionByKid(){
		Version version = new Version();
		version.setContent(this.content);
		version.setTitle(this.title);
		version.setState(true);//state涓簍rue琛ㄧず涓婇攣浜嗭紝鎰忔�鏄湁涓�釜浜烘鍦ㄨ闂紝鍏朵粬鐨勪汉涓嶈兘璁块棶
		version.setUpdatetime(new Date());
		Kynamic kynamic = this.kynamicService.getKynamicById(this.getModel().getKid());
		//寤虹珛version涓� kynamic涔嬮棿鐨勫叧绯�
		version.setKynamic(kynamic);
		this.kynamicService.saveVersionByKid(version);
		return SUCCESS;
	}
}
