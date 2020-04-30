package com.cyz.ob.article.pojo.entity;

import java.util.HashMap;
import java.util.Map;

import com.cyz.basic.pojo.CreatorEntity;
import com.cyz.basic.valid.annotation.EnableCheckOut;
import com.cyz.basic.valid.annotation.FieldMeta;
import com.fasterxml.jackson.annotation.JsonProperty;

@EnableCheckOut
public class Classify extends CreatorEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6582919586159676647L;

	/*
	 * 系统分类值
	 */
	public static final byte SYSTEMCLASSIFY = 1; 
	
	/*
	 * 用户自定义分类值
	 */
	public static final byte CUSTOMCLASSIFY = 2;
	
	/**
	 * 日记
	 */
	public static final byte DIARY = 1;
	
	/**
	 * 原本是总结，现在改为备忘
	 */
	public static final byte FinalReport = 2;
	
	public static final byte NOVEL = 4;
	
	public static final Map<Byte, String> mapperTable = new HashMap<>();
	
	{
		mapperTable.put(DIARY, "diary");
		//mapperTable.put(FinalReport, "finalreport");
		mapperTable.put(NOVEL, "novel");
	}
	
	@FieldMeta(name="分类名")
	private String name;
	
	private Byte type; // 1-系统  2-自定义
	
	@FieldMeta(name="所属")
	private Byte childType; // 1-日记  2-备忘 3-灵感   4-小说
	
	private int count = 0; //拥有文章数
	
	public Classify() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Classify(Byte delflag) {
		this.delflag = delflag;
	}

	public Classify(Integer id, Byte delflag) {
		super(id, delflag);
		// TODO Auto-generated constructor stub
	}

	public Classify(Integer id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 获取当前分类包含的是哪种类型的文章
	 * @return
	 */
	public String getThisTarget() {
		return mapperTable.get(this.childType);
	}
	
	/**
	 * 当前分类是否属于系统分类
	 * @return
	 */
	public boolean systemClassify() {
		return this.type == SYSTEMCLASSIFY;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty(defaultValue="1")
	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Byte getChildType() {
		return childType;
	}

	public void setChildType(Byte childType) {
		this.childType = childType;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public String getChildTypeName() {
		if (childType == null) {
			return "无";
		}
		switch (childType) {
		case DIARY:
			return "日记";
		case NOVEL:
			return "小说";
		default:
			return "无";
		}
	}

	@Override
	public void acceptId(long id) {
		this.id = Integer.parseInt(String.valueOf(id));
	}

}
