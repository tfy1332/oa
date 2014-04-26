package com.hbsy.oa.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hbsy.oa.action.base.BaseAction;
import com.hbsy.oa.domain.User;
import com.hbsy.oa.service.LoginService;
import com.hbsy.oa.utils.OAUtils;


@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction<User>{
	@Resource(name="loginService")
	private LoginService loginService;
	
	public LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public String login(){
		User user = this.loginService.login(this.getModel().getUsername(), this.getModel().getPassword());
		if(user==null){//鐢ㄦ埛鍚嶅拰瀵嗙爜閿欒
			//鍦ㄧ櫥闄嗛〉闈㈡樉绀洪敊璇俊鎭�
			return null;
		}else{
			OAUtils.putUserToSession(user);
			return "index";
		}
	}
	public String logout(){
		return "login";
	}
}
