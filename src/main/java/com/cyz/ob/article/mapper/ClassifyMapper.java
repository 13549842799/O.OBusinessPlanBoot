package com.cyz.ob.article.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cyz.basic.mapper.BasicMapper;
import com.cyz.ob.article.pojo.entity.Classify;

@Mapper
public interface ClassifyMapper extends BasicMapper<Classify> {
	
	@Select("select id, `name` from classify where childType = IFNULL(#{childType}, childType) and creator = #{creator} and delflag = #{delflag}")
	List<Classify> getSimpleList(Classify cls);

}
