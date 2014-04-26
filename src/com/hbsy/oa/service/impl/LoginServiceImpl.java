package com.hbsy.oa.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hbsy.oa.dao.LoginDao;
import com.hbsy.oa.domain.User;
import com.hbsy.oa.service.LoginService;


@Service("loginService")
public class LoginServiceImpl implements LoginService{
	@Resource(name="loginDao")
	private LoginDao loginDao;
	
	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	public User login(String username, String password) {
		return this.loginDao.getUserByUAndP(username, password);
	}
	
}
