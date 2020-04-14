package com.cyz.ob.additional.pojo.entity;

import java.time.LocalDateTime;

import com.cyz.basic.pojo.DeleteAbleEntity;

/**
 * 短信实体
 * @author cyz
 *
 */
public class Msg extends DeleteAbleEntity<Integer>{
	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -4254878024214715099L;

	public static final byte PASSWORD = 1; //验证密码类型
	
	public static final byte PHONE = 2; //验证绑定手机号类型
	
	private String code;
	private byte type;
	private String phoneNo;
	private LocalDateTime createTime;
	private LocalDateTime validTime;
	
	

	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public byte getType() {
		return type;
	}



	public void setType(byte type) {
		this.type = type;
	}



	public String getPhoneNo() {
		return phoneNo;
	}



	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}



	public LocalDateTime getCreateTime() {
		return createTime;
	}



	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}



	public LocalDateTime getValidTime() {
		return validTime;
	}



	public void setValidTime(LocalDateTime validTime) {
		this.validTime = validTime;
	}



	@Override
	public void acceptId(long id) {
		this.id = Integer.parseInt(String.valueOf(id));
	}

}
