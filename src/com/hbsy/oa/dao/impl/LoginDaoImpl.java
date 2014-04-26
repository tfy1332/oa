package com.hbsy.oa.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.hbsy.oa.dao.LoginDao;
import com.hbsy.oa.domain.User;

/**
 * <bean id="loginDao" 
 *    class="..LoginDaoImpl">
 * @author Think
 *
 */
@Repository("loginDao")
public class LoginDaoImpl implements LoginDao{
	@Resource(name="hibernateTemplate")
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
	public User getUserByUAndP(String username, String password) {
		// TODO Auto-generated method stub
		List<User> userList = this.hibernateTemplate.find("from User where username=? and password=?",new Object[]{username,password});
		if(userList.size()==0){
			return null;
		}else{
			return userList.get(0);
		}
	}
}
