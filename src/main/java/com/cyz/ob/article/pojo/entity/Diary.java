package com.cyz.ob.article.pojo.entity;

import java.time.LocalDate;

import com.cyz.ob.article.pojo.AbstractArticle;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Diary extends AbstractArticle<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8423862982234247029L;

	/**
	 * 
	 */
	
	private LocalDate date ;// 日记日期
	
	private String labels; //格式 标签1,标签2,标签3...
	
	private Byte status; // 日记状态

	public Diary() {
		super();
	}
	
	public Diary(Integer id, Integer creator, Byte delflag) {
		this(id, delflag);
		this.setCreator(creator);
	}

	public Diary(Integer id, Byte delflag) {
		super(id, delflag);
	}

	public Diary(Integer id, Integer creator) {
		this(id);
		this.setCreator(creator);
	}
	
	public Diary(Integer id) {
		super(id);
	}
	
	@JsonFormat(pattern="yyyy-MM-dd")
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Override
	public void acceptId(long id) {
		this.id = Integer.parseInt(String.valueOf(id));
	}

}
