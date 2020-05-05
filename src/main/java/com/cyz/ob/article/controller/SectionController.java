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
import com.cyz.basic.pojo.UseTrigger;
import com.cyz.basic.util.StrUtil;
import com.cyz.ob.article.pojo.entity.Portion;
import com.cyz.ob.article.pojo.entity.Section;
import com.cyz.ob.article.pojo.form.SectionForm;
import com.cyz.ob.article.service.PortionService;
import com.cyz.ob.article.service.SectionService;
import com.cyz.ob.common.constant.ResultConstant;
import com.cyz.ob.ouser.service.impl.OuserService;
import com.cyz.ob.upload.pojo.UploadFile;
import com.cyz.ob.upload.service.UploadFileService;

@RestController
@RequestMapping(value = "/api/article/section")
public class SectionController extends BasicController {
	
	@Autowired
	private OuserService ouserService;
	
	@Autowired
	private PortionService portionService;
	
	@Autowired
	private SectionService sectionService;
	
	@Autowired
	private UploadFileService uploadFileService;
	
	@UseTrigger(name="alterForInsertSection, alterForUpdateSection", effect="修改小说和分卷的字数合计，章节合计，状态和最新章标记")
    @PostMapping(value = "/save.do")
    public ResponseResult<Section> save(HttpServletRequest request,
    		@RequestBody(required = true) SectionForm form) {
        ResponseResult<Section> response = new ResponseResult<>();
        int user = ouserService.currentUserId(request);
        form.setWordsNum(StrUtil.isNotEmpty(form.getContent()) ? form.getContent().length() : 0);
        Section section = form;
        if (form.getId() == null) {       	   
        	form.create(user);
			sectionService.add(form, Long.class);
			
			section = sectionService.getById(form.getId());
			Long lastId = sectionService.lastSectionId(section);
			if (lastId != null) {
				Section last = new Section();
				last.setId(lastId);
				last.setNextSection(section.getId());
				sectionService.update(last);
			}
			Long nextId = sectionService.nextSectionId(section);
			if (nextId != null) {
				Section next = new Section();
				next.setId(nextId);
				next.setLastSection(section.getId());
				sectionService.update(next);
			}
			section = new Section(section.getId());
			section.setLastSection(lastId);
			section.setNextSection(nextId);
		}
        
        section.update(user);
		sectionService.update(section);
		
        if (StrUtil.isNotEmpty(form.getDelImagesId())) {
        	uploadFileService.deleteBatch(form.getDelImagesId(), user);
        }

        return response.success(sectionService.getById(section.getId()));
    }
	
	@UseTrigger(name="alterForDeleteSection")
    @DeleteMapping(value = "/{id}/delete.do")
    public ResponseResult<Section> delSection(HttpServletRequest request,
    		@PathVariable(name="id")Long id) {
        ResponseResult<Section> response = new ResponseResult<>();     
        
        Integer user = ouserService.currentUserId(request);
        
        Section section = new Section();
        section.setId(id);
        section.setCreator(user);
        section.update(user);
        return response.deleteResult(sectionService.delete(section));
    }

	@GetMapping("/{id}/section.re")
	public ResponseResult<Section> section(HttpServletRequest request,
			@PathVariable(name="id")Long id) {
		
		ResponseResult<Section> response = new ResponseResult<>();
		
		Section section = sectionService.getById(id);
		
		if (section == null) {
			return response.fail(ResultConstant.RESULT_NOT_EXIST);
		}
		
		if (section.getPortionId() != null) {
			Portion p = portionService.getById(section.getPortionId());
			section.setPortion(p);
			section.setNovel(p.getNovel());
		}
		UploadFile p = new UploadFile();
		p.setObjId(section.getId());
		p.setRelevance((byte)4);
		section.setFiles(uploadFileService.getList(p));
		
		return response.success(section);
		
	}
}
