package com.cyz.ob.authority.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyz.basic.enumeration.DeleteFlag;
import com.cyz.basic.enumeration.StatusFlag;

import com.cyz.ob.authority.mapper.AuthoritiesMapper;
import com.cyz.ob.authority.pojo.Authorities;
import com.cyz.ob.authority.pojo.Authorities.AuthoritiesBuilder;
import com.cyz.ob.authority.pojo.Roles;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.basic.service.IdsCreateService;
import com.cyz.ob.basic.service.PageServiceTemplate;
import com.cyz.ob.common.util.ParamsBuilder;
import com.cyz.ob.ouser.pojo.entity.Ouser;

@Service
public class AuthoritiesService extends PageServiceTemplate<Authorities, PageEntity<Authorities>> implements IdsCreateService<Authorities> {
	
	@Autowired
	private AuthoritiesMapper mapper;
	
	/**
	 * 为用户添加新的权限，并移除指定权限
	 * @param auths
	 * @param user
	 */
    public void addAuthsToUser(List<Authorities> auths, Ouser user) {
    	List<Map<String, Object>> authParams = new ArrayList<>();
    	if (auths != null && auths.size() > 0) {
    		auths.forEach(a -> ParamsBuilder.create().authId(a.getId()).userId(user.getId()).roleId(0).build());
    	}
		this.addAuthsToUser(authParams);
	}
    
    /**
     * 为用户添加新的权限，并移除指定权限
     * @param authParams
     */
    public void addAuthsToUser(List<Map<String, Object>> authParams) {
    	if (authParams == null || authParams.size() == 0) {
    		return;
    	}
		mapper.addAuthsToUser(authParams);
	}
    
    /**
     * 为角色更新权限关系（添加权限+移除权限）
     * @param auths
     * @param role
     */
    public void addAuthsToRole(final List<Authorities> auths, final Roles role) {
		
    	final List<Map<String, Object>> params = new ArrayList<>();
    	
    	auths.forEach(a->
    		params.add(ParamsBuilder.create().roleId(role.getId()).authId(a.getId()).delflag(a.getDelflag()).build())
    	);
    	
    	
		
	}
	
	public List<Authorities> searchAuthsById(String ids) {
		return mapper.searchListById(ids, DeleteFlag.VALID.getCode());
	}
	
	public List<Authorities> getAuthoritiesByUserId(int id) {
				
		List<Authorities> auths = mapper.getAllAuthoritiesByUser(id, DeleteFlag.VALID.getCode());
		
		return auths;
	}
	
	public List<Authorities> getAllAuthoritiesWithResources() {
		return mapper.getAllAuthoritiesWithResources(DeleteFlag.VALID.getCode(), StatusFlag.ENABLE.getCode());
	}

	@Override
	public Authorities newEntity() {
		
		return new Authorities();
	}

	@Override
	public Authorities entity(String id, byte delflag) {
		
		return new Authorities(Integer.parseInt(id), delflag);
	}
	
	

}
