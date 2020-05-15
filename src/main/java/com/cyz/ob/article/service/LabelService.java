package com.cyz.ob.article.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyz.basic.enumeration.DeleteFlag;
import com.cyz.basic.pojo.CreatorEntity;
import com.cyz.ob.article.mapper.LabelMapper;
import com.cyz.ob.article.pojo.entity.Label;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.basic.service.PageServiceTemplate;

@Service
public class LabelService extends PageServiceTemplate<Label, PageEntity<Label>, Integer> {
	
	@Autowired
	private LabelMapper mapper;

	@Override
	public Label newEntity() {
		return new Label();
	}
	
	/**
	 * 批量插入更新标签
	 * @param labels
	 * @param target 标签的关联对象
	 * @param targetType 标签的类型
	 */
    public void batchAddOrUpdate(List<Label> labels, CreatorEntity<Integer> target, byte targetType) {
		
		if (labels == null || labels.size() == 0) {
			return;
		}
		for (Label l : labels) {
			l.setCreator(target.getCreator());
			l.setCreateTime(LocalDateTime.now());
			l.setTargetType(targetType);
			l.setTargetId(target.getId());
		}
		mapper.insertOrUpdate(labels);
	}
	
	/**
	 * 获取当前用户常用的标签和系统中常用的标签
	 * @param label
	 * @return
	 */
    public Set<String> frequentlyUsedLabelNames(Label label) {
		
		Set<String> nameForSelf = getFrequentlyUsedLabel(label.getCreator(), label.getTargetType(), 6);
	
		Set<String> nameForAll = getFrequentlyUsedLabel(null, label.getTargetType(), 6);
		
		nameForSelf.addAll(nameForAll);
		return nameForSelf;
	}
    
    private Set<String> getFrequentlyUsedLabel(Integer creator, Byte targetType, int count) {
    	Set<String> labels = mapper.frequentlyUsedLabelNames(creator, targetType, DeleteFlag.VALID.getCode(), 6);  	
    	return labels != null ? labels : new HashSet<>();
    }
    
    public List<Label> labels(Label label) {
    	return mapper.labels(label);
    }

}
