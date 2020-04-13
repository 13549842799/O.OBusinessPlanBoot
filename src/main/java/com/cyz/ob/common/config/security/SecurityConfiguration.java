package com.cyz.ob.common.config.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import com.cyz.basic.config.security.SecurityProperties;
import com.cyz.basic.config.security.WebSecurityConfig;
import com.cyz.basic.config.security.authentication.AuthenticationProvider;
import com.cyz.basic.config.security.authentication.ProviderManager;
import com.cyz.basic.config.security.config.annotation.web.builders.HttpSecurity;
import com.cyz.basic.config.security.config.annotation.web.configuration.EnableWebSecurity;
import com.cyz.basic.config.security.web.access.ExceptionTranslationFilter;
import com.cyz.basic.config.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import com.cyz.basic.config.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.cyz.ob.authority.service.AuthorityService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfig{
	
/*	@Autowired
	private AuthorityService authorityService;
	*/
    /*@Autowired
	private RedisTemplate<String, Object> redisTemplate;*/
	
	@PostConstruct
	private void initProperties() {

	}


	protected void additionalConfigure(HttpSecurity http) {
		
	}
	
	@Bean
	public DefaultFilterInvocationSecurityMetadataSource defaultFilterInvocationSecurityMetadataSource(AuthorityService authorityService) {
		
		DefaultFilterInvocationSecurityMetadataSource source= new MySecurityMetadaSource(authorityService);
		
		return source;
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
	
	
	
	public List<Filter> addFilters() {
		List<Filter> filters = new ArrayList<>();
		filters.add(new TestFilter());
		return filters;
	}

}
