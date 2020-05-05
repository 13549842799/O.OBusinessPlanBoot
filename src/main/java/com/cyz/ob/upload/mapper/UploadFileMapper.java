package com.cyz.ob.upload.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cyz.basic.mapper.BasicMapper;
import com.cyz.ob.upload.pojo.UploadFile;

@Mapper
public interface UploadFileMapper extends BasicMapper<UploadFile> {
	
	@Delete("delete from upload where id in (${ids}) and creator = #{creator}")
	int deleteBatch(@Param("ids")String ids, @Param("creator") Integer creator);
	
	@Select("select path from upload where id in (${ids}) and creator = #{creator}")
	String[] getFilesPath(@Param("ids")String ids, @Param("creator")Integer creator);

}
