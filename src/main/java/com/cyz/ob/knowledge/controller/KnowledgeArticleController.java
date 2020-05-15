package com.cyz.ob.knowledge.controller;

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
import com.cyz.ob.article.pojo.entity.Label;
import com.cyz.ob.article.pojo.form.LabelForm;
import com.cyz.ob.article.service.LabelService;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.common.constant.ArticleConstant.ArticleType;
import com.cyz.ob.knowledge.pojo.KnowledgeArticle;
import com.cyz.ob.knowledge.service.KnowledgeArticleService;
import com.cyz.ob.ouser.service.impl.OuserService;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/api/knowledge/article")
public class KnowledgeArticleController extends BasicController {
	
	@Autowired
	private OuserService ouserService;
	
	@Autowired
	private KnowledgeArticleService articleService;
	
	@Autowired
	private LabelService labelService;
	
	@PostMapping("/save.do")
	public ResponseResult<KnowledgeArticle> save(HttpServletRequest request,
		@RequestBody(required=true)KnowledgeArticle article) {
		ResponseResult<KnowledgeArticle> response = new ResponseResult<>();
		
		if (StrUtil.isEmpty(article.getTitle())) {
			return response.fail("请选择标题");
		}
		
		Integer userId = ouserService.currentUserId(request);
		article.update(userId);
		
		if (article.getId() == null) {
			article.create(userId);
			articleService.add(article, Integer.class);
		} else {
			articleService.updateFull(article);
		}
		
		labelService.batchAddOrUpdate(article.getLabelList(), article, ArticleType.KNOWLEDGE);
		Label lp = new Label();
		lp.delflag();
		lp.setTargetId(article.getId());
		lp.setTargetType(ArticleType.KNOWLEDGE);
		article.setLabelList(labelService.labels(lp));

		return response.success(article);
	}
	
	@DeleteMapping("/{id}/delete.do")
	public ResponseResult<KnowledgeArticle> deleteDiary(HttpServletRequest request,
			@PathVariable(name="id") Integer id) {
		
		ResponseResult<KnowledgeArticle> response = new ResponseResult<>();
		Integer userId = ouserService.currentUserId(request);
		KnowledgeArticle article = new KnowledgeArticle(id, userId);
		article.update(userId);

		return response.deleteResult(articleService.delete(article));
		
	}
	
	@GetMapping("/page.re")
	public ResponseResult<PageInfo<KnowledgeArticle>> page(
			HttpServletRequest request,
			PageEntity<KnowledgeArticle> pageParam, 
			KnowledgeArticle form) {
		
		ResponseResult<PageInfo<KnowledgeArticle>> result = new ResponseResult<>();
		form.setCreator(ouserService.currentUserId(request));
		pageParam.setParams(form);
		PageInfo<KnowledgeArticle> page = articleService.getPage(pageParam);
		
		return result.success(page);
	}
	
	@GetMapping("/{id}/article.re")
	public ResponseResult<KnowledgeArticle>  findDiary(
			HttpServletRequest request,
			@PathVariable(value="id")int id) {
		ResponseResult<KnowledgeArticle> response = new ResponseResult<>();
		
		KnowledgeArticle entity = new KnowledgeArticle(id);
		entity.setCreator(ouserService.currentUserId(request));
		entity = articleService.get(entity);
		if (entity == null) {
			return response.fail("找不到目标日记");
		}
		LabelForm param = new LabelForm();
		param.delflag();
		param.setTargetId(entity.getId());
		param.setTargetType(ArticleType.KNOWLEDGE);
		entity.setLabelList(labelService.labels(param));
		return response.success(entity);
	}

}
