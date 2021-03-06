package com.cyz.ob.authority.pojo;

import java.util.List;

import com.cyz.basic.config.security.detail.SecurityAuthority;
import com.cyz.basic.pojo.ModifierEntity;
import com.cyz.basic.valid.annotation.EnableCheckOut;
import com.cyz.basic.valid.annotation.FieldMeta;
import com.cyz.ob.ouser.pojo.entity.Ouser;

/**
 */
@EnableCheckOut
public class Authorities extends ModifierEntity<Integer> implements SecurityAuthority {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1321108800213632856L;
    
	@FieldMeta("权限名")
	private String authName;
	
	@FieldMeta("显示名")
	private String displayName;
	
	private String remark;
	
	private Integer sort;
	
	private Ouser user;
	
	private List<Resources> resources;
	
	

	public Authorities() {
		super();
	}
	
	public Authorities(Integer id, byte delflag) {
		this.id = id;
		this.delflag = delflag;
	}



	public String getAuthName() {
		return authName;
	}



	public void setAuthName(String authName) {
		this.authName = authName;
	}



	public String getDisplayName() {
		return displayName;
	}



	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public Integer getSort() {
		return sort;
	}



	public void setSort(Integer sort) {
		this.sort = sort;
	}

    

	public Ouser getUser() {
		return user;
	}



	public void setUser(Ouser user) {
		this.user = user;
	}



	public List<Resources> getResources() {
		return resources;
	}



	public void setResources(List<Resources> resources) {
		this.resources = resources;
	}



	@Override
	public String getAuthority() {
	    return "/O.OBusinessPlan" + this.authName;
	}



	@Override
	public void acceptId(long id) {
		this.id = Integer.parseInt(String.valueOf(id));
	}
	
	public static class AuthoritiesBuilder {
		private Integer id;
		private String authName;
		private String displayName;
        private Integer userId;
        private String username;
		
		public static AuthoritiesBuilder build() {
			return new AuthoritiesBuilder();
		}
		
		public AuthoritiesBuilder id(Integer id) {
			this.id = id;
			return this;
		}
		
		public AuthoritiesBuilder authName(String authName) {
			this.authName = authName;
			return this;
		}
		
		public AuthoritiesBuilder displayName(String displayName) {
			this.displayName = displayName;
			return this;
		}
		
		public AuthoritiesBuilder userId(Integer userId) {
			this.userId = userId;
			return this;
		}
		
		public AuthoritiesBuilder username(String username) {
			this.username = username;
			return this;
		}
		
		public Authorities create() {
			Authorities authority = new Authorities();
			authority.setId(id);
			authority.setAuthName(authName);
			authority.setDisplayName(displayName);
			if (userId != null || username != null) {
				Ouser user = new Ouser();
				user.setId(userId);
				user.setUsername(username);
				authority.setUser(user);
			}
			return authority;
		}
	}

}
