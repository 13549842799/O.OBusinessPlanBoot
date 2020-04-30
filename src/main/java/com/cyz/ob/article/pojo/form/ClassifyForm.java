package com.cyz.ob.article.pojo.form;

import com.cyz.ob.article.pojo.entity.Classify;

public class ClassifyForm extends Classify {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8070789851522511096L;

	private Integer maxCount;
	
	private Integer minCount;

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
