package com.cyz.ob.authority.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyz.basic.controller.BasicController;
import com.cyz.basic.enumeration.DeleteFlag;
import com.cyz.basic.pojo.ResponseResult;
import com.cyz.ob.authority.pojo.Authorities;
import com.cyz.ob.authority.pojo.Roles;
import com.cyz.ob.authority.service.AuthoritiesService;
import com.cyz.ob.authority.service.RolesService;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.common.constant.ResultConstant;
import com.cyz.ob.ouser.pojo.entity.Ouser;
import com.cyz.ob.ouser.service.impl.OuserService;
import com.github.pagehelper.PageInfo;


@RestController
@RequestMapping("/api/authority/auth")
public class AuthoritiesController extends BasicController{
	
	@Autowired
	private OuserService ouserService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	private RolesService rolesService;
	
	
	/**
	 * 创建权限
	 * @param request
	 * @param auths
	 * @return
	 */
	@PostMapping(value="/add.do")
	public ResponseResult<Authorities> addAuthority(
			HttpServletRequest request,
			@RequestBody(required = true) Authorities authorities) {
		ResponseResult<Authorities> response = new ResponseResult<>();
		
		String errorMessage = validUtil.validReturnFirstError(authorities);
		if (errorMessage != null) {
			return response.fail(errorMessage);
		}
		authorities.setId(ouserService.currentUserId(request));
		authorities.setCreateTime(LocalDateTime.now());
		authoritiesService.add(authorities, Integer.class);
		
		return response.success(authorities);
	}
	
	/**
	 * 删除权限
	 * @param request
	 * @param auth
	 * @return
	 */
	@DeleteMapping(value="/{id}/delete.do")
	public ResponseResult<Authorities> deleteAuthority(
			HttpServletRequest request,
			@PathVariable(name="id") Integer id) {
		ResponseResult<Authorities> response = new ResponseResult<>();
		Authorities authorities = new Authorities();
		authorities.setId(id);
        if (authoritiesService.delete(authorities)) {
			return response.success();
		}
        return response.error("error");
	}
	
	/**
	 * 修改权限
	 * @param request
	 * @param auth
	 * @return
	 */
	@PatchMapping(value="/edit.do")
	public ResponseResult<Authorities> updateAuthority(
			HttpServletRequest request,
			@RequestBody(required = true) Authorities auth) {
		
		ResponseResult<Authorities> response = new ResponseResult<>();
		
		String errorMessage = validUtil.validReturnFirstError(auth);
		
		if (errorMessage != null) {
			return response.fail(errorMessage);
		}
		
		auth.setDelflag(DeleteFlag.VALID.getCode());
		auth.setModify(ouserService.currentUserId(request));
		auth.setModifyTime(LocalDateTime.now());
		switch (authoritiesService.update(auth)) {
		case 1:
			return response.success(auth);
		case 0:
			return response.fail(ResultConstant.NOT_EXIST_AUTH);
		default :
			return response.error("error");
		}
	}
	
	@GetMapping(value="/page.re")
	public ResponseResult<PageInfo<Authorities>> authsPage(
			HttpServletRequest request,
			Authorities authorities,
			PageEntity<Authorities> pageParam) {
		ResponseResult<PageInfo<Authorities>> response = new ResponseResult<>();
		
		pageParam.setParams(authorities);
		PageInfo<Authorities> page = authoritiesService.getPage(pageParam);
		
		return response.success(page);
	}
	
	@GetMapping(value="/simpleList.re")
	public ResponseResult<List<Authorities>> authList(
			HttpServletRequest request,
			Authorities authorities) {
		ResponseResult<List<Authorities>> response = new ResponseResult<>();
		
		List<Authorities> auths = authoritiesService.getSimpleList(authorities);
		
		return response.success(auths);
	}
	
	@GetMapping("one.re")
	public ResponseResult<Authorities> getOne(HttpServletRequest request,
		@RequestParam(name="id", required=true) Integer id
			) {
		ResponseResult<Authorities> response = new ResponseResult<>();
		
		Authorities authorities = authoritiesService.getById(id);
		
		return response.success(authorities);
		
	}
	
	/**
	 * 
	 * @param request
	 * @param addAuth
	 * @return
	 */
	@PostMapping("addAuthstoUser.do")
	public ResponseResult<String> addAuthsToUser(
			HttpServletRequest request,
			@RequestBody(required=true) AddAuthsForm addAuth) {
		
		ResponseResult<String> result = new ResponseResult<>();
		
		if (addAuth.getUserId() == null || (addAuth.getInAuthIds() == null && addAuth.getReAuthIds() == null)) {
			return result.fail(ResultConstant.PARAMETER_REQUIRE_NULL);
		}
				
		Ouser user = ouserService.getById(addAuth.getUserId());

		List<Authorities> add = authoritiesService.createByParams(addAuth.getInAuthIds(), DeleteFlag.VALID.getCode()),
				rem = authoritiesService.createByParams(addAuth.getReAuthIds(), DeleteFlag.DELETE.getCode());
		add.addAll(rem);
		authoritiesService.addAuthsToUser(add, user);
		
		return result.success();
	}
	
	/**
	 * 
	 * @param request
	 * @param addAuth
	 * @return
	 */
	@PostMapping("/addAuthstoRole.do")
	public ResponseResult<String> addAuthsToRole(
			HttpServletRequest request,
			@RequestBody(required=true) AddAuthsForm addAuth) {
		
		ResponseResult<String> result = new ResponseResult<>();
		
		if (addAuth.getRoleId() == null || (addAuth.getInAuthIds() == null && addAuth.getReAuthIds() == null)) {
			return result.fail(ResultConstant.PARAMETER_REQUIRE_NULL);
		}
		
		
		Roles role = rolesService.getById(addAuth.getRoleId());
		List<Authorities> add = authoritiesService.createByParams(addAuth.getInAuthIds(), DeleteFlag.VALID.getCode()),
				rem = authoritiesService.createByParams(addAuth.getReAuthIds(), DeleteFlag.DELETE.getCode());
		add.addAll(rem);
		authoritiesService.addAuthsToRole(add, role);
		
		return result.success();
	}
	
	/**
	 * 获取树形格式的资源携带权限的列表
	 * @param request
	 * @param roleId
	 * @return
	 */
	/* @RequestMapping(value="/tree.re",method=RequestMethod.GET)
	 public ResponseResult<Object>  treeResources(
		HttpServletRequest request,
		@RequestParam(value="roleId", required=true)int roleId){
		  	  
		ResponseResult<Object> response = new ResponseResult<>();
		Queue<Resources> queue = new LinkedList<>(resourceService.getFullList(roleId));
		List<Resources> list = resourceService.getResourceTree(queue, null);
		list.forEach(o->System.out.println(o));
		return response.success(list);	  
	  }*/
	
	public static final class AddAuthsForm {
		
		private Integer userId;
		
		private Integer roleId;
		
		private String inAuthIds;
	
        private String reAuthIds;

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public Integer getRoleId() {
			return roleId;
		}

		public void setRoleId(Integer roleId) {
			this.roleId = roleId;
		}

		public String getInAuthIds() {
			return inAuthIds;
		}

		public void setInAuthIds(String inAuthIds) {
			this.inAuthIds = inAuthIds;
		}

		public String getReAuthIds() {
			return reAuthIds;
		}

		public void setReAuthIds(String reAuthIds) {
			this.reAuthIds = reAuthIds;
		}
        
        
				
		
	}
	
}
