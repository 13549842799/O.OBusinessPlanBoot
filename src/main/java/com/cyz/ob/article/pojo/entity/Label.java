package com.cyz.ob.article.pojo.entity;

import com.cyz.basic.pojo.CreatorEntity;

public class Label extends CreatorEntity<Integer> {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4254461449267745901L;

	public static final byte SYSTEM = 0;
	
	public static final byte USER = 1;
	
	public Label () {}
	

	public Label(Integer id, Byte delflag) {
		super(id, delflag);
	}

	public Label(Integer id) {
		super(id);
	}

	private String name;
	
	/**
	 * 1-日记 4-小说
	 */
	private Byte targetType;
	
	/**
	 * 关联的实体的id 日记,小说,
	 */
	private Integer targetId;
	
	private Integer count;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getTargetType() {
		return targetType;
	}


	public void setTargetType(Byte targetType) {
		this.targetType = targetType;
	}


	public Integer getTargetId() {
		return targetId;
	}


	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	

	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}


	/**
	 * 为前端提供check属性初始值
	 * @return
	 */
	public boolean getCheck() {
		return false;
	}

	@Override
	public void acceptId(long id) {
		this.id = Integer.parseInt(String.valueOf(id));
	}

}
