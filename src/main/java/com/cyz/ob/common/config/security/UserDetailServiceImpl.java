package com.cyz.ob.common.config.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cyz.basic.config.security.core.userdetails.UserDetails;
import com.cyz.basic.config.security.detail.SecurityAuthority;
import com.cyz.basic.config.security.detail.SecurityUser;
import com.cyz.basic.config.security.provisioning.UserDetailServiceSupport;
import com.cyz.ob.authority.pojo.Authority;
import com.cyz.ob.authority.service.AuthorityService;
import com.cyz.ob.ouser.pojo.entity.Ouser;
import com.cyz.ob.ouser.service.impl.OuserService;

public class UserDetailServiceImpl extends UserDetailServiceSupport {
	
	@Autowired
	private OuserService userService;
	@Autowired
	private AuthorityService authorityService;

	@Override
	public UserDetails createUserDetail(SecurityUser user, List<SecurityAuthority> auths) {
		return new Ouser(user.getUsername(), user.getPassword(), auths);
	}

	@Override
	public SecurityUser getUserByUsername(String username) {
		return userService.getByUsername(username);
	}

	@Override
	public List<SecurityAuthority> getAuthsByUsername(SecurityUser user) {
		List<SecurityAuthority> auths = new ArrayList<>();		
	    List<Authority> authorites = (authorites = authorityService.getAuthoritiesByUserId(user.getId())) != null ? authorites : new ArrayList<>();
	    auths.addAll(authorites);
		return auths;
	}

	
	

}
