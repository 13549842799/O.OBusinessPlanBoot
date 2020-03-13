package com.cyz.ob.ouser.pojo.entity;

import com.cyz.basic.pojo.CreatorEntity;

/**
 * 用户实体类
 * @author cyz
 *
 */
public class Ouser extends CreatorEntity<Integer>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4517424619136772731L;

	private String accountname;
	
	private String bindPhone; //绑定手机
	
	private String password;
	
	private String nikename;
	
	private String avatar;  
	
	private Integer relatedid;
	
	private String autologin_mac;

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getBindPhone() {
		return bindPhone;
	}

	public void setBindPhone(String bindPhone) {
		this.bindPhone = bindPhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNikename() {
		return nikename;
	}

	public void setNikename(String nikename) {
		this.nikename = nikename;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getRelatedid() {
		return relatedid;
	}

	public void setRelatedid(Integer relatedid) {
		this.relatedid = relatedid;
	}

	public String getAutologin_mac() {
		return autologin_mac;
	}

	public void setAutologin_mac(String autologin_mac) {
		this.autologin_mac = autologin_mac;
	}

	@Override
	public String toString() {
		return "Ouser [accountname=" + accountname + ", bindPhone=" + bindPhone + ", password=" + password
				+ ", nikename=" + nikename + ", avatar=" + avatar + ", relatedid=" + relatedid + ", autologin_mac="
				+ autologin_mac + "]";
	}

	@Override
	public void acceptId(long id) {
		this.id = Integer.parseInt(String.valueOf(id));
	}

	
	
	
	

}
