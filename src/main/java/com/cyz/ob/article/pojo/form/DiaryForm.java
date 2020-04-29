package com.cyz.ob.article.pojo.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.cyz.ob.article.pojo.entity.Diary;

public class DiaryForm extends Diary {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6118169353766965119L;

	/**
	 * 
	 */
	
	public LocalDate startTime;
	
	public LocalDate endTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public LocalDate getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDate startTime) {
		this.startTime = startTime;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public LocalDate getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDate endTime) {
		this.endTime = endTime;
	}
	
	

}
