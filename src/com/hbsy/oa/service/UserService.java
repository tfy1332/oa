package com.hbsy.oa.service;

import java.io.Serializable;
import java.util.Collection;

import com.hbsy.oa.domain.User;


public interface UserService {
	public Collection<User> getAllUser();
	
	public void saveUser(User user);
	
	public void deleteUser(Serializable id);
	
	public User getUserByID(Serializable id);
	
	public void updateUser(User user);
}
