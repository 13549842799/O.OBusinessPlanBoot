package com.cyz.ob.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyz.ob.article.mapper.NovelMapper;
import com.cyz.ob.article.pojo.entity.Novel;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.basic.service.PageServiceTemplate;

@Service
public class NovelService extends PageServiceTemplate<Novel, PageEntity<Novel>, Integer> {
	
	@Autowired
	private NovelMapper mapper;

	@Override
	public Novel newEntity() {
		return new Novel();
	}

}
