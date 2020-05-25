package com.cyz.ob.ouser.pojo.entity;

import com.cyz.basic.pojo.CreatorEntity;

public class Account extends CreatorEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1250504785401212949L;

	private String username;

    private String password;

    private String source;

    private String bind_email;

    private String bind_phone;

    private String remark;
    
    

	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getSource() {
		return source;
	}



	public void setSource(String source) {
		this.source = source;
	}



	public String getBind_email() {
		return bind_email;
	}



	public void setBind_email(String bind_email) {
		this.bind_email = bind_email;
	}



	public String getBind_phone() {
		return bind_phone;
	}



	public void setBind_phone(String bind_phone) {
		this.bind_phone = bind_phone;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	@Override
	public void acceptId(long id) {
		this.id = Integer.parseInt(String.valueOf(id));
	}

}
