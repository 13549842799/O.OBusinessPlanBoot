package com.cyz.ob.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyz.ob.article.mapper.DiaryMapper;
import com.cyz.ob.article.pojo.entity.Diary;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.basic.service.PageServiceTemplate;

@Service
public class DiaryService extends PageServiceTemplate<Diary, PageEntity<Diary>, Integer> {
	
	@Autowired
	private DiaryMapper mapper;

	@Override
	public Diary newEntity() {
		
		return new Diary();
	}

}
