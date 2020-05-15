package com.cyz.ob.knowledge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyz.basic.controller.BasicController;
import com.cyz.ob.knowledge.service.KnowledgeArticleService;
import com.cyz.ob.ouser.service.impl.OuserService;

@RestController
@RequestMapping("/api/knowledge/article")
public class KnowledgeArticleController extends BasicController {
	
	@Autowired
	private OuserService ouserService;
	
	@Autowired
	private KnowledgeArticleService service;

}
