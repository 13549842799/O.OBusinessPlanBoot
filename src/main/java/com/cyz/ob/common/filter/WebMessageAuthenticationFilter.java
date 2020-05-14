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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import com.cyz.basic.config.security.web.util.matcher.AntPathRequestMatcher;
import com.cyz.basic.config.security.web.util.matcher.RequestMatcher;
import com.cyz.basic.util.HttpUtil;
import com.cyz.basic.util.HttpUtil.RespParams;
import com.cyz.ob.additional.service.WebMessageService;
import com.cyz.ob.ouser.pojo.entity.WebMessage;

public class WebMessageAuthenticationFilter implements Filter{
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private final WebMessageService service;
	
	private final RequestMatcher requestMatcher;
	
	
	public WebMessageAuthenticationFilter(WebMessageService service, String requestUrl) {
		Assert.notNull(service, "webMessageService can't null");
        this.service = service;
        this.requestMatcher = new AntPathRequestMatcher(requestUrl);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		logger.info(" begin run the WebMessageAuthenticationFilter ");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if (!requiresAuthentication(request, response) || request.getMethod().equals("OPTIONS")) {
			chain.doFilter(request, response);
			return;
		}
		WebMessage web = service.getByCode(request.getParameter("code"));
		if (web == null) {
			logger.info(" it must neet the code when you check then WebMessage ");
			HttpUtil.responseResult(RespParams.create(request, response).WebMessageError());
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		logger.info("init the WebMessageAuthenticationFilter");
	}
	
	
	protected boolean requiresAuthentication(HttpServletRequest request,
			HttpServletResponse response) {
		return requestMatcher.matches(request);
	}

}
