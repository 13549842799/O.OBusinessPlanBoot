package com.cyz.ob.article.pojo.entity;

import java.time.LocalDate;
import java.util.List;

import com.cyz.basic.util.StrUtil;
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
	//数据库字段
	private LocalDate date ;// 日记日期
	
	private Byte status; // 日记状态
	
	//数据库字段 end
	
	private String labels; //格式 标签1,标签2,标签3...
	
	private List<Label> labelList;

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
	
	public String[] getSimpleLabls () {
		
		if (StrUtil.isEmpty(labels)) {
			return new String[0];
		}
		
		return labels.split(",");
	}
	
	public List<Label> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<Label> labelList) {
		this.labelList = labelList;
	}

	public String getStatusName() {
		if (status == null) {
			return "";
		}
		switch (status) {
		case 0:
			return "草稿";
		case 1:
			return "私密";
		case 2:
			return "发布";
		default:
			return "";
		}
	}
	
	public String getDateStr() {
		if (date == null) {
			return "";
		}
		return StrUtil.formatDate(date);
	}

}
