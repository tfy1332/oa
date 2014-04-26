package com.hbsy.oa.test;

import java.util.Collection;

import org.junit.Test;

import com.hbsy.oa.domain.Post;
import com.hbsy.oa.service.PostService;


public class PostTest extends BaseSpring{
	@Test
	public void testQuery(){
		PostService postService = (PostService)context.getBean("postService");
		Collection<Post> pList = postService.getAllPost();
		System.out.println(pList.size());
	}
}
