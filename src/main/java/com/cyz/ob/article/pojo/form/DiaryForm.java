package com.cyz.ob.article.pojo.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.cyz.ob.article.pojo.entity.Diary;

public class DiaryForm extends Diary {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4419043609625929464L;
	
	private String classifyName;
	
	public LocalDate startTime;
	
	public LocalDate endTime;

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

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
