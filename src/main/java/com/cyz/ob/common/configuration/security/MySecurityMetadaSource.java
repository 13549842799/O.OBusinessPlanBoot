package com.cyz.ob.common.configuration.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import com.cyz.basic.config.security.access.ConfigAttribute;
import com.cyz.basic.config.security.access.SecurityConfig;
import com.cyz.basic.config.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import com.cyz.basic.config.security.web.util.matcher.AntPathRequestMatcher;
import com.cyz.basic.config.security.web.util.matcher.RequestMatcher;
import com.cyz.ob.authority.pojo.Authorities;
import com.cyz.ob.authority.pojo.Resources;
import com.cyz.ob.authority.service.AuthoritiesService;

public class MySecurityMetadaSource extends DefaultFilterInvocationSecurityMetadataSource {
	
	public MySecurityMetadaSource(AuthoritiesService authorityService) {
		super(createMap(authorityService));
	}

	private static LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> createMap(AuthoritiesService authorityService) {
		
		List<Authorities> auths = authorityService.getAllAuthoritiesWithResources();

		LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
		
		for (Authorities auth : auths) {
			if (auth.getResources() == null || auth.getResources().size() == 0) {
				continue;
			}
			Set<ConfigAttribute> configs = new HashSet<ConfigAttribute>(auth.getResources().size());
			configs.add(new SecurityConfig(auth.getAuthority()));
			for (Resources r : auth.getResources()) {
				RequestMatcher matcher = new AntPathRequestMatcher(r.getUrl());
				requestMap.put(matcher, configs);
			}
		}
		
		return requestMap;
	}

}
