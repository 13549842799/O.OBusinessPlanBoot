package com.cyz.ob.ouser.pojo.entity;

import java.math.BigDecimal;

import com.cyz.basic.pojo.CreatorEntity;

public class AccountBook extends CreatorEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4999390421953582847L;

	private String name;
	
	private BigDecimal amount; //当前金额
	
	private BigDecimal payMoney;
	
	private BigDecimal inCome;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}

	public BigDecimal getInCome() {
		return inCome;
	}

	public void setInCome(BigDecimal inCome) {
		this.inCome = inCome;
	}
	
	
	
	

}
