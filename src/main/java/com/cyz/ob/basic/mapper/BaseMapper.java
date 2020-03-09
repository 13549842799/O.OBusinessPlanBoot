package com.cyz.ob.basic.mapper;

/**
 * 
 * @author cyz
 *
 * @param <T>
 */
public interface BaseMapper<T> {
	
	int add(T t);
	
	void delete(T t);
	
	int update(T t);
	
	T get(T t);

}
