package com.cyz.ob.ouser.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyz.basic.pojo.ResponseResult;
import com.cyz.ob.basic.controller.BaseController;
import com.cyz.ob.ouser.service.impl.OuserService;

/**
 * 用户模块接口控制类
 * @author cyz
 *
 */
@RestController
@RequestMapping("/api/admin")
public class OuserController extends BaseController{
	
	@Autowired
	private OuserService service;
	
	@GetMapping("/test")
	public ResponseResult<Object> test(HttpServletRequest request) {
		ResponseResult<Object> result = new ResponseResult<>();

		return result.success();
	}
	
	
	

}
