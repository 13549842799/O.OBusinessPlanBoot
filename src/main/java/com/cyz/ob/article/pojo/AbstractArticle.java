package com.cyz.ob.article.pojo;

import com.cyz.basic.pojo.ModifierEntity;

@SuppressWarnings("serial")
public abstract class AbstractArticle<T> extends ModifierEntity<T> {
	
    private String title; //标题
	
	private String content; //内容 
	
	private Integer classify; // 分类
	
	private Byte source;// 来源 1-电脑 2-app
	
	public AbstractArticle() {
		super();
	}
	
	public AbstractArticle(T id, Byte delflag) {
		super(id, delflag);
	}

	public AbstractArticle(T id) {
		super(id);
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getClassify() {
		return classify;
	}

	public void setClassify(Integer classify) {
		this.classify = classify;
	}

	public Byte getSource() {
		return source;
	}

	public void setSource(Byte source) {
		this.source = source;
	}
}
