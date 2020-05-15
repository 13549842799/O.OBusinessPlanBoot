package com.cyz.ob.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyz.basic.enumeration.DeleteFlag;
import com.cyz.ob.article.mapper.ClassifyMapper;
import com.cyz.ob.article.pojo.entity.Classify;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.basic.service.PageServiceTemplate;

@Service
public class ClassifyService extends PageServiceTemplate<Classify, PageEntity<Classify>, Integer> {
	
	@Autowired
	private ClassifyMapper mapper;

	@Override
	public Classify newEntity() {
		return new Classify();
	}
	
	

	@Override
	public boolean delete(Classify t) {
		boolean result =  super.delete(t);
		
		if (result) {
			int count = mapper.classifyArticleCount(t.getThisTarget(), t.getId(), t.getCreator(), DeleteFlag.VALID.getCode());
			if (count > 0) {
				mapper.alterArticleClassify(t.getThisTarget(), t.getCreator(), t.getId(), 22);
			}
		}
		
		return result;
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
