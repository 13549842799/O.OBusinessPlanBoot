package com.cyz.ob.additional.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cyz.basic.service.impl.BasicServiceImplTemplate;
import com.cyz.ob.additional.mapper.WebMessageMapper;
import com.cyz.ob.ouser.pojo.entity.WebMessage;

@Service
public class WebMessageService extends BasicServiceImplTemplate<WebMessage, Integer> {
	
	@Autowired
	private WebMessageMapper mapper;
	
	public WebMessage getByCode(String code) {
		if (StringUtils.isEmpty(code)) {
			return null;
		}
		return mapper.getByCode(code);
	}
	
	public boolean hasWebMessage(String code) {
		return getByCode(code) != null;
	}

	@Override
	public WebMessage newEntity() {
		return new WebMessage();
	}

}
