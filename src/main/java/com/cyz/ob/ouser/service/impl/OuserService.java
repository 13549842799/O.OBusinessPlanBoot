package com.cyz.ob.ouser.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cyz.basic.Exception.AddErrorException;
import com.cyz.basic.config.security.SecurityProperties;
import com.cyz.basic.constant.EntityConstants;
import com.cyz.basic.enumeration.DeleteFlag;
import com.cyz.basic.util.StrUtil;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.basic.service.PageServiceTemplate;
import com.cyz.ob.ouser.mapper.OuserMapper;
import com.cyz.ob.ouser.pojo.entity.Ouser;

@Service
public class OuserService extends PageServiceTemplate<Ouser, PageEntity<Ouser>> {
	
	@Autowired
	private OuserMapper mapper;
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Cacheable(value=EntityConstants.ENTITY.REDIS_OUSER, key="#username")
	public Ouser getByUsername(String username) {
		Ouser user = new Ouser();
		user.setUsername(username);
		return get(user);
	}
	
	/**
	 * 获取无密码的用户信息
	 * @param username
	 * @return
	 */
	public Ouser getSimpleByUsername(String username) {
		Ouser user = getByUsername(username);
		user.setPassword(null);
		return user;
	}

	@Override
	public void add(Ouser t) throws AddErrorException {
		
	}

	@Override
	public Ouser newEntity() {
		return new Ouser();
	}

	/**
	 * 查找数据库，当前昵称是否已存在
	 * @param nikename
	 * @return
	 */
    public boolean checkNikenameExist(String nikename) {
		
		return mapper.validNikeNameExist(nikename,DeleteFlag.VALID.getCode())>0;
	}
    
    /**
     * 获取当前登录的用户的信息
     * @param request
     * @return
     */
    public Ouser currentUser(HttpServletRequest request) {
    	
    	String username = currentUsername(request);
    	
    	if (StrUtil.isEmpty(username)) {
    		throw new IllegalArgumentException("参数异常");
    	}
    	
    	return getSimpleByUsername(username);
    }
    
    /**
     * 获取当前登录的用户名
     * @param request
     * @return
     */
    public String currentUsername(HttpServletRequest request) {
    	return request.getHeader(securityProperties.getHeader().getUser());
    }
    
    public Integer currentUserId(HttpServletRequest request) {
    	return currentUser(request).getId();
    }
    
    /**
	 * 修改密码
	 * @param oldPass 旧密码
	 * @param newPass 新密码
	 * @return  1:正确 -1:两次密码不相同 0-密码不正确
	 */
    public int alterPassword(String oldPass, String newPass) {
		
		//1.判断密码是否相同
		if (!oldPass.equals(newPass)) {
			
			return -1;
		}
		return 1;
	}
    
    
    
    @Override
    @CachePut
	public int update(Ouser t) {
		return super.update(t);
	}

	@Cacheable(value="testList2", key="#root.methodName")
	public List<Ouser> test() {
		//PageHelper.startPage(1, 10);
		List<Ouser> list = mapper.getList();
		//PageInfo<Ouser> p = new PageInfo<>(list);
		return list;
	}

}
