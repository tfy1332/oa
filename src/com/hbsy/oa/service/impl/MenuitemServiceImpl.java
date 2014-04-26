package com.hbsy.oa.service.impl;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hbsy.oa.dao.MenuitemDao;
import com.hbsy.oa.domain.Menuitem;
import com.hbsy.oa.service.MenuitemService;


@Service("menuitemService")
public class MenuitemServiceImpl implements MenuitemService{
	@Resource(name="menuitemDao")
	private MenuitemDao menuitemDao;

	

	public MenuitemDao getMenuitemDao() {
		return menuitemDao;
	}

	public void setMenuitemDao(MenuitemDao menuitemDao) {
		this.menuitemDao = menuitemDao;
	}

	public Collection<Menuitem> getAllMenuitems(Long uid) {
		return this.menuitemDao.getAllMenuitems(uid);
	}

	public Collection<Menuitem> getMenuitemsByPid(Long pid) {
		return this.menuitemDao.getMenuitemsByPid(pid);
	}

	public Set<Menuitem> getMenuitemsByMIDS(Long[] mids) {
		return this.menuitemDao.getMenuitemsByMIDS(mids);
	}

	public Collection<Menuitem> getMenuitemsByUser() {
		return this.menuitemDao.getMenuitemsByUser();
	}
}
