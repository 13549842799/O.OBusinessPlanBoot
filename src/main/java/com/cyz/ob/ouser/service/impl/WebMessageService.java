package com.cyz.ob.ouser.service.impl;

import org.springframework.stereotype.Service;

import com.cyz.basic.service.impl.BasicServiceImplTemplate;
import com.cyz.ob.ouser.pojo.entity.WebMessage;

@Service
public class WebMessageService extends BasicServiceImplTemplate<WebMessage> {

	@Override
	public WebMessage newEntity() {
		return new WebMessage();
	}

}
