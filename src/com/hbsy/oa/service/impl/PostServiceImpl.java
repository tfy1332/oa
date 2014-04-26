package com.hbsy.oa.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import com.hbsy.oa.dao.PostDao;
import com.hbsy.oa.domain.Post;
import com.hbsy.oa.service.PostService;


public class PostServiceImpl implements PostService{
	private PostDao postDao;

	public PostDao getPostDao() {
		return postDao;
	}

	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}

	public Collection<Post> getAllPost() {
		return this.postDao.getAllPost();
	}

	public Set<Post> getPostsByIds(Long[] pids) {
		return this.postDao.getPostsByIds(pids);
	}

	public void deletePost(Serializable id) {
		 this.postDao.deletePost(id);
		
	}

	public Post getPostById(Serializable id) {
		return this.postDao.getPostById(id);
	}

	public void savePost(Post post) {
		 this.postDao.savePost(post);
		
	}

	public void updatePost(Post post) {
		 this.postDao.updatePost(post);
		
	}
}
