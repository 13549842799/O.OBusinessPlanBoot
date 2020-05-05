package com.cyz.ob.article.controller;

import java.time.LocalDateTime;

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
import com.cyz.ob.article.pojo.entity.Diary;
import com.cyz.ob.article.pojo.entity.Label;
import com.cyz.ob.article.pojo.form.DiaryForm;
import com.cyz.ob.article.pojo.form.LabelForm;
import com.cyz.ob.article.service.DiaryService;
import com.cyz.ob.article.service.LabelService;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.common.constant.ArticleConstant.ArticleType;
import com.cyz.ob.ouser.service.impl.OuserService;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/api/article/diary")
public class DiaryController extends BasicController {
	
	@Autowired
	private DiaryService diaryService;
	@Autowired
	private OuserService ouserService;
	@Autowired
	private LabelService labelService;
	
	@PostMapping("/save.do")
	public ResponseResult<Diary> save(HttpServletRequest request,
		@RequestBody(required=true)Diary diary) {
		ResponseResult<Diary> response = new ResponseResult<>();
		
		String errorMessage = validUtil.validReturnFirstError(diary);
		if (errorMessage != null) {
			return response.fail(errorMessage);
		}
		Integer userId = ouserService.currentUserId(request);
		diary.update(userId);
		
		if (diary.getId() == null) {
			diary.setCreator(userId);
			diary.setCreateTime(LocalDateTime.now());
			diaryService.add(diary, Integer.class);
		} else {
			diaryService.updateFull(diary);
		}
		
		labelService.batchAddOrUpdate(diary.getLabelList(), diary, ArticleType.DIARY);
		Label lp = new Label();
		lp.delflag();
		lp.setTargetId(diary.getId());
		lp.setTargetType(ArticleType.DIARY);
		diary.setLabelList(labelService.labels(lp));

		return response.success(diary);
	}
	
	@DeleteMapping("/{id}/delete.do")
	public ResponseResult<Diary> deleteDiary(HttpServletRequest request,
			@PathVariable(name="id") Integer id) {
		
		ResponseResult<Diary> response = new ResponseResult<>();
		Integer userId = ouserService.currentUserId(request);
		Diary diary = new Diary(id, userId);
		diary.update(userId);

		return response.deleteResult(diaryService.delete(diary));
		
	}
	
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
	
	@GetMapping("/{id}/diary.re")
	public ResponseResult<Diary>  findDiary(
			HttpServletRequest request,
			@PathVariable(value="id")int id) {
		ResponseResult<Diary> response = new ResponseResult<>();
		
		Diary diary = new Diary(id);
		diary.setCreator(ouserService.currentUserId(request));
		diary = diaryService.get(diary);
		if (diary == null) {
			return response.fail("找不到目标日记");
		}
		LabelForm param = new LabelForm();
		param.delflag();
		param.setTargetId(diary.getId());
		param.setTargetType(ArticleType.DIARY);
		diary.setLabelList(labelService.labels(param));
		return response.success(diary);
	}

}
