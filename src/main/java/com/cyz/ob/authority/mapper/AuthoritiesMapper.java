package com.cyz.ob.authority.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cyz.basic.mapper.BasicMapper;
import com.cyz.ob.authority.pojo.Authorities;

@Mapper
public interface AuthoritiesMapper extends BasicMapper<Authorities> {
    
	List<Authorities> getAllAuthoritiesWithResources(@Param("delflag")byte delflag, @Param("state")byte state);

}
