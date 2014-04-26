package com.hbsy.oa.service;

import java.util.Collection;

import com.hbsy.oa.domain.Menuitem;


public interface PrivilegeService {
	public Collection<Menuitem> getAllPrivilege(Long uid);
}
