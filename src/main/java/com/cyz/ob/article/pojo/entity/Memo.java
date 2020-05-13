package com.cyz.ob.article.pojo.entity;

import java.time.LocalDate;

import com.cyz.ob.article.pojo.AbstractArticle;

/**
 * 备忘
 * @author cyz
 *
 */
public class Memo extends AbstractArticle<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6888801851014464706L;
	private LocalDate date;
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public void acceptId(long id) {
		this.id = Integer.parseInt(String.valueOf(id));
	}

}
