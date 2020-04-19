package com.cyz.ob.authority.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cyz.basic.mapper.BasicMapper;
import com.cyz.ob.authority.pojo.Authorities;

@Mapper
public interface AuthoritiesMapper extends BasicMapper<Authorities> {
	/**
	 * 
	 * @param auths
	 */
	void addAuthsToUser(List<Map<String, Object>> auths);
	
	/**
	 * 
	 * @param auths
	 */
	void addAuthsToRoles(List<Map<String, Object>> auths);
    
	/**
	 * 获取携带资源的权限列表
	 * @param delflag
	 * @param state
	 * @return
	 */
	List<Authorities> getAllAuthoritiesWithResources(@Param("delflag")byte delflag, @Param("state")byte state);
	
	/**
	 * 获取当前未被删除的指定id的权限列表
	 * @param ids
	 * @param delfalg
	 * @return
	 */
	@Select("select id, delflag from authorities where id in (${ids) and delflag = #{delflag}")
	List<Authorities> searchListById(@Param("ids") String ids, @Param("delflag") byte delfalg);
	
	List<Authorities> getAllAuthoritiesByUser(@Param("userId")Integer userId, @Param("delflag")byte delflag);
    
	List<Authorities> getSimpleAuths(Authorities authorities);

}
