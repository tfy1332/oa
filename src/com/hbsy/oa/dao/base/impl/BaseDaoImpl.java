package com.hbsy.oa.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hbsy.oa.dao.base.BaseDao;


public class BaseDaoImpl<T>  implements BaseDao<T> {
	@Resource(name="hibernateTemplate")
	public HibernateTemplate hibernateTemplate;
	
	public Class getClasst() {
		return classt;
	}

	public void setClasst(Class classt) {
		this.classt = classt;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	private Class classt;
	
	public BaseDaoImpl(){
		//this浠ｈ〃瀛愮被鐨勫璞�
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		this.classt = (Class)type.getActualTypeArguments()[0];
		System.out.println(type.getRawType().toString());
	}
	
	@SuppressWarnings("unchecked")
	public Collection<T> getAllEntry(){
		return this.getHibernateTemplate().find("from "+this.classt.getName());
	}

	public void updateEntry(T t){
		this.getHibernateTemplate().update(t);
	}

	public void saveEntry(T t){
		this.getHibernateTemplate().save(t);
	}

	public void deleteEntry(Serializable id){
		T t = this.getEntryByID(id);
		this.getHibernateTemplate().delete(t);
	}

	public T getEntryByID(Serializable id){
		return (T)this.getHibernateTemplate().get(this.classt, id);
	}
}
