package com.cyz.ob.authority.pojo;

public class AuthoritiesForm extends Authorities {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2268153441357770877L;
	private Integer roleId;
	private Integer userId;
	private Byte withRes;
	
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Byte getWithRes() {
		return withRes;
	}
	public void setWithRes(Byte withRes) {
		this.withRes = withRes;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	

}
