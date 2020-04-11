package com.cyz.ob.ouser.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyz.basic.config.security.authentication.AuthenticationProvider;
import com.cyz.basic.config.security.authentication.ProviderManager;
import com.cyz.basic.config.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import com.cyz.basic.config.security.core.userdetails.UserDetailsService;
import com.cyz.basic.pojo.ResponseResult;
import com.cyz.ob.basic.controller.BaseController;
import com.cyz.ob.ouser.service.impl.OuserService;

/**
 * 用户模块接口控制类
 * @author cyz
 *
 */
@RestController
@RequestMapping("/api/ouser")
public class OuserController extends BaseController{
	
	@Autowired
	private OuserService service;

	@GetMapping("/test")
	public ResponseResult<Object> test(HttpServletRequest request) {
		ResponseResult<Object> result = new ResponseResult<>();

		return result.success();
	}
	
	
	@GetMapping("/try")
	public ResponseResult<Object> testTry(HttpServletRequest request) {
		ResponseResult<Object> result = new ResponseResult<>();

		return result.success();
	}
	

}
