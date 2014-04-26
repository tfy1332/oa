package com.hbsy.oa.test;

import org.junit.Test;

import com.hbsy.oa.domain.Kynamic;
import com.hbsy.oa.service.KynamicService;


public class KynamicTest extends BaseSpring{
	@Test
	public void testSaveKynamic(){
		KynamicService kynamicService = (KynamicService)context.getBean("kynamicService");
		Kynamic kynamic = new Kynamic();
		kynamic.setIsParent(false);
		kynamic.setName("瑙勭珷鍒跺害");
		kynamic.setPid(1L);
		kynamicService.saveKynamic(kynamic);
	}
}
