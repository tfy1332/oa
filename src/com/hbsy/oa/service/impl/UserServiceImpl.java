package com.hbsy.oa.service.impl;

import java.io.Serializable;
import java.util.Collection;

import com.hbsy.oa.dao.UserDao;
import com.hbsy.oa.domain.User;
import com.hbsy.oa.service.UserService;


public class UserServiceImpl implements UserService{
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public Collection<User> getAllUser() {
		return this.userDao.getAllUser();
	}

	public void saveUser(User user) {
		this.userDao.saveEntry(user);
	}

	public void deleteUser(Serializable id) {
		this.userDao.deleteEntry(id);
	}

	public User getUserByID(Serializable id) {
		return (User)this.userDao.getEntryByID(id);
	}

	public void updateUser(User user) {
		this.userDao.updateEntry(user);
	}
}
