package com.hbsy.oa.service;

import java.util.Collection;

import com.hbsy.oa.domain.Kynamic;
import com.hbsy.oa.domain.Version;


public interface KynamicService {
	public void saveKynamic(Kynamic kynamic);
	
	public Collection<Kynamic> getKynamics();
	
	public Kynamic getKynamicByName(String name);
	
	public void updateKynamic(Kynamic kynamic);
	
	public Kynamic getKynamicById(Long kid);
	
	public void deleteKynamicByID(Long kid);
	
	public Collection<Version> getVersionsByKid(Long kid);
	
	public void saveVersionByKid(Version version);
}
