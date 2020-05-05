package com.cyz.ob.article.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyz.basic.controller.BasicController;
import com.cyz.basic.pojo.ResponseResult;
import com.cyz.ob.article.pojo.entity.Label;
import com.cyz.ob.article.pojo.form.LabelForm;
import com.cyz.ob.article.service.LabelService;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.ouser.service.impl.OuserService;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping(value = "/api/article/label")
public class LabelController extends BasicController {
	
	@Autowired
	private OuserService ouserService;
	
	@Autowired
	private LabelService labelService;
	
	@GetMapping("/page.re")
	public ResponseResult<PageInfo<Label>> page(HttpServletRequest request,
			PageEntity<Label> pageParams,
			LabelForm label) {
		ResponseResult<PageInfo<Label>> response = new ResponseResult<>();
		System.out.println(label.getTargetTypes());
		label.setCreator(ouserService.currentUserId(request));
		pageParams.setParams(label);
		PageInfo<Label> page = labelService.getPage(pageParams);
		
		return response.success(page);
	}
	
	@GetMapping(value = "/frequently-used.re")
    public ResponseResult<Set<String>> frequentlyUsedList(
    		HttpServletRequest request, @RequestParam(value="type", required = false) Byte type) {
        ResponseResult<Set<String>> response = new ResponseResult<>();
        
        Label label = new Label();
        label.setCreator(ouserService.currentUserId(request));
        label.setTargetType(type);
        Set<String> names = labelService.frequentlyUsedLabelNames(label);
        
        return response.success(names);
    }

}
