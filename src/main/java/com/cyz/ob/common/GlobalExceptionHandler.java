package com.cyz.ob.common;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyz.basic.pojo.ResponseResult;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value=Exception.class)
	public ResponseResult<Object> accidengExceptionHandler(HttpServletRequest request, Exception le) {
		le.printStackTrace();
		
		return new ResponseResult<>().fail("网络延迟，请及时联系管理员");
	}

}
