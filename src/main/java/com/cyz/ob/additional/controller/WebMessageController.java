package com.cyz.ob.additional.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyz.basic.controller.BasicController;
import com.cyz.basic.pojo.ResponseResult;
import com.cyz.ob.additional.service.WebMessageService;
import com.cyz.ob.ouser.pojo.entity.WebMessage;

@RequestMapping("/api/webMessage")
@RestController
public class WebMessageController extends BasicController {
	
	@Autowired
	private WebMessageService webMessageService;

	@GetMapping("/weblist.do")
	public ResponseResult<List<WebMessage>> webList(
		HttpServletRequest request) {
		ResponseResult<List<WebMessage>> result = new ResponseResult<>();
		WebMessage web = new WebMessage();
		List<WebMessage> webs = webMessageService.getList(web);
		return result.success(webs);
	}

}


