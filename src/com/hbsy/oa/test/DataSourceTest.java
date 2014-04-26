package com.hbsy.oa.test;

import javax.sql.DataSource;

import org.junit.Test;

public class DataSourceTest extends BaseSpring{
	@Test
	public void testDataSource(){
		DataSource dataSource = (DataSource)context.getBean("dataSource");
	}
}
