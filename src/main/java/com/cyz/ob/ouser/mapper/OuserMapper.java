package com.cyz.ob.ouser.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cyz.basic.mapper.BasicMapper;
import com.cyz.ob.ouser.pojo.entity.Ouser;

@Mapper
public interface OuserMapper extends BasicMapper<Ouser>{
	
	List<Ouser> getList();

}
