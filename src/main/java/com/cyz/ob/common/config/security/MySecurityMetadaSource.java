package com.cyz.ob.common.config.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyz.basic.config.security.access.ConfigAttribute;
import com.cyz.basic.config.security.service.SecurityMetadataSourceSupport;
import com.cyz.basic.config.security.web.util.matcher.AntPathRequestMatcher;
import com.cyz.basic.config.security.web.util.matcher.RequestMatcher;
import com.cyz.ob.authority.pojo.Authority;
import com.cyz.ob.authority.pojo.Resources;
import com.cyz.ob.authority.service.AuthorityService;

@Service("securityMetadataSource")
public class MySecurityMetadaSource extends SecurityMetadataSourceSupport {

	@Autowired
	private AuthorityService authorityService;
	
	@Override
	protected Map<RequestMatcher, Collection<ConfigAttribute>> createMap() {
		
		List<Authority> auths = authorityService.getAllAuthoritiesWithResources();

		LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
		
		for (Authority auth : auths) {
			if (auth.getResources() == null || auth.getResources().size() == 0) {
				continue;
			}
			Set<ConfigAttribute> configs = new HashSet<ConfigAttribute>(auth.getResources().size());
			configs.add(new com.cyz.basic.config.security.access.SecurityConfig(auth.getAuthority()));
			for (Resources r : auth.getResources()) {
				RequestMatcher matcher = new AntPathRequestMatcher(r.getUrl());
				requestMap.put(matcher, configs);
			}
		}
		
		/*
		
		for (SecurityResource resource : resourceList) {
			//资源对应权限名
			List<String> authorityNames = resource.getAuthorityNames();

			if (authorityNames!=null&&authorityNames.size()>0) {
				Set<ConfigAttribute> configs = new HashSet<ConfigAttribute>(authorityNames.size());
				for (String authority : authorityNames) {
					configs.add(new SecurityConfig(authority));
				}
				RequestMatcher matcher = new AntPathRequestMatcher(resource.getValue());
				requestMap.put(matcher, configs);
			}
		}*/
		
		return requestMap;
	}

}
