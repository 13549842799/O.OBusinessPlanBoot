package com.cyz.ob.knowledge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.basic.service.PageServiceTemplate;
import com.cyz.ob.knowledge.mapper.KnowledgeArticleMapper;
import com.cyz.ob.knowledge.pojo.KnowledgeArticle;

@Service
public class KnowledgeArticleService extends PageServiceTemplate<KnowledgeArticle, PageEntity<KnowledgeArticle>, Integer> {

	@Autowired
	private KnowledgeArticleMapper mapper;
	
	@Override
	public KnowledgeArticle newEntity() {
		return new KnowledgeArticle();
	}

}
