package com.hbsy.oa.dao;

import com.hbsy.oa.domain.User;


public interface LoginDao{
	public User getUserByUAndP(String username,String password);
}
