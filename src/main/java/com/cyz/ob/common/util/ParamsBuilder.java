package com.cyz.ob.common.util;

import java.util.HashMap;

public class ParamsBuilder {
	
	private HashMap<String, Object> params = new HashMap<>();
	
	private ParamsBuilder() {}
	
	public static ParamsBuilder create() {
		return new ParamsBuilder();
	}
	
	public HashMap<String, Object> build() {
		return this.params;
	}
	
	public ParamsBuilder roleId(Integer roleId) {
		params.put("roleId", roleId);
		return this;
	}
	
	public ParamsBuilder authId(Integer authId) {
		params.put("authId", authId);
		return this;
	}
	
	public ParamsBuilder userId(Integer userId) {
		params.put("userId", userId);
		return this;
	}
	
	public ParamsBuilder delflag(byte delflag) {
		params.put("delflag", delflag);
		return this;
	}
	
	public ParamsBuilder ids(String ids) {
		params.put("ids", ids);
		return this;
	}

}
