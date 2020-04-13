package com.cyz.ob.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;

import com.cyz.ob.ouser.service.impl.WebMessageService;

public class WebMessageAuthenticationFilter implements Filter{
	
	private final WebMessageService service;
	
	public WebMessageAuthenticationFilter(WebMessageService service) {
		Assert.notNull(service, "webMessageService can't null");
        this.service = service;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
		
	}

}
