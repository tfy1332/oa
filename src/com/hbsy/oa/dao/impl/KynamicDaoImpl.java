package com.hbsy.oa.dao.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.hbsy.oa.dao.KynamicDao;
import com.hbsy.oa.dao.base.impl.BaseDaoImpl;
import com.hbsy.oa.domain.Kynamic;
import com.hbsy.oa.domain.Version;


@Repository("kynamicDao")
public class KynamicDaoImpl extends BaseDaoImpl<Kynamic> implements KynamicDao<Kynamic>{
	private HibernateTemplate hibernateTemplate;
	private SessionFactory sessionFactory;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Kynamic getKynamicByName(String name) {
		List<Kynamic> kynamicList = this.getHibernateTemplate().find("from Kynamic where name=?",name);
		if(kynamicList.size()==0){
			return null;
		}else{
			return kynamicList.get(0);
		}
	}

	public Collection<Version> getVersionsByKid(Long kid) {
		return this.hibernateTemplate.find("from Version v where v.kynamic.kid=?",kid);
	}

	public Long getCurrentVersionByKid(Long kid) {
		List<Long> versionList = this.hibernateTemplate.find("select max(v.versionid) from Version v where v.kynamic.kid=?",kid);
		if(versionList.get(0)==null){
			return 1L;
		}else{
			return versionList.get(0)+1L;
		}
	}

}
