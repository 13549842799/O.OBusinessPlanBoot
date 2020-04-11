package com.cyz.ob.authority.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyz.basic.enumeration.DeleteFlag;
import com.cyz.basic.enumeration.StatusFlag;
import com.cyz.basic.service.impl.BasicServiceImplTemplate;
import com.cyz.ob.authority.mapper.AuthorityMapper;
import com.cyz.ob.authority.pojo.Authority;
import com.cyz.ob.authority.pojo.Authority.AuthoritiesBuilder;

@Service
public class AuthorityService extends BasicServiceImplTemplate<Authority> {
	
	@Autowired
	private AuthorityMapper mapper;
	
	public List<Authority> getAuthoritiesByUserId(int id) {
				
		List<Authority> auths = getList(AuthoritiesBuilder.build().userId(id).create());
		
		return auths;
	}
	
	public List<Authority> getAllAuthoritiesWithResources() {
		return mapper.getAllAuthoritiesWithResources(DeleteFlag.VALID.getCode(), StatusFlag.ENABLE.getCode());
	}

	@Override
	public Authority newEntity() {
		
		return new Authority();
	}

}
