package com.cyz.ob.article.controller;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import com.cyz.ob.article.pojo.entity.Memo;
import com.cyz.ob.article.service.MemoService;
import com.cyz.ob.ouser.service.impl.OuserService;

@RestController
@RequestMapping(value = "/api/article/memo")
public class MemoController extends BasicController {
	
	@Autowired
	private OuserService ouserService;
	
	@Autowired
	private MemoService momoService;
	
	@PostMapping("/save.do")
	public ResponseResult<Memo> add(
			HttpServletRequest request,
			@RequestBody Memo memo) {
		
		ResponseResult<Memo> response = new ResponseResult<>();
		
		Integer userId = ouserService.currentUserId(request);
		
		if (memo.getId() == null) {					
			memo.create(userId);
			memo.setDate(memo.getDate() != null ? memo.getDate() : LocalDate.now());
			momoService.add(memo, Integer.class);			
			return response.success(memo);
		} else {
			memo.update(userId);
			return response.updateResult(momoService.update(memo));
		}
	}
	
	@DeleteMapping("/{id}/delete.do")
	public ResponseResult<Memo> classifyDelete(HttpServletRequest request,
			@PathVariable("id")int id) {
		ResponseResult<Memo> response = new ResponseResult<>();
		
		Integer userId = ouserService.currentUserId(request);
		
		Memo memo = new Memo();
		memo.setId(id);
		memo.delflag();
		memo.setCreator(userId);
	
		return response.deleteResult(momoService.delete(memo));
	}
	
	@GetMapping("/list.re")
	public ResponseResult<Map<String, List<Memo>>> page(HttpServletRequest request,
			Memo momo) {
		ResponseResult<Map<String, List<Memo>>> response = new ResponseResult<>();
		momo.setCreator(ouserService.currentUserId(request));
		
		Map<String, List<Memo>> map = createMap(momoService.getList(momo));
		
		return response.success(map);
	}
	
	private Map<String, List<Memo>> createMap(List<Memo> list) {
		Map<String, List<Memo>> map = new LinkedHashMap<>();
		
		if (list == null || list.size() == 0) {
			return map;
		}
		
		for (Memo memo : list) {
			String m = StrUtil.formatMonth(memo.getDate());
			List<Memo> ms = map.get(m);
			if (ms == null) {
				map.put(m, (ms = new LinkedList<>()));
			}
			ms.add(memo);
		}
		return map;
	}
	

}
