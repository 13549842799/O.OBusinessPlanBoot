package com.cyz.ob.authority.pojo;

import java.util.List;

import com.cyz.basic.pojo.DeleteAbleEntity;
import com.cyz.basic.valid.annotation.EnableCheckOut;
import com.cyz.basic.valid.annotation.FieldMeta;

@EnableCheckOut
public class Roles extends DeleteAbleEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3789750542162598727L;

	@FieldMeta("角色名")
	private String name;
	
	@FieldMeta("显示名")
	private String displayName;
	
	private String describes;
	
	private List<Authorities> authsList;

	public Roles() {
	}

	public Roles(Integer id, Byte delflag) {
		super(id, delflag);
	}

	@Override
	public void acceptId(long id) {
		this.id = Integer.parseInt(String.valueOf(id));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}

	public List<Authorities> getAuthsList() {
		return authsList;
	}

	public void setAuthsList(List<Authorities> authsList) {
		this.authsList = authsList;
	}
	
	

}
