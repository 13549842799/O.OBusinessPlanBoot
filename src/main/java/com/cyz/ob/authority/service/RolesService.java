package com.cyz.ob.authority.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyz.basic.enumeration.DeleteFlag;
import com.cyz.ob.authority.mapper.RolesMapper;
import com.cyz.ob.authority.pojo.Authorities;
import com.cyz.ob.authority.pojo.Roles;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.basic.service.IdsCreateService;
import com.cyz.ob.basic.service.PageServiceTemplate;
import com.cyz.ob.common.util.ParamsBuilder;
import com.cyz.ob.ouser.pojo.entity.Ouser;

@Service
public class RolesService extends PageServiceTemplate<Roles, PageEntity<Roles>> implements IdsCreateService<Roles> {
	
	
	
	@Autowired
	private RolesMapper mapper;

	@Autowired
	private AuthoritiesService authoritiesService;
	
	/**
	 * 用户添加角色，通过角色关联添加权限
	 * 关联表是以authid，userid，roleid作为联合主键，其中roleid为0表示该权限为直接添加给用户的，而非通过角色间接添加的。
	 * @param addRoles 增加的角色
	 * @param removeRoles 移除的角色
	 * @param user 目标用户
	 */
	@Transactional
	public void addRoleToUser(List<Roles> addRoles, List<Roles> removeRoles, Ouser user) {
		
		final List<Roles> all = new ArrayList<>();
		all.addAll(addRoles);
		all.addAll(removeRoles);
		
		final List<Map<String, Object>> params = new ArrayList<>();
		all.forEach(a-> 
			params.add(ParamsBuilder.create().roleId(a.getId()).userId(user.getId()).delflag(a.getDelflag()).build())
		);
		//更新用户和角色关系
		mapper.addRolesToUser(params);
				
	    //根据关联角色查询变动的权限
		List<Map<String, Object>> authParams = new ArrayList<>();
		authParams.addAll(createAuthsParams(addRoles, DeleteFlag.VALID.getCode(), user));
		authParams.addAll(createAuthsParams(removeRoles, DeleteFlag.DELETE.getCode(), user));
        
		authoritiesService.addAuthsToUser(authParams);
	}
	
	/**
	 * 创建用于更新权限-用户关联表的参数map集合
	 * @param roles
	 * @param delflag
	 * @param user
	 * @return
	 */
	private List<Map<String, Object>> createAuthsParams(List<Roles> roles, byte delflag, Ouser user) {
		List<Map<String, Object>> ap = new ArrayList<>();
		if (roles.size() == 0) {
			return ap;
		}
		String ids = roles.stream().map(r-> String.valueOf(r.getId())).collect(Collectors.joining(","));
		roles = mapper.getRolesWithAuth(ParamsBuilder.create().ids(ids).delflag(DeleteFlag.VALID.getCode()).build());
		for (Roles role : roles) {
			if (role.getAuthsList() == null || role.getAuthsList().size() == 0) {
				continue;
			}
			List<Authorities> as = role.getAuthsList();
			for(int i = 0, size = as.size(); i < size; i++) {
				ap.add(ParamsBuilder.create()
						.authId(as.get(i).getId()).userId(user.getId()).roleId(role.getId()).delflag(DeleteFlag.VALID.getCode()).build());
			}
		}
		return ap;
	}

	@Override
	public Roles newEntity() {
		
		return new Roles();
	}

	@Override
	public Roles entity(String id, byte delflag) {
		
		return new Roles(Integer.parseInt(id), delflag);
	}

}
