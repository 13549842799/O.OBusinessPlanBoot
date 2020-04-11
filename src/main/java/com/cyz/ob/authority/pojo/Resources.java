package com.cyz.ob.authority.pojo;

import com.cyz.basic.pojo.ModifyEntity;

public class Resources extends ModifyEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1871930684499623757L;

	private Integer pid;
	
	private Integer authId;
	
	private String name;
	
	private String displayName;
	
	private String url;
	
	private Byte type;
	
	private String describes;
	
	private Byte locking;
	
	private Byte state;
	
	private Byte delflag;
	
	

	public Integer getPid() {
		return pid;
	}



	public void setPid(Integer pid) {
		this.pid = pid;
	}



	public Integer getAuthId() {
		return authId;
	}



	public void setAuthId(Integer authId) {
		this.authId = authId;
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



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public Byte getType() {
		return type;
	}



	public void setType(Byte type) {
		this.type = type;
	}



	public String getDescribes() {
		return describes;
	}



	public void setDescribes(String describes) {
		this.describes = describes;
	}



	public Byte getLocking() {
		return locking;
	}



	public void setLocking(Byte locking) {
		this.locking = locking;
	}



	public Byte getState() {
		return state;
	}



	public void setState(Byte state) {
		this.state = state;
	}



	public Byte getDelflag() {
		return delflag;
	}



	public void setDelflag(Byte delflag) {
		this.delflag = delflag;
	}



	@Override
	public void acceptId(long id) {
		this.id = Integer.parseInt(String.valueOf(id));
	}
	
	

}
