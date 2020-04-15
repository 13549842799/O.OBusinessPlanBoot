package com.cyz.ob.authority.service;

import org.springframework.stereotype.Service;

import com.cyz.basic.mapper.BasicMapper;
import com.cyz.basic.service.impl.BasicServiceImplTemplate;
import com.cyz.ob.authority.pojo.Roles;

@Service
public class RolesService extends BasicServiceImplTemplate<Roles> {
	

	@Override
	public Roles newEntity() {
		
		return new Roles();
	}

}
