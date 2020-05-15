package com.cyz.ob.ouser.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cyz.basic.mapper.BasicMapper;
import com.cyz.ob.ouser.pojo.entity.Ouser;

@Mapper
public interface OuserMapper extends BasicMapper<Ouser>{
	
	List<Ouser> getList();
	
	/**
	  * 返回拥有此昵称的账号的数量
	  * @param nikename
	  * @param code
	  * @return
	  */
	@Select("SELECT COUNT(id) FROM ouser WHERE nikename=#{nikename,jdbcType=VARCHAR} AND delflag=#{delflag,jdbcType=TINYINT}")
	int validNikeNameExist(@Param("nikename")String nikename,@Param("delflag")byte delflag);
	
	List<Ouser> getListByMap(Map<String, Object> params);

}
