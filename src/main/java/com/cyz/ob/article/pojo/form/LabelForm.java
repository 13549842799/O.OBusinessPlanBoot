package com.cyz.ob.article.pojo.form;

import com.cyz.ob.article.pojo.entity.Label;

public class LabelForm extends Label {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8569176387765989937L;

	private String targetTypes;
	
	private Integer maxCount;
	
	private Integer minCount;

	public String getTargetTypes() {
		return targetTypes;
	}

	public void setTargetTypes(String targetTypes) {
		this.targetTypes = targetTypes;
	}

	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	public Integer getMinCount() {
		return minCount;
	}

	public void setMinCount(Integer minCount) {
		this.minCount = minCount;
	}
	
	

}
