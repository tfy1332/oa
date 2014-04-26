package com.hbsy.oa.test;

import java.util.Collection;

import org.junit.Test;

import com.hbsy.oa.dao.UserDao;
import com.hbsy.oa.domain.User;


public class UserTest extends BaseSpring{
	@Test
	public void testQuery(){
		UserDao userDao = (UserDao)context.getBean("userDao");
		Collection<User> userList = userDao.getAllUser();
		System.out.println(userList.size());
	}
}
