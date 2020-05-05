package com.cyz.ob.article.controller;

import java.sql.Timestamp;
import java.util.Date;
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

import com.cyz.basic.controller.BasicController;
import com.cyz.basic.pojo.ResponseResult;
import com.cyz.ob.article.pojo.entity.Portion;
import com.cyz.ob.article.service.PortionService;
import com.cyz.ob.ouser.service.impl.OuserService;

@RestController
@RequestMapping(value = "/api/article/portion")
public class PortionController extends BasicController {
	
	@Autowired
	private OuserService ouserService;
	
	@Autowired
	private PortionService portionService;
	
	@PostMapping(value = "/save.do")
    public ResponseResult<Portion> savePortion(HttpServletRequest request,
    		@RequestBody Portion portion) {
    	
        ResponseResult<Portion> response = new ResponseResult<>();
        if (portion.getNovelId() == null) {
        	return response.fail("请选择小说");
        }
        if (portion.judgeWorksInfo()) {
        	return response.fail("参数错误");
        }
        
        Integer userId = ouserService.currentUserId(request);
        
        portion.delflag();
        portion.update(userId);
        if (portion.getId() == null) {
        	portion.setWordsNum(0);
        	portion.setSectionNum(0);
        	portion.create(userId);
        	portion.setNumber(portionService.getNewPortionNumber(portion.getNovelId()));
        	portionService.add(portion, Integer.class);
        	return response.success(portionService.getById(portion.getId()));
        } else {
        	return response.updateResult(portionService.update(portion));
        }
    }
	
	@DeleteMapping(value = "/{id}/delete.do")
    public ResponseResult<Portion> deletePortion(HttpServletRequest request,
    		@PathVariable(name="id")Integer id) {
    	
        ResponseResult<Portion> response = new ResponseResult<>();
        
        if (id == null) {
        	return response.fail("请选择小说");
        }
        Integer user = ouserService.currentUserId(request);
        Portion portion = portionService.getById(id);
        if (portion == null || portion.getCreator() != user) {
        	return response.fail("不存在小说");
        }
        if (portion.judgeWorksInfo()) {
        	return response.fail("参数错误");
        }
             
        portion.update(user);
        
        return response.deleteResult(portionService.delete(portion));
    }
	
	@GetMapping(value = "/{novel}/list.re")
    public ResponseResult<List<Portion>> list(HttpServletRequest request,
    		@PathVariable(name="novel") Integer novelId,
    		@RequestParam(name="portion", required = false)Integer id,
    		@RequestParam(name="expand", required = false, defaultValue = "0")int  expand) {
    	
        ResponseResult<List<Portion>> response = new ResponseResult<>();
        Portion param = new Portion();
        param.setId(id);
        param.setCreator(ouserService.currentUserId(request));
        param.setNovelId(novelId);
        param.delflag();;
        List<Portion> novelPortions = expand == 1 ? portionService.getExpandList(param) : portionService.getList(param);
        
        return response.success(novelPortions);
    }
	
	@GetMapping(value = "/{id}/portion.re")
    public ResponseResult<Portion> portion(HttpServletRequest request,
    		@PathVariable(name="id") Integer id) {
    	
        ResponseResult<Portion> response = new ResponseResult<>();
        Portion portion = portionService.getById(id);

        return response.success(portion);
    }

}
