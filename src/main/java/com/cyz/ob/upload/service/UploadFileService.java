package com.cyz.ob.upload.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyz.basic.service.impl.BasicServiceImplTemplate;
import com.cyz.basic.util.StrUtil;
import com.cyz.basic.util.UpLoadUtil;
import com.cyz.ob.upload.mapper.UploadFileMapper;
import com.cyz.ob.upload.pojo.UploadFile;

@Service
public class UploadFileService extends BasicServiceImplTemplate<UploadFile, Long> {
	
	@Autowired
	private UploadFileMapper mapper;
	
	@Autowired
	private UpLoadUtil util;

	@Override
	public UploadFile newEntity() {
		return new UploadFile();
	}
	
	public int deleteBatch(String ids, int creator) {
		if (StrUtil.isEmpty(ids)) {
			return 0;
		}
		int count = mapper.deleteBatch(ids, creator);
		String[] paths = mapper.getFilesPath(ids, creator);
		for (int i = 0; i< paths.length; i++) {
			util.deleteFile(paths[i]);
		}
		return count;
	}
	
	public byte[] getFile(long id) throws IOException {
		
		UploadFile file = getById(id);
		
		return util.getFileByte(file.getPath());
	}

}
