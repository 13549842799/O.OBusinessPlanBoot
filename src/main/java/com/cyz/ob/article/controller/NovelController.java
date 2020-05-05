package com.cyz.ob.article.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyz.basic.controller.BasicController;
import com.cyz.basic.pojo.ResponseResult;
import com.cyz.basic.util.StrUtil;
import com.cyz.ob.article.pojo.entity.Novel;
import com.cyz.ob.article.pojo.entity.Portion;
import com.cyz.ob.article.pojo.form.LabelForm;
import com.cyz.ob.article.pojo.form.NovelForm;
import com.cyz.ob.article.service.LabelService;
import com.cyz.ob.article.service.NovelService;
import com.cyz.ob.article.service.PortionService;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.common.constant.ArticleConstant.ArticleType;
import com.cyz.ob.common.constant.ResultConstant;
import com.cyz.ob.ouser.service.impl.OuserService;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping(value = "/api/article/novel")
public class NovelController extends BasicController {
	
	@Autowired
	private OuserService ouserService;
	
	@Autowired
	private NovelService novelService;
	
	@Autowired
	private PortionService portionService;
	
	@Autowired
	private LabelService labelService;
	
	@PostMapping(value = "/save.do")
    public ResponseResult<Novel> createNovel2(HttpServletRequest request,
    		@RequestBody NovelForm novel) {
        ResponseResult<Novel> response = new ResponseResult<>();
        if (StrUtil.isEmpty(novel.getTitle())) {
        	return response.fail("请输入标题");
        }
        
        if (StrUtil.isNotEmpty(novel.getContent()) && novel.getContent().length() > 600) {
        	return response.fail("简介过长");
        }

        Integer userId = ouserService.currentUserId(request);
        if (novel.getId() == null) {
        	novel.create(userId);
        	novel.setNovelState(Novel.UNSTART);
        	novelService.add(novel, Integer.class);
        	//添加小说相关分管
        	Portion worksInfo = new Portion();
        	worksInfo.setTitle("作品相关");
        	worksInfo.create(userId);
        	worksInfo.setNovelId(novel.getId());
        	worksInfo.setNumber(0d);
        	worksInfo.setType(Portion.WORKSINFO);
        	portionService.add(worksInfo);
        } else {
        	novel.update(userId);
        	if (novelService.update(novel) != 1) {
        		return response.fail("更新失败");
        	}
        }

        //批量保存标签
        labelService.batchAddOrUpdate(novel.getLabelList(), novel, ArticleType.NOVEL);      
        Novel novelNew = novelService.get(novel);
        
        return response.success(novelNew);
    }
	
	@DeleteMapping("/{id}/delete.do")
    public ResponseResult<String> deleteNovel(HttpServletRequest request,
    		@PathVariable(name="id") Integer id) {
    	ResponseResult<String> response = new ResponseResult<>();
    	
    	Integer userId = ouserService.currentUserId(request);
    	Novel novel = new Novel(id, userId);
    	novel.update(userId);

    	return response.deleteResult(novelService.delete(novel));
    }
	
	@GetMapping(value = "/page.re")
	public ResponseResult<PageInfo<Novel>> list(
			HttpServletRequest request,
			PageEntity<Novel> pageParam,
			NovelForm form) {
		ResponseResult<PageInfo<Novel>> response = new ResponseResult<>();
		
		form.setCreator(ouserService.currentUserId(request));
		pageParam.setParams(form);
		PageInfo<Novel> page = novelService.getPage(pageParam);
		return response.success(page);
	}
	
	@GetMapping(value = "/{id}/novel.re")
	public ResponseResult<Novel> novel(
			HttpServletRequest request,
			@PathVariable(name="id")Integer id) {
		ResponseResult<Novel> response = new ResponseResult<>();
		
		Integer userId = ouserService.currentUserId(request);
		
		Novel novel = novelService.getById(id);
		if (novel == null || novel.getCreator() != userId) {
			return response.fail(ResultConstant.RESULT_NOT_EXIST);
		}
		LabelForm lp = new LabelForm();
		lp.delflag();
		lp.setTargetType(ArticleType.NOVEL);
		lp.setTargetId(novel.getId());
		novel.setLabelList(labelService.labels(lp));	
		return response.success(novel);
	}
	
	

}
