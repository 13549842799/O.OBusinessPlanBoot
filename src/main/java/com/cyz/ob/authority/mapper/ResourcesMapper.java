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
	@Select("select re.id, re.pid, re.`name`, re.displayName, re.url "
			+ "from resources re left join authorities au on re.authId = au.id left join authorities_ouser au_ou on au.id = au_ou.auth_id"
			+ " left join ouser ou on au_ou.user_id = ou.id where ou.id = #{userId} "
			+ " and re.`type` in (${types}) and re.delflag = #{delflag} and au.delflag = #{delflag} and au_ou.delflag = #{delflag} and re.`state` = #{state} "
			+ " order by re.`type`, re.sort ")
    List<Resources> getModuleResourcesList(@Param("userId")Integer userId,
  		@Param("types")String types,@Param("delflag")byte delflag,
  		@Param("state")byte state);
    
    @Update("update resources set state = IF(state = 0, #{enable}, #{unable}) where id = #{id}")
    int state(@Param("id") int id, @Param("enable") byte enable, @Param("unable")byte unable);

    /**
     * 
     * @param resources
     * @return
     */
	List<Resources> getSimpleList(Resources resources);
}
