package com.cyz.ob.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyz.ob.article.mapper.ClassifyMapper;
import com.cyz.ob.article.pojo.entity.Classify;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.basic.service.PageServiceTemplate;

@Service
public class ClassifyService extends PageServiceTemplate<Classify, PageEntity<Classify>> {
	
	@Autowired
	private ClassifyMapper mapper;

	@Override
	public Classify newEntity() {
		return new Classify();
	}

	/**
	 * 获取简单的分类列表（id, name）
	 * @param cls
	 * @return
	 */
	public List<Classify> getSimpleList(Classify cls) {
		cls.delflag();
		return mapper.getSimpleList(cls);
	}

}
