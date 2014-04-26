package com.hbsy.oa.service.base;

import java.io.Serializable;
import java.util.Collection;

public interface BaseService<T> {

	public Collection<T> getAllEntry();
	
	public void updateEntry(T t);
	
	public void saveEntry(T t);
	
	public void deleteEntry(Serializable id);
	
	public T getEntryByID(Serializable id);
}
