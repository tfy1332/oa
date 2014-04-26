package com.hbsy.oa.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import com.hbsy.oa.dao.MenuitemDao;
import com.hbsy.oa.domain.Menuitem;
import com.hbsy.oa.service.PrivilegeService;


public class PrivilegeServiceImpl implements PrivilegeService{
	@Resource(name="menuitemDao")
	private MenuitemDao menuitemDao;

	public MenuitemDao getMenuitemDao() {
		return menuitemDao;
	}

	public void setMenuitemDao(MenuitemDao menuitemDao) {
		this.menuitemDao = menuitemDao;
	}

	public Collection<Menuitem> getAllPrivilege(Long uid) {
		return this.menuitemDao.getAllMenuitems(uid);
	}
}
