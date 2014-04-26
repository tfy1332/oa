package com.hbsy.oa.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;

import com.hbsy.oa.action.base.BaseAction;
import com.hbsy.oa.domain.A;
import com.hbsy.oa.domain.B;
import com.hbsy.oa.domain.Menuitem;
import com.hbsy.oa.domain.User;
import com.hbsy.oa.service.MenuitemService;
import com.hbsy.oa.service.PrivilegeService;
import com.hbsy.oa.service.UserService;


public class PrivilegeAction extends BaseAction<Menuitem>{
	@Resource(name="privilegeService")
	private PrivilegeService privilegeService;
	@Resource(name="menuitemService")
	private MenuitemService menuitemService;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	private UserService userService;

	@JSON(serialize=false)
	public MenuitemService getMenuitemService() {
		return menuitemService;
	}

	public void setMenuitemService(MenuitemService menuitemService) {
		this.menuitemService = menuitemService;
	}

	@JSON(serialize=false)
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private Collection<Menuitem> privilegeList;
	
	public Collection<Menuitem> getPrivilegeList() {
		return privilegeList;
	}
	
	private Long uid;
	private Long[] mids;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long[] getMids() {
		return mids;
	}

	public void setMids(Long[] mids) {
		this.mids = mids;
	}

	@JSON(serialize=false)
	public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}

	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}
	
	public String showPrivilege() throws Exception{
		this.privilegeList = this.privilegeService.getAllPrivilege(this.uid);
		//Thread.sleep(3000);
//		for(Menuitem menuitem:privilegeList){
//			Set<User> users = menuitem.getUsers();
//			for(User user:users){
//				user.setDepartment(null);
//				user.setPosts(null);
//			}
//		}
		return SUCCESS;
	}
	
	public A getAaa(){
		A a = new A();
		B b = new B();
		b.setS("aaa");
		List<B> bList = new ArrayList<B>();
		bList.add(b);
		a.setbList(bList);
		//b.setA(null);
		return a;
	}
	
	public String savePrivilege(){
		User user = this.userService.getUserByID(this.uid);
		Set<Menuitem> menuitems = this.menuitemService.getMenuitemsByMIDS(this.mids);
		user.setMenuitems(menuitems);
		this.userService.updateUser(user);
		this.message = "鎿嶄綔鎴愬姛";
		return SUCCESS;
	}
}
