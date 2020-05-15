package com.cyz.ob.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyz.basic.service.impl.BasicServiceImplTemplate;
import com.cyz.ob.article.mapper.PortionMapper;
import com.cyz.ob.article.pojo.entity.Portion;

@Service
public class PortionService extends BasicServiceImplTemplate<Portion, Integer> {
	
	@Autowired
	private PortionMapper mapper;

	@Override
	public Portion newEntity() {
		return new Portion();
	}
	
    public List<Portion> getExpandList(Portion portion) {
		
		return mapper.getPortionListSections(portion);
	}
    
    public Double getNewPortionNumber(Integer novelId) {
    	return mapper.getLaststNumer(novelId) + 1;
    }

}
