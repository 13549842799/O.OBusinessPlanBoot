package com.cyz.ob.common.configuration.security;

import java.util.ArrayList;
import java.util.List;

import com.cyz.basic.config.security.core.userdetails.User;
import com.cyz.basic.config.security.core.userdetails.User.UserBuilder;
import com.cyz.basic.config.security.core.userdetails.UserDetails;
import com.cyz.basic.config.security.detail.SecurityAuthority;
import com.cyz.basic.config.security.provisioning.UserDetailServiceSupport;
import com.cyz.ob.authority.pojo.Authorities;
import com.cyz.ob.authority.service.AuthoritiesService;
import com.cyz.ob.ouser.pojo.entity.Ouser;
import com.cyz.ob.ouser.service.impl.OuserService;

public class UserDetailServiceImpl extends UserDetailServiceSupport<Ouser> {
	
	private final OuserService userService;

	private final AuthoritiesService authorityService;
	
	public UserDetailServiceImpl(OuserService userService, AuthoritiesService authorityService) {
		this.userService = userService;
		this.authorityService = authorityService;
	}

	@Override
	public UserDetails createUserDetail(Ouser user, List<SecurityAuthority> auths) {
		
		User securityUser = new User(user.getUsername(), user.getPassword(), auths);
		
		return securityUser;
	}

	@Override
	public Ouser getUserByUsername(String username) {
		return userService.getByUsername(username);
	}

	@Override
	public List<SecurityAuthority> getAuthsByUsername(Ouser user) {
		List<SecurityAuthority> auths = new ArrayList<>();		
	    List<Authorities> authorites = (authorites = authorityService.getAuthoritiesByUserId(user.getId())) != null ? authorites : new ArrayList<>();
	    auths.addAll(authorites);
		return auths;
	}

	
	

}
