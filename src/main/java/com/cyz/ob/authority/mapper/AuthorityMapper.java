package com.cyz.ob.authority.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cyz.basic.mapper.BasicMapper;
import com.cyz.ob.authority.pojo.Authority;

@Mapper
public interface AuthorityMapper extends BasicMapper<Authority> {
    
	List<Authority> getAllAuthoritiesWithResources(@Param("delflag")byte delflag, @Param("state")byte state);

}
