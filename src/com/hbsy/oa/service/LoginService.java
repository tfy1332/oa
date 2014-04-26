package com.hbsy.oa.service;

import com.hbsy.oa.domain.User;


public interface LoginService {
	public User login(String username,String password);
}
