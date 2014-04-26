package com.hbsy.oa.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import com.hbsy.oa.domain.Post;


public interface PostService {
	/**
	 * 查询所有的岗位
	 * @return
	 */
	public Collection<Post> getAllPost();
	
/**
 * 根据 id 查询 岗位
 * @param id
 * @return
 */
	public Post getPostById(Serializable id);

	/**
	 *  保存 岗位
	 * @param post
	 */
	public void savePost(Post post);
/**
 *  更新岗位
 * @param post
 */
	public void updatePost(Post post);
/**
 *  根据 id删除 岗位
 * @param id
 */
	public void deletePost(Serializable id);
	
	public Set<Post> getPostsByIds(Long[] pids);
}
