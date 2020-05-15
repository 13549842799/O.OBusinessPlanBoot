package com.cyz.ob.knowledge.pojo;

import java.util.List;

import com.cyz.basic.util.StrUtil;
import com.cyz.ob.article.pojo.AbstractArticle;
import com.cyz.ob.article.pojo.entity.Label;

/**
 * 
 * @author cyz
 *
 */
public class KnowledgeArticle extends AbstractArticle<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8451002789561666763L;
	
	

	public KnowledgeArticle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KnowledgeArticle(Integer id, Byte delflag) {
		super(id, delflag);
		// TODO Auto-generated constructor stub
	}

	public KnowledgeArticle(Integer id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	public KnowledgeArticle(Integer id, Integer userId) {
		super(id);
		setCreator(userId);
	}

	private String labels;
	
	private List<Label> labelList;

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public List<Label> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<Label> labelList) {
		this.labelList = labelList;
	}
	
    public String[] getSimpleLabls () {
		
		if (StrUtil.isEmpty(labels)) {
			return new String[0];
		}
		
		return labels.split(",");
	}

}
