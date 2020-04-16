package com.cyz.ob.basic.entity;

public class PageEntity<T> implements PagePlus<T> {
	
	public int pageNum;
	
	public int pageSize;
	
	public T params;

	

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public T getParams() {
		return params;
	}

	public void setParams(T params) {
		this.params = params;
	}

	
	
	

}
