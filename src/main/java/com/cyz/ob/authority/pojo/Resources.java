package com.cyz.ob.authority.pojo;

import java.util.List;

import com.cyz.basic.pojo.ModifierEntity;
import com.cyz.basic.valid.annotation.EnableCheckOut;
import com.cyz.basic.valid.annotation.FieldMeta;

@EnableCheckOut
public class Resources extends ModifierEntity<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1871930684499623757L;
	
	public final static byte TOPMENU = 1;
	
	public final static byte SECONDMENU = 2;
	
	public final static byte INTERFACE = 3;

	private Integer pid;
	
	private Integer authId;
	
	@FieldMeta("资源名称")
	private String name;
	
	@FieldMeta("资源显示名称")
	private String displayName;
	
	private String url;
	
	private Byte withParam;
	
	@FieldMeta(name="资源类型")
	private Byte type; //1级菜单 2-菜单 3-接口
	
	@Deprecated
	private String path;
	
	private String describes;
	
	private Integer sort;
	
	@FieldMeta(name="锁定", notNull=false, max="0", maxMess="锁定的资源无法修改")
	private Byte locking;
	
	private Byte state;
	
	private Byte delflag;
	
	private List<Resources> childs;

	public Resources() {
	}

	public Resources(Integer id, Byte delflag) {
		this.id = id;
		this.delflag = delflag;
	}

	public Integer getPid() {
		return pid;
	}



	public void setPid(Integer pid) {
		this.pid = pid;
	}



	public Integer getAuthId() {
		return authId;
	}



	public void setAuthId(Integer authId) {
		this.authId = authId;
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



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}

    

	public Byte getWithParam() {
		return withParam;
	}

	public void setWithParam(Byte withParam) {
		this.withParam = withParam;
	}

	public Byte getType() {
		return type;
	}



	public void setType(Byte type) {
		this.type = type;
	}



	public String getDescribes() {
		return describes;
	}



	public void setDescribes(String describes) {
		this.describes = describes;
	}



	public Byte getLocking() {
		return locking;
	}



	public void setLocking(Byte locking) {
		this.locking = locking;
	}



	public Byte getState() {
		return state;
	}



	public void setState(Byte state) {
		this.state = state;
	}



	public Byte getDelflag() {
		return delflag;
	}



	public void setDelflag(Byte delflag) {
		this.delflag = delflag;
	}

    

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<Resources> getChilds() {
		return childs;
	}

	public void setChilds(List<Resources> childs) {
		this.childs = childs;
	}

	@Override
	public void acceptId(long id) {
		this.id = Integer.parseInt(String.valueOf(id));
	}
	
	public void createPath() {
		if (this.pid == null) {
			this.path =String.valueOf(this.getId());
		}
		if (this.childs == null || this.childs.size() == 0) {
			return;
		}
		childs.forEach(o->o.setPath(this.getId()+","+o.getId()));
	}
	
	public String getTypeStr() {
		if (type == null) {
			return "";
		}
		switch (type) {
		case TOPMENU:
			return "一级模块";
		case SECONDMENU:
			return "二级模块";
		case INTERFACE:
			return "接口";
		default:
			return "";
		}
	}
	

}
