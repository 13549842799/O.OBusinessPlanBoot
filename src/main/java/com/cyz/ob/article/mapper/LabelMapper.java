package com.cyz.ob.article.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cyz.basic.mapper.BasicMapper;
import com.cyz.ob.article.pojo.entity.Label;

@Mapper
public interface LabelMapper extends BasicMapper<Label> {
	
	/**
	 * 批量更新标签
	 * @param list
	 */
	void insertOrUpdate(List<Label> list);
	
	/**
	 * 查询频繁被使用的标签的名称
	 * @param creator
	 * @param targetType
	 * @return
	 */
	Set<String> frequentlyUsedLabelNames(@Param("creator")Integer creator, @Param("targetType")Byte targetType, @Param("delflag")byte delfalg, @Param("count")int count);
	
	@Select("select id, name, delflag from label where targetId = #{targetId} AND targetType = #{targetType} AND delflag = #{delflag}")
	List<Label> labels(Label label);

}
