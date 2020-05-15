package com.cyz.ob.upload.controller;

import java.io.File;
import java.io.IOException;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cyz.basic.controller.BasicController;
import com.cyz.basic.pojo.MethodResult;
import com.cyz.basic.pojo.ResponseResult;
import com.cyz.basic.util.QrCodeUtil;
import com.cyz.basic.util.UpLoadUtil;
import com.cyz.ob.ouser.pojo.entity.Ouser;
import com.cyz.ob.ouser.service.impl.OuserService;
import com.cyz.ob.upload.pojo.UploadFile;
import com.cyz.ob.upload.service.UploadFileService;

@RestController
@RequestMapping(value = "/api/upload/uploadFile")
public class UploadFileController extends BasicController {
	
	@Autowired
	private OuserService ouserService;
	
	@Autowired
	private UploadFileService upLoadService;
	
	@Autowired
	private UpLoadUtil upLoadUtil;
    
    private final String[] models = {"admin", "employee", "novel", "section"};
    private final String[] types = {"img", "img", "img", "img"};
    
    @PostMapping(value = "/upload.do")
    public ResponseResult<UploadFile> uploadFile(HttpServletRequest request,
    		UploadFile upLoadFile) {
    	
        ResponseResult<UploadFile> response = new ResponseResult<>();
     
        Map<String, MultipartFile> fileMap = upLoadUtil.getFile(upLoadFile.getName(), request);
        MultipartFile file = null;
        System.out.println(upLoadFile.getName());
        if (fileMap == null || (file = fileMap.get(upLoadFile.getName())) == null) {
        	return response.fail("请提交文件");
        }
        MethodResult<String> validResult = 
        		upLoadUtil.validFile(file, 120l, types[upLoadFile.getRelevance() - 1]);
        
        if (validResult.fail()) {
        	return response.fail(validResult.getErrorMessage());
        }
        
        Ouser user = ouserService.currentUser(request);
        
        String path = 
        		upLoadUtil.filePersistence(file, File.separator + models[upLoadFile.getRelevance() - 1], upLoadUtil.getRandomName(user.getUsername()));
        System.out.println(path);
        upLoadFile.setPath(path);
        //upLoadFile.setFileSize(file.getSize());
        upLoadFile.setTheType();
        upLoadFile.create(user.getId());
        upLoadService.add(upLoadFile, Long.class);
        //redis
        
        return response.success(upLoadFile);
    }
    
    @GetMapping(value="/{id}/file.re", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[]  getFile(HttpServletRequest request, HttpServletResponse response, @PathVariable(name="id")Long id ) throws IOException {   	
    	
        byte[] bytes = upLoadService.getFile(id);

        return bytes;

    }

}
