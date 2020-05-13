package com.cyz.ob.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyz.basic.service.impl.BasicServiceImplTemplate;
import com.cyz.ob.article.mapper.MemoMapper;
import com.cyz.ob.article.pojo.entity.Memo;

@Service
public class MemoService extends BasicServiceImplTemplate<Memo> {
	
	@Autowired
	private MemoMapper mapper;

	@Override
	public Memo newEntity() {
		return new Memo();
	}

}
