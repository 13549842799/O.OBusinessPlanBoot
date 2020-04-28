package com.cyz.ob.article.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyz.basic.controller.BasicController;
import com.cyz.basic.pojo.ResponseResult;
import com.cyz.ob.article.pojo.entity.Classify;
import com.cyz.ob.article.service.ClassifyService;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.ouser.service.impl.OuserService;
import com.github.pagehelper.PageInfo;

/**
 * 文章分类的控制器
 * 专门管理系统的所有文章模块的分类。
 * 在这个模块下还有用户自身的权限模块和管理员模块
 * 即用户创建自定义分类和管理员创建的系统分类
 * @author cyz
 *
 */
@RequestMapping("/api/article/classify")
@RestController
public class ClassifyController extends BasicController {
	
	@Autowired
	private ClassifyService clsService;
	@Autowired
	private OuserService ouserService;
	
	/**
	 * 用户获取自身的对应的分类分页列表
	 * @param request
	 * @param type
	 * @param childType
	 * @return
	 */
	@GetMapping("/page.re")
	public ResponseResult<PageInfo<Classify>> classifyPage(
			HttpServletRequest request,
			PageEntity<Classify> pageParam,
			Classify cls) {
		
		ResponseResult<PageInfo<Classify>> response = new ResponseResult<>();
		
		pageParam.setParams(cls);
		PageInfo<Classify> page = clsService.getPage(pageParam);
	
		return response.success(page);
	}
	
	@GetMapping("/simples.re")
	public ResponseResult<List<Classify>> classifyList(HttpServletRequest request,Classify cls) {
		ResponseResult<List<Classify>> response = new ResponseResult<>();
		
		cls.setCreator(ouserService.currentUserId(request));
		List<Classify> list = clsService.getSimpleList(cls);
		
		return response.success(list);
	}

}
