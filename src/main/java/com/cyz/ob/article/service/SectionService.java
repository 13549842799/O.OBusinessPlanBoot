package com.cyz.ob.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyz.basic.service.impl.BasicServiceImplTemplate;
import com.cyz.ob.article.mapper.SectionMapper;
import com.cyz.ob.article.pojo.entity.Section;

@Service
public class SectionService extends BasicServiceImplTemplate<Section> {
	
	@Autowired
	private SectionMapper mapper;

	@Override
	public Section newEntity() {
		// TODO Auto-generated method stub
		return new Section();
	}
	
	public Long lastSectionId(Section current) {
		if (current == null) {
			return null;
		}
		return mapper.lastSectionId(current);
	}

	public Long nextSectionId(Section current) {
		if (current == null) {
			return null;
		}
		return mapper.nextSectionId(current);
	}

}
