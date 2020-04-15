package com.cyz.ob.authority.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyz.basic.enumeration.DeleteFlag;
import com.cyz.basic.enumeration.StatusFlag;
import com.cyz.basic.service.impl.BasicServiceImplTemplate;
import com.cyz.ob.authority.mapper.AuthoritiesMapper;
import com.cyz.ob.authority.pojo.Authorities;
import com.cyz.ob.authority.pojo.Authorities.AuthoritiesBuilder;

@Service
public class AuthoritiesService extends BasicServiceImplTemplate<Authorities> {
	
	@Autowired
	private AuthoritiesMapper mapper;
	
	public List<Authorities> getAuthoritiesByUserId(int id) {
				
		List<Authorities> auths = getList(AuthoritiesBuilder.build().userId(id).create());
		
		return auths;
	}
	
	public List<Authorities> getAllAuthoritiesWithResources() {
		return mapper.getAllAuthoritiesWithResources(DeleteFlag.VALID.getCode(), StatusFlag.ENABLE.getCode());
	}

	@Override
	public Authorities newEntity() {
		
		return new Authorities();
	}

}
