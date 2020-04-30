package com.cyz.ob.article.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cyz.basic.mapper.BasicMapper;
import com.cyz.ob.article.pojo.entity.Classify;

@Mapper
public interface ClassifyMapper extends BasicMapper<Classify> {
	
	@Select("select id, `name` from classify where childType = IFNULL(#{childType}, childType) and creator = #{creator} and delflag = #{delflag}")
	List<Classify> getSimpleList(Classify cls);
	
	/**
	 * 检查目标类型下存在的文章数
	 * @param table 表名
	 * @param id 分类id
	 * @param delflag 删除标志
	 * @return
	 */
	@Select("SELECT COUNT(0) FROM classify cl LEFT JOIN ${table} t ON t.classify=cl.id WHERE t.delflag=#{delflag, jdbcType=TINYINT} AND t.creator = #{creator}")
	int classifyArticleCount(@Param("table")String table,@Param("id") int id,@Param("creator") int creator, @Param("delflag") byte delflag);

	@Update("UPDATE ${table} SET classify = #{newId} WHERE classify=#{oldClassifyId} AND creator = #{creator}")
	void alterArticleClassify(@Param("table")String table,@Param("creator") int creator,@Param("oldClassifyId") int oldClassifyId, @Param("newId") int newClassifyId);
}
