package com.cyz.ob.ouser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.cyz.ob.basic.service.suport.BaseServiceSuport;
import com.cyz.ob.ouser.mapper.OuserMapper;
import com.cyz.ob.ouser.pojo.entity.Ouser;

@Service
public class OuserService extends BaseServiceSuport<Ouser> {
	
	@Autowired
	private OuserMapper mapper;
	
	/*@Autowired
	private StringRedisTemplate stringRedisTmplate;*/
	
	@Cacheable(value="testList2", key="#root.methodName")
	public List<Ouser> test() {
		//PageHelper.startPage(1, 10);
		List<Ouser> list = mapper.getList();
		//PageInfo<Ouser> p = new PageInfo<>(list);
		return list;
	}

}
