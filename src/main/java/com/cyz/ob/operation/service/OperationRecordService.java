package com.cyz.ob.operation.service;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.cyz.basic.enumeration.DeleteFlag;
import com.cyz.basic.pojo.DeleteAbleEntity;
import com.cyz.basic.pojo.IdEntity;
import com.cyz.basic.util.HttpUtil;
import com.cyz.ob.operation.mapper.OperationRecordMapper;
import com.cyz.ob.operation.pojo.OperationRecord;

@Service
@EnableAsync
public class OperationRecordService {
	
	@Autowired
	private OperationRecordMapper mapper;
	
	@Async
	public void saveRecordAsync(HttpServletRequest request, String username, IdEntity<?> obj, String type) {
		try {
			OperationRecord record = new OperationRecord();
			record.setIpAddress(HttpUtil.getIpAddr(request));
			record.setRequestPath(request.getRequestURI());
			record.setOperationTime(LocalDateTime.now());
			record.setObjectClass(obj.getClass().getSimpleName());
			record.setObjectId(obj.getId().toString());	
			record.setOperationType(type);
			record.setUsername(username);
			
			mapper.add(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
       
	}

}
