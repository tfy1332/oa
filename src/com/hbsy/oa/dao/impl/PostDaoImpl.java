package com.hbsy.oa.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hbsy.oa.dao.PostDao;
import com.hbsy.oa.dao.base.impl.BaseDaoImpl;
import com.hbsy.oa.domain.Post;
import com.hbsy.oa.domain.User;


public class PostDaoImpl extends BaseDaoImpl<Post> implements PostDao<Post>{
	private HibernateTemplate hibernateTemplate;
	private SessionFactory sessionFactory;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Set<Post> getPostsByIds(Long[] pids) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from Post");
		stringBuffer.append(" where pid in(");
		for(int i=0;i<pids.length;i++){
			if(i<pids.length-1){
				stringBuffer.append(pids[i]+",");
			}else{
				stringBuffer.append(pids[i]);
			}
		}
		stringBuffer.append(")");
		return new HashSet<Post>(this.getHibernateTemplate().find(stringBuffer.toString()));
	}

	public void deletePost(Serializable id) {
		Post  post= this.getPostById(id);
		Set<User> users = post.getUsers();
		Set<Post> posts=new HashSet<Post>();
		posts.add(post);
		for(User user:users){
			user.setPosts(posts);
		}
		this.getHibernateTemplate().delete(post);
		
	}

	public Post getPostById(Serializable id) {
		return (Post)this.getHibernateTemplate().get(Post.class, id);
	}

	public void savePost(Post post) {
		this.getHibernateTemplate().save(post);
	}

	public void updatePost(Post post) {
		this.getHibernateTemplate().update(post);
		
	}

	@SuppressWarnings("unchecked")
	public Collection<Post> getAllPost() {
		return this.getHibernateTemplate().find("from  Post");
	}

}
