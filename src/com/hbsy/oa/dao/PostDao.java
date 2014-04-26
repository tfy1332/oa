package com.hbsy.oa.dao;


import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import com.hbsy.oa.dao.base.BaseDao;
import com.hbsy.oa.domain.Post;



public interface PostDao<T> extends BaseDao<T>{
	public Collection<Post> getAllPost();
	public Set<Post> getPostsByIds(Long[] pids);

	public void deletePost(Serializable id);

	public Post getPostById(Serializable id);

	public void savePost(Post post);

	public void updatePost(Post post);
}
