package com.cyz.ob.article.pojo.entity;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.cyz.basic.pojo.ModifierEntity;
import com.cyz.ob.upload.pojo.UploadFile;

public class Section extends ModifierEntity<Long> {
	
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -2329540278917067904L;

	private String title;
	
	private String content;

	private Integer novelId;//小说id
	
	private Integer portionId;//分卷id
	
	private String remark; //备注，也就是ps
	
	private Integer wordsNum;
	
	private Double number; //章节号
	
	private List<UploadFile> files;
	
	private Long lastSection;
	
	private Long nextSection;
	
	private Novel novel;
	
	private Portion portion;
	
	
	public Section() {}
	
	public Section(Long id) {
		this.setId(id);
	}

	public Integer getPortionId() {
		return portionId;
	}

	public void setPortionId(Integer portionId) {
		this.portionId = portionId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getWordsNum() {
		return wordsNum;
	}

	public void setWordsNum(Integer wordsNum) {
		this.wordsNum = wordsNum;
	}

	public List<UploadFile> getFiles() {
		return files;
	}

	public void setFiles(List<UploadFile> files) {
		this.files = files;
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

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public Long getLastSection() {
		return lastSection;
	}

	public void setLastSection(Long lastSection) {
		this.lastSection = lastSection;
	}

	public Long getNextSection() {
		return nextSection;
	}

	public void setNextSection(Long nextSection) {
		this.nextSection = nextSection;
	}
	

	public Integer getNovelId() {
		return novelId;
	}

	public void setNovelnId(Integer novelId) {
		this.novelId = novelId;
	}
	
	public Novel getNovel() {
		return novel;
	}

	public void setNovel(Novel novel) {
		this.novel = novel;
	}

	public Portion getPortion() {
		return portion;
	}

	public void setPortion(Portion portion) {
		this.portion = portion;
	}

	@Override
	public void acceptId(long id) {
		setId(id);
	}
	
	public String getCreateTimeStr() {
		if (this.getCreateTime() == null) {
			return null;
		}
		Duration d = Duration.between(createTime, LocalDateTime.now());
		long distance = d.getSeconds();
		if (distance < 60) {
			return "刚刚";
		}
		distance = distance/60; //分钟
		if (distance <60) {
			return distance + "分钟前";
		}
		distance = distance/60; //小时
		if (distance < 24) {
			return distance + "小时前";
		}
		distance = distance/24;
		if (distance < 30) {
			return distance + "天前";
		}
		distance = distance/30;
		if (distance < 12) {
			return distance + "个月前";
		}
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        return sdf.format(this.getCreateTime());
	}

}
