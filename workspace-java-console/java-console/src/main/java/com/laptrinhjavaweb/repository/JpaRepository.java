package com.laptrinhjavaweb.repository;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.paging.Pageable;

public interface JpaRepository<T> {

	public List<T> findAll(Map<String, Object> properties, Pageable pageable, Object... objects);
	public List<T> findAll(Map<String, Object> properties,  Object... objects);
	public List<T> findAll(String sql,Pageable pageable, Object...objects);
	Long insert(Object object);

}
