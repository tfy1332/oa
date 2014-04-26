package com.hbsy.oa.service.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hbsy.oa.dao.base.BaseDao;
import com.hbsy.oa.service.base.BaseService;


public class BaseServiceImpl<T>  implements BaseService<T> {
	private BaseDao baseDao;
	
	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@SuppressWarnings("unchecked")
	public Collection<T> getAllEntry(){
		return baseDao.getAllEntry();
	}

	public void updateEntry(T t){
	 baseDao.updateEntry(t);
	}

	public void saveEntry(T t){
		 baseDao.saveEntry(t);
	}

	public void deleteEntry(Serializable id){
		  baseDao.deleteEntry(id);
	}

	public T getEntryByID(Serializable id){
		return (T)baseDao.getEntryByID(id);
	}
}
