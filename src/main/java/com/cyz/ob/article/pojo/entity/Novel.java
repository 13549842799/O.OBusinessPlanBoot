package com.cyz.ob.article.pojo.entity;

import java.util.List;

import com.cyz.basic.valid.annotation.EnableCheckOut;
import com.cyz.ob.article.pojo.AbstractArticle;

@EnableCheckOut
public class Novel extends AbstractArticle<Integer> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8221362076350065967L;

	/**
	 * 小说的连载状态：未开始
	 */
	public static final byte UNSTART = 0;

	private String cover; //封面
	
	private String labels; //标签
	
	private Integer wordsNum;// 整本书字数
	
	private List<Portion> portions; //分卷集合
	
	private Byte novelState;// 状态  0-未开始 1-连载中 2-没有更新 3-完结
	
	private Integer portionsNum;
	
	private Integer sectionsNum;
	
	private Long lastetSectionId; //最新的章节
	
	//非持久化字段
	
	private String classifyName;
	
	private List<Label> labelList;
	
	private String authorName;
	
	private Section lastetSection;
	
	public Novel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Novel(Integer id, Byte delflag) {
		super(id, delflag);
		// TODO Auto-generated constructor stub
	}
	
	public Novel(Integer id, Integer creator) {
		super(id);
		this.setCreator(creator);
	}

	public Novel(Integer id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public Integer getWordsNum() {
		return wordsNum;
	}

	public void setWordsNum(Integer wordsNum) {
		this.wordsNum = wordsNum;
	}

	public List<Portion> getPortions() {
		return portions;
	}

	public void setPortions(List<Portion> portions) {
		this.portions = portions;
	}

	public Byte getNovelState() {
		return novelState;
	}

	public void setNovelState(Byte novelState) {
		this.novelState = novelState;
	}

	public Integer getPortionsNum() {
		return portionsNum;
	}

	public void setPortionsNum(Integer portionsNum) {
		this.portionsNum = portionsNum;
	}

	public Integer getSectionsNum() {
		return sectionsNum;
	}

	public void setSectionsNum(Integer sectionsNum) {
		this.sectionsNum = sectionsNum;
	}

	public Long getLastetSectionId() {
		return lastetSectionId;
	}

	public void setLastetSectionId(Long lastetSectionId) {
		this.lastetSectionId = lastetSectionId;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	
	public List<Label> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<Label> labelList) {
		this.labelList = labelList;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	

	public Section getLastetSection() {
		return lastetSection;
	}

	public void setLastetSection(Section lastetSection) {
		this.lastetSection = lastetSection;
	}
	
	/**
	 * 状态名
	 * @return
	 */
	public String getStateName() {
		if (this.getNovelState() == null) {
			this.setNovelState(Novel.UNSTART);
		}
		switch (this.getNovelState()) {
		case 0:
			return "未开始";
		case 1:
			return "连载中";
		case 2:
			return "没有更新";
		case 3:
			return "完结";
		}
		return "";
	}

	public String getWordsNumStr() {
		if(this.getWordsNum() == null) {
			return "0字";
		}
		if (this.getWordsNum() < 10000) {
			return this.getWordsNum() + "字";
		}
		return String.format("%.2f万字", this.getWordsNum().doubleValue()/10000);
	}

}
