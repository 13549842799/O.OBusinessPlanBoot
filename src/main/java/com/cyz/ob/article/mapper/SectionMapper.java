package com.cyz.ob.article.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.cyz.basic.mapper.BasicMapper;
import com.cyz.ob.article.pojo.entity.Section;

@Mapper
public interface SectionMapper extends BasicMapper<Section> {
	
	/**
	 * 获取当前章节的上一章节
	 * 存在一下情形:
	 * 1.没有上一章：  
	 *     当前章节为第一卷第一章
	 * 2.有上一章：
	 *     上一章为同分卷
	 *     上一章为上一个分卷的最后一章
	 * @param current
	 * @return
	 */
	Long lastSectionId(Section current);
	
	/**
	 * 获取当前章节的下一章节
	 * 存在以下情形：
	 * 1.没有下一章节
	 * 2。下一章节为下一分卷的第一章
	 * @param current
	 * @return
	 */
	Long nextSectionId(Section current);

}
