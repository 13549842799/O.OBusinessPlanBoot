package com.cyz.ob.operation.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.cyz.ob.operation.pojo.OperationRecord;

@Mapper
public interface OperationRecordMapper{
	
	void add(OperationRecord record);
}
