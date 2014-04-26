package com.hbsy.oa.test;

import org.junit.Test;

import com.hbsy.oa.dao.LoginDao;
import com.hbsy.oa.service.LoginService;


public class LoginTest extends BaseSpring{
	@Test
	public void testLogin(){
		LoginDao loginDao = (LoginDao)context.getBean("loginDao");
		loginDao.getUserByUAndP("aa", "afds");
	}
	
	@Test
	public void testLoginService(){
		LoginService loginService = (LoginService)context.getBean("loginService");
		System.out.println(loginService);
	}
}	
