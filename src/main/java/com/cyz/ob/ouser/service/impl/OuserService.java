package com.cyz.ob.ouser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cyz.basic.Exception.AddErrorException;
import com.cyz.basic.service.impl.BasicServiceImplTemplate;
import com.cyz.ob.ouser.mapper.OuserMapper;
import com.cyz.ob.ouser.pojo.entity.Ouser;

@Service
public class OuserService extends BasicServiceImplTemplate<Ouser> {
	
	@Autowired
	private OuserMapper mapper;
	
	public Ouser getByUsername(String username) {
		Ouser user = new Ouser();
		user.setUsername(username);
		return get(user);
	}
	
	@Cacheable(value="testList2", key="#root.methodName")
	public List<Ouser> test() {
		//PageHelper.startPage(1, 10);
		List<Ouser> list = mapper.getList();
		//PageInfo<Ouser> p = new PageInfo<>(list);
		return list;
	}

	@Override
	public void add(Ouser t) throws AddErrorException {
		
	}

	@Override
	public Ouser newEntity() {
		return new Ouser();
	}


}
