package com.hbsy.oa.dao;

import java.util.Collection;
import java.util.Set;

import com.hbsy.oa.dao.base.BaseDao;
import com.hbsy.oa.domain.Menuitem;


public interface MenuitemDao<T> extends BaseDao<T>{
	public Collection<Menuitem> getMenuitemsByPid(Long pid);
	
	public Collection<Menuitem> getAllMenuitems(Long uid);
	
	public Set<Menuitem> getMenuitemsByMIDS(Long[] mids);
	
	public Collection<Menuitem> getMenuitemsByUser();
}
