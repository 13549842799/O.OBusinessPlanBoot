package com.cyz.ob.article.pojo.entity;

import java.util.List;

import com.cyz.basic.pojo.ModifierEntity;

public class Portion extends ModifierEntity<Integer> {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5750761843853694065L;

	public static final byte WORKSINFO = 0; //作品相关
	
	public static final byte CONTENT = 1; //正文
	
	private String title;
	
	private String content;
	
	private Integer novelId;
	
	private Integer wordsNum;
	
	private Double number; //序号 ，卷号
	
	private Integer sectionNum; // 拥有的章节数
	
	private Byte type = CONTENT; //分卷类型 
	
	private List<Section> sections;
	
	private Novel novel;
	
	public Portion () {
	}
	
	public Portion (Integer id) {
		this.setId(id);
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

	public Integer getNovelId() {
		return novelId;
	}

	public void setNovelId(Integer novelId) {
		this.novelId = novelId;
	}

	public Integer getWordsNum() {
		return wordsNum;
	}

	public void setWordsNum(Integer wordsNum) {
		this.wordsNum = wordsNum;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public Integer getSectionNum() {
		return sectionNum;
	}

	public void setSectionNum(Integer sectionNum) {
		this.sectionNum = sectionNum;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}
	
	public boolean judgeWorksInfo() {
		return this.type == WORKSINFO;
	}

	public Novel getNovel() {
		return novel;
	}

	public void setNovel(Novel novel) {
		this.novel = novel;
	}

	@Override
	public void acceptId(long id) {
		this.id = Integer.parseInt(String.valueOf(id));
	}
}
