package com.cyz.ob.authority.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cyz.basic.mapper.BasicMapper;
import com.cyz.ob.authority.pojo.Roles;

@Mapper
public interface RolesMapper extends BasicMapper<Roles> {
	
	/**
	 * 更新用户和角色的关联，增加角色则插入，移除角色则更新delflag
	 * @param params
	 */
	void addRolesToUser(List<Map<String, Object>> params);
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	List<Roles> getRolesWithAuth(Map<String, Object> params);

}
