package com.hbsy.oa.dao.base;

import java.io.Serializable;
import java.util.Collection;

public interface BaseDao<T> {

	public Collection<T> getAllEntry();
	
	public void updateEntry(T t);
	
	public void saveEntry(T t);
	
	public void deleteEntry(Serializable id);
	
	public T getEntryByID(Serializable id);
}
