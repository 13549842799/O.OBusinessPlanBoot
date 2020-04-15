package com.cyz.ob.authority.pojo;

import com.cyz.basic.pojo.DeleteAbleEntity;

public class Roles extends DeleteAbleEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3789750542162598727L;

	private String name;
	
	private String displayName;
	
	private String describes;

	@Override
	public void acceptId(long id) {
		this.id = Integer.parseInt(String.valueOf(id));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}
	
	

}
