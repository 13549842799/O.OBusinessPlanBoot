package com.cyz.ob.article.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyz.basic.controller.BasicController;
import com.cyz.basic.pojo.ResponseResult;
import com.cyz.ob.article.pojo.entity.Diary;
import com.cyz.ob.article.pojo.form.DiaryForm;
import com.cyz.ob.article.service.DiaryService;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.ouser.service.impl.OuserService;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/api/article/diary")
public class DiaryController extends BasicController {
	
	@Autowired
	private DiaryService diaryService;
	@Autowired
	private OuserService ouserService;
	
	@GetMapping("/page.re")
	public ResponseResult<PageInfo<Diary>> page(
			HttpServletRequest request,
			PageEntity<Diary> pageParam, 
			DiaryForm form) {
		
		ResponseResult<PageInfo<Diary>> result = new ResponseResult<>();
		form.setCreator(ouserService.currentUserId(request));
		pageParam.setParams(form);
		PageInfo<Diary> page = diaryService.getPage(pageParam);
		
		return result.success(page);
	}

}
