package com.cyz.ob.basic.service;

import java.util.List;

import com.cyz.basic.service.impl.BasicServiceImplTemplate;
import com.cyz.ob.authority.pojo.Resources;
import com.cyz.ob.basic.entity.PageEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 分页继承类
 * @author cyz
 *
 * @param <T> 原本类的实体
 * @param <P> 分页参数 分页参数可以是本类，也可以是T继承了PageEntity
 */
public abstract class PageServiceTemplate<T, P extends PageEntity<T>> extends BasicServiceImplTemplate<T> {
	
	/**
	 * 获取分页对象
	 * @param pageParam
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageInfo<T> getPage(P pageParam) {
		
		T params = pageParam.getParams();
		
		if (params == null && newEntity().getClass().isAssignableFrom(pageParam.getClass())) {
			T origal = newEntity();
			if (origal.getClass().isAssignableFrom(pageParam.getClass())) {
				params = (T)pageParam;
			} else {
				params = origal;
			}
			
		}
		PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());			
        List<T> list = getList(params);
		PageInfo<T> page = new PageInfo<>(list);		
		return page;
	}

}
