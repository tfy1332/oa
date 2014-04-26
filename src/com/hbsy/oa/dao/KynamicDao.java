package com.hbsy.oa.dao;

import java.util.Collection;

import com.hbsy.oa.dao.base.BaseDao;
import com.hbsy.oa.domain.Kynamic;
import com.hbsy.oa.domain.Version;


public interface KynamicDao<T> extends BaseDao<T>{
	public Kynamic getKynamicByName(String name);
	
	public Collection<Version> getVersionsByKid(Long kid);
	
	public Long getCurrentVersionByKid(Long kid);
}
