package com.cyz.ob.article.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyz.basic.config.security.core.userdetails.User;
import com.cyz.basic.controller.BasicController;
import com.cyz.basic.pojo.ResponseResult;
import com.cyz.basic.util.StrUtil;
import com.cyz.ob.article.pojo.entity.Classify;
import com.cyz.ob.article.pojo.form.ClassifyForm;
import com.cyz.ob.article.service.ClassifyService;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.ouser.pojo.entity.Ouser;
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
	 * 用户创建自定义分类
	 * @param request
	 * @param type
	 * @param childType
	 * @return
	 */
	@PostMapping("/save.do")
	public ResponseResult<Classify> classifyAdd(
			HttpServletRequest request,
			@RequestBody Classify cls) {
		
		ResponseResult<Classify> response = new ResponseResult<>();
		
		Integer userId = ouserService.currentUserId(request);
		
		String mess = validUtil.validReturnFirstError(cls);
		
		if (StrUtil.isNotEmpty(mess)) {
			return response.fail(mess);
		}
		
		Classify old = new Classify();
		old.delflag();
		old.setName(cls.getName());		
		old.setCreator(userId);
		old = clsService.get(old);
		if(old != null && old.getChildType() == cls.getChildType() && (cls.getId() == null || cls.getId() != old.getId())) {
			return response.fail("已经存在同名分类");
		}
		
		if (old != null && old.getCreator() != userId) {
			return response.fail("违规操作");
		}
			
		cls.setType(Classify.CUSTOMCLASSIFY);
		if (cls.getId() == null) {					
			cls.create(userId);
			clsService.add(cls, Integer.class);			
			return response.success(cls);
		} else {
			return response.updateResult(clsService.update(cls));
		}
	}
	
	@DeleteMapping("/{id}/delete.do")
	public ResponseResult<Classify> classifyDelete(HttpServletRequest request,
			@PathVariable("id")int id) {
		ResponseResult<Classify> response = new ResponseResult<>();
		
		Integer userId = ouserService.currentUserId(request);
		
		Classify cls = new Classify();
		cls.setId(id);
		cls.delflag();
		cls.setCreator(userId);
		cls = clsService.get(cls);
		if (cls == null) {
		  return response.fail("不存在此分类");
		}
		
		return response.deleteResult(clsService.delete(cls));
	}
	
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
			ClassifyForm form) {
		
		ResponseResult<PageInfo<Classify>> response = new ResponseResult<>();
		
		Integer userId = ouserService.currentUserId(request);
		form.setCreator(userId);
		pageParam.setParams(form);
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
