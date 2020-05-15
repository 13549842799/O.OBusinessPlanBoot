package com.cyz.ob.common.constant;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public interface ArticleConstant {
	/**
	 * 文章类型
	 * @author cyz
	 *
	 */
	public static class ArticleType {
		public static final byte DIARY = 1; //日记类型
		
		public static final byte MEMO = 2; //备忘类型
		
		public static final byte INSPIRATION = 3; //灵感类型
		
		public static final byte NOVEL = 4; //小说类型
		
		public static final byte KNOWLEDGE = 5; //技术文章
		
		public static final LinkedHashMap<Byte, String> articleTypeMap = new LinkedHashMap<>(4);
		
		public static final Map<Byte, String> mapperTable = new HashMap<>();
		
		static {
			articleTypeMap.put(DIARY, "日记");
			articleTypeMap.put(MEMO, "备忘");
			articleTypeMap.put(INSPIRATION, "灵感");
			articleTypeMap.put(NOVEL, "小说");
			
			mapperTable.put(DIARY, "diary");
			//mapperTable.put(FinalReport, "finalreport");
			mapperTable.put(NOVEL, "novel");
		}
	}
}
