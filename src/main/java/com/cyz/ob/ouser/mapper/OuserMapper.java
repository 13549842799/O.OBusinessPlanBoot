package com.cyz.ob.ouser.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;

import com.cyz.ob.ouser.pojo.entity.Ouser;

@Mapper
public interface OuserMapper {
	
	List<Ouser> getList();

}
