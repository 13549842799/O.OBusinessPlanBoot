package com.cyz.ob.basic.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyz.ob.Application;
import com.cyz.ob.ouser.pojo.entity.Ouser;
import com.cyz.ob.ouser.service.impl.OuserService;
import com.github.pagehelper.PageInfo;

@RestController
public class BaseController {
	
	@Autowired
	private OuserService service;
	
	private final static Logger logger = LoggerFactory.getLogger(Application.class);
	
	@GetMapping("/test")
	public List<Ouser> test() {
		//PageInfo<Ouser> p = service.test();
		List<Ouser> u = service.test();
		u.forEach(e->System.out.println(e.getCreateTime().getDayOfMonth()));
		return u;
	}


}
