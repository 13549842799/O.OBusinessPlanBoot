package com.cyz.ob.basic.entity;

public interface PagePlus<T> {
	
	int getPageNum();
	
	void setPageNum(int pageNum);
	
	int getPageSize();
	
	void setPageSize(int pageSize);
	
	T getParams();
	
	void setParams(T t);

}
