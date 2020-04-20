package com.cyz.ob.additional.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cyz.basic.mapper.BasicMapper;
import com.cyz.ob.ouser.pojo.entity.WebMessage;

@Mapper
public interface WebMessageMapper extends BasicMapper<WebMessage> {

	@Select("select * from webmapper where code = #{code}")
	WebMessage getByCode(String code);
	
	@Select("select * from webmapper ")
	List<WebMessage> getList();

}
