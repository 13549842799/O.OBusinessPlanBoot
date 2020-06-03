package com.cyz.ob.common.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cyz.basic.pojo.IdEntity;
import com.cyz.basic.web.SpringMvcHolder;
import com.cyz.ob.operation.service.OperationRecordService;
import com.cyz.ob.ouser.service.impl.OuserService;

@Aspect
public class RecordAop {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private OperationRecordService service;
	
	@Autowired
	private OuserService ouserService;
	
	@After("execution(* com.cyz.basic.mapper.BasicMapper.add(..)) && args(entity)")
	public void operationRecordAopAdd(JoinPoint point, IdEntity<?> entity) {
		operationRecordAop(point, entity, "保存");
	}
	
	@After("execution(* com.cyz.basic.mapper.BasicMapper.update*(..)) && args(entity)")
	public void operationRecordAopUpdate(JoinPoint point, IdEntity<?> entity) {
		operationRecordAop(point, entity, "更新");
	}
	
	@After("execution(* com.cyz.basic.mapper.BasicMapper.delete(..)) && args(entity)")
	public void operationRecordAopDelete(JoinPoint point, IdEntity<?> entity) {
		operationRecordAop(point, entity, "删除");
	}
	
	private void operationRecordAop(JoinPoint point, IdEntity<?> entity, String type) {
		if (entity == null) {
			return;
		}
		try {
            HttpServletRequest request = SpringMvcHolder.getRequest();
            
			service.saveRecordAsync(request, ouserService.currentUsername(request), entity,type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("记录执行结束");
	}

}
