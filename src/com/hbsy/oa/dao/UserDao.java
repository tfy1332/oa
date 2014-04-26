package com.hbsy.oa.dao;

import java.util.Collection;

import com.hbsy.oa.dao.base.BaseDao;
import com.hbsy.oa.domain.User;


public interface UserDao<T> extends BaseDao<T>{
	public Collection<User> getAllUser();
}
