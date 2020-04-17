package com.cyz.ob.basic.service;

import java.util.ArrayList;
import java.util.List;

import com.cyz.basic.util.StrUtil;

public interface IdsCreateService<T> {
	
	/**
	 * 通过以,分割的id字符串集合，遍历它们并实例化，然后以集合的形式返回.
	 * @param ids
	 * @param delflag
	 * @return
	 */
	default List<T> createByParams(String ids, byte delflag) {
		List<T> list = new ArrayList<>();
		if (StrUtil.isEmpty(ids)) {
			return list;
		}
		String[] arr = ids.split(",");
		if (arr == null || arr.length == 0) {
			return list;
		}
		for (String id : arr) {
			list.add(this.entity(id, delflag));
		}
		return list;
	}
	
	T entity(String id, byte delflag);

}
