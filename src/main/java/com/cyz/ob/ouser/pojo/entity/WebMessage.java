package com.cyz.ob.ouser.pojo.entity;

import java.time.LocalDate;

import com.cyz.basic.pojo.IdEntity;

public class WebMessage extends IdEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3368405097525556252L;
	private String code;
	private String name;
	private String homepage;
	private String signoutAddress;
	private LocalDate jointime;
	
	
	
	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getHomepage() {
		return homepage;
	}



	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}



	public String getSignoutAddress() {
		return signoutAddress;
	}



	public void setSignoutAddress(String signoutAddress) {
		this.signoutAddress = signoutAddress;
	}



	public LocalDate getJointime() {
		return jointime;
	}



	public void setJointime(LocalDate jointime) {
		this.jointime = jointime;
	}



	@Override
	public void acceptId(long id) {
		this.id = Integer.parseInt(String.valueOf(id));
	}
}
