package com.cyz.ob.authority.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cyz.basic.mapper.BasicMapper;
import com.cyz.ob.authority.pojo.Resources;

@Mapper
public interface ResourcesMapper extends BasicMapper<Resources> {

	/**
	 * 获取admin可访问的资源
	 * @param userId
	 * @param username
	 * @param type
	 * @param delflag
	 * @param state
	 * @return
	 */
	@Select("select re.* from resources re left join authorities au on re.auId = au.id left join authority_ouser au_ou on au.id =  where ")
    List<Resources> getModuleResourcesList(@Param("userId")Integer userId,
  		@Param("username")String username,@Param("types")String types,@Param("delflag")byte delflag,
  		@Param("state")byte state);
    
    @Update("update resources set state = IF(state = 0, #{enable}, #{unable}) where id = #{id}")
    int state(@Param("id") int id, @Param("enable") byte enable, @Param("unable")byte unable);
}
