package com.cyz.ob.common.configuration.security;


import javax.annotation.PostConstruct;
import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import com.cyz.basic.config.security.WebSecurityConfig;
import com.cyz.basic.config.security.config.annotation.web.builders.HttpSecurity;
import com.cyz.basic.config.security.config.annotation.web.configuration.EnableWebSecurity;
import com.cyz.basic.config.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import com.cyz.basic.config.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.cyz.ob.authority.service.AuthoritiesService;
import com.cyz.ob.common.filter.WebMessageAuthenticationFilter;
import com.cyz.ob.ouser.service.impl.OuserService;
import com.cyz.ob.ouser.service.impl.WebMessageService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfig{
	
/*	@Autowired
	private AuthorityService authorityService;
	*/
    /*@Autowired
	private RedisTemplate<String, Object> redisTemplate;*/
	
	@Autowired
	private WebMessageService webMessageService;
	
	@PostConstruct
	private void initProperties() {

	}
	
	@Bean
	public DefaultFilterInvocationSecurityMetadataSource defaultFilterInvocationSecurityMetadataSource(AuthoritiesService authorityService) {
		
		DefaultFilterInvocationSecurityMetadataSource source= new MySecurityMetadaSource(authorityService);
		
		return source;
	}
	
	@Bean
	public MyProvider myProvider(RedisTemplate<String, Object> redisTemplate, AuthoritiesService authorityService, OuserService userService) {
		return new MyProvider(redisTemplate, new UserDetailServiceImpl(userService, authorityService));
	}

	@Override
	protected void uniqueConfigure(HttpSecurity http) {
		http.addFilterBefore(webMessageAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
	
	private Filter webMessageAuthenticationFilter() {
		WebMessageAuthenticationFilter filter = new WebMessageAuthenticationFilter(webMessageService, getProperties().getLoginUrl());
		return filter;
	}
	
	
	
	
	
	/*@Bean
	public ProviderManager providerManager() {
		List<AuthenticationProvider> list = new ArrayList<>();
		list.add(myProvider());
		ProviderManager pm = new ProviderManager(list);		
		return pm;
	}*/
	
	//@ConditionalOnMissingBean(UserDetailsService.class)
	/*@Bean(name="userDetailsService")
	public UserDetailServiceImpl userDetailsService() {
		return new UserDetailServiceImpl();
	}*/
	
	/*@Bean
	public MyProvider myProvider() {
		
		MyProvider provider =null;
		
		return provider;
	}*/

}
