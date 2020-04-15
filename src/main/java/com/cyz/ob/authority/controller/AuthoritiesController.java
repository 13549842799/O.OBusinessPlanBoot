package com.cyz.ob.authority.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyz.basic.controller.BasicController;
import com.cyz.basic.enumeration.DeleteFlag;
import com.cyz.basic.pojo.ResponseResult;
import com.cyz.ob.authority.pojo.Authorities;
import com.cyz.ob.authority.service.AuthoritiesService;
import com.cyz.ob.common.constant.ResultConstant;
import com.cyz.ob.ouser.service.impl.OuserService;


@RestController
@RequestMapping("/api/authority/auth")
public class AuthoritiesController extends BasicController{
	
	@Autowired
	private OuserService ouserService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	/*@Autowired
	private ResourceService resourceService;*/
	
	
	
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
	 * 为角色修改权限
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
	
	/**
	 * 获取树形格式的资源携带权限的列表
	 * @param request
	 * @param roleId
	 * @return
	 */
	 /*@RequestMapping(value="/tree.re",method=RequestMethod.GET)
	 public ResponseResult<Object>  treeResources(
		HttpServletRequest request,
		@RequestParam(value="roleId", required=true)int roleId){
		  	  
		ResponseResult<Object> response = new ResponseResult<>();
		Queue<Resources> queue = new LinkedList<>(resourceService.getFullList(roleId));
		List<Resources> list = resourceService.getResourceTree(queue, null);
		list.forEach(o->System.out.println(o));
		return response.success(list);	  
	  }
	
	 *//**
	  * 获取角色拥有的权限列表
	  * @param request
	  * @param roleId 角色id
	  * @return
	  *//*
	@GetMapping(value="/list.re")
	public ResponseResult<List<AuthorityWithKey>> authList(
			HttpServletRequest request, 
			@RequestParam(value="roleId", required=true)int roleId) {
		ResponseResult<List<AuthorityWithKey>> response = new ResponseResult<>();
		
		List<AuthorityWithKey> list = authService.getfullList(roleId);
		
		
		return response.success(list);
	}*/
	
}
