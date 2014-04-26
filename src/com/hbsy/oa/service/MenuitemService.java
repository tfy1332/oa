package com.hbsy.oa.service;

import java.util.Collection;
import java.util.Set;

import com.hbsy.oa.domain.Menuitem;


public interface MenuitemService {
	public Collection<Menuitem> getAllMenuitems(Long uid);
	
	public Collection<Menuitem> getMenuitemsByPid(Long pid);
	
	public Set<Menuitem> getMenuitemsByMIDS(Long[] mids);
	
	public Collection<Menuitem> getMenuitemsByUser();
}
