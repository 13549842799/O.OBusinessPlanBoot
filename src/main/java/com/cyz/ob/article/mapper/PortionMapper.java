package com.cyz.ob.article.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cyz.basic.mapper.BasicMapper;
import com.cyz.ob.article.pojo.entity.Portion;

@Mapper
public interface PortionMapper extends BasicMapper<Portion> {
	
	List<Portion> getPortionListSections(Portion portion);
    
	@Select("select IFNULL(max(number), 0.0) from portion where novelId = #{novelId}")
	Double getLaststNumer(Integer novelId);

}
