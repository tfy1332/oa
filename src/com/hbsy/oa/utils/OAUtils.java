package com.hbsy.oa.utils;

import com.hbsy.oa.domain.User;
import com.opensymphony.xwork2.ActionContext;


public class OAUtils {
	public static void putUserToSession(User user){
		ActionContext.getContext().getSession().put("user", user);
	}
	
	public static User getUserFromSession(){
		return (User)ActionContext.getContext().getSession().get("user");
	}
}
