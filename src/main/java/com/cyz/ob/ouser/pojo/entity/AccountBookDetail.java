package com.cyz.ob.ouser.pojo.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.cyz.basic.pojo.CreatorEntity;

public class AccountBookDetail extends CreatorEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2461644146706356453L;

	private String name;
	
	private Byte type;
	
	private BigDecimal amountDetail;
	
	private LocalDate date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public BigDecimal getAmountDetail() {
		return amountDetail;
	}

	public void setAmountDetail(BigDecimal amountDetail) {
		this.amountDetail = amountDetail;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
}
