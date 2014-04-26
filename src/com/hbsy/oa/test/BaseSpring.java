package com.hbsy.oa.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseSpring {
	public static ApplicationContext context = null;
	static{
		//context = new ClassPathXmlApplicationContext("spring/applicationContext-annotation.xml");
		context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	}
}
