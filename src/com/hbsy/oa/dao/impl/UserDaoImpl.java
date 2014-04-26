package com.hbsy.oa.dao.impl;

import java.util.Collection;
import java.util.HashSet;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hbsy.oa.dao.UserDao;
import com.hbsy.oa.dao.base.impl.BaseDaoImpl;
import com.hbsy.oa.domain.User;


public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao<User>{
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
	@SuppressWarnings("unchecked")
	public Collection<User> getAllUser() {
		String hql = "from User u left outer join fetch u.posts p left outer join fetch u.department d";
		return new HashSet<User>(this.getHibernateTemplate().find(hql));
	}

}
