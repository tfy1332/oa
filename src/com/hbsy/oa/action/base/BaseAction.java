package com.hbsy.oa.action.base;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	private Class classt;
	private T t;
	
	public Class getClasst() {
		return classt;
	}
	public void setClasst(Class classt) {
		this.classt = classt;
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public BaseAction(){
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		this.classt = (Class)type.getActualTypeArguments()[0];
		try {
			this.t = (T) this.classt.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	public T getModel() {
		return this.t;
	}
	public static final String ADD_UI = "addUI";
	public static final String UPDATE_UI = "updateUI";
	public static final String LISTACTION = "listAction";
	public static final String ACTION2ACTION = "action2action";
	
	public static String addUI = BaseAction.ADD_UI;
	public static String updateUI = BaseAction.UPDATE_UI;
	public static String listAction = BaseAction.LISTACTION;
	public static String action2action = BaseAction.ACTION2ACTION;
}
