package com.hbsy.oa.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hbsy.oa.action.base.BaseAction;
import com.hbsy.oa.domain.A;
import com.hbsy.oa.domain.B;
import com.hbsy.oa.domain.Menuitem;
import com.hbsy.oa.domain.User;
import com.hbsy.oa.service.MenuitemService;


@Controller("menuitemAction")
@Scope("prototype")
public class MenuitemAction extends BaseAction<Menuitem>{
	
	@Resource(name="menuitemService")
	private MenuitemService menuitemService;
	
	private Collection<Menuitem> menuitemList;


	public Collection<Menuitem> getMenuitemList() {
		return menuitemList;
	}

	
	
	public String showMenuitemsByUser(){
		this.menuitemList = this.menuitemService.getMenuitemsByUser();
		return SUCCESS;
	}

	public void setMenuitemService(MenuitemService menuitemService) {
		this.menuitemService = menuitemService;
	}
	
	public String showMenuitems(){
		this.menuitemList = this.menuitemService.getAllMenuitems(null);
		return SUCCESS;
	}
	
	public String showMeuitemsByPid(){
		this.menuitemList = this.menuitemService.getMenuitemsByPid(this.getModel().getPid());
		for(Menuitem menuitem:menuitemList){
			Set<User> users = menuitem.getUsers();
			for(User user:users){
				user.setDepartment(null);
				user.setPosts(null);
			}
		}
		return SUCCESS;
	}
	
	public String getAaa(){
		return "aaaa";
	}
	
	public A getBbb(){
		A a = new A();
		B b = new B();
		b.setS(null);
		List<B> bList = new ArrayList<B>();
		bList.add(b);
		a.setbList(bList);
		b.setA(a);
		return a;
	}
}
