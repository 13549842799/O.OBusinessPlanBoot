package com.cyz.ob.authority.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyz.basic.Exception.AddErrorException;
import com.cyz.basic.controller.BasicController;
import com.cyz.basic.enumeration.DeleteFlag;
import com.cyz.basic.pojo.ResponseResult;
import com.cyz.ob.authority.pojo.Roles;
import com.cyz.ob.authority.service.AuthoritiesService;
import com.cyz.ob.authority.service.RolesService;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.common.constant.ResultConstant;
import com.cyz.ob.ouser.pojo.entity.Ouser;
import com.cyz.ob.ouser.service.impl.OuserService;
import com.github.pagehelper.PageInfo;


/**
 * 增加角色接口
 * 修改角色接口
 * 删除角色接口
 * 给用户添加角色接口
 * 移动角色接口
 * 角色列表接口
 * 用户的角色列表接口
 * @author cyz
 *
 */
@RequestMapping("/api/authority/roles")
@RestController
public class RolesController extends BasicController{
	
	  @Autowired
	  private RolesService rolesService;
	  
	  @Autowired
	  private AuthoritiesService authService;
	  
	  @Autowired
	  private OuserService ouserService;
	
	  /**
	   * 创建角色
	   * @param request
	   * @param role
	   * @return
	   */
	  @PostMapping(value="/add.do")
	  public ResponseResult<Roles>  addRole(
			  HttpServletRequest request,
			  @RequestBody(required = true) Roles role){
		  
		ResponseResult<Roles> response = new ResponseResult<>();
		
		String messageError = validUtil.validReturnFirstError(role);
		if (messageError != null) {
			return response.fail(messageError);
		}
		
		try {
			rolesService.add(role);
			return response.success(role);
		} catch (AddErrorException e) {
			e.printStackTrace();
			return response.error(e.getMessage());
		}
	  }
	  
	  /**
	   * 创建角色
	   * @param request
	   * @param role
	   * @return
	   */
	  @DeleteMapping(value="/{id}/delete.do")
	  public ResponseResult<Object>  deleteRole(
			  HttpServletRequest request, @PathVariable(name="id")Integer id){
		  
		ResponseResult<Object> response = new ResponseResult<>();
		
		Roles role = new Roles();
		
		role.setId(id);
		
		return response.deleteResult(rolesService.delete(role));
		
	  }
	  
	  /**
	   * 编辑（修改）角色
	   * @param request
	   * @param role
	   * @return
	   */
	  @PatchMapping(value="/edit.do")
	  public ResponseResult<Object>  editResource(
			  HttpServletRequest request,
			  @RequestBody(required = true) Roles role){
		  
		ResponseResult<Object> response = new ResponseResult<>();
		
		if (role.getId() == 1) {
			return response.fail("超级管理员不能修改");
		}
		
		switch (rolesService.updateFull(role)) {
		case 1:				
			return response.success(role);
		case 0:
			return response.fail("更新失败");
		case -1:
			return response.error("更新异常");
		}
		
		
		return response.success();
	  }
	  
	  /**
	   * 角色列表
	   * @param request
	   * @param creator
	   * @param state
	   * @return
	   */
	  @GetMapping(value="/page.re")
	  public ResponseResult<PageInfo<Roles>> rolePage(
			  HttpServletRequest request, 
			  PageEntity<Roles> pageParam, 
			  Roles role) {
		  
		  ResponseResult<PageInfo<Roles>> result = new ResponseResult<>();
		  
		  pageParam.setParams(role);
		  PageInfo<Roles> page = rolesService.getPage(pageParam);
		  
		  return result.success(page);
	  }
	  
	  @PostMapping(value="/addToUser.do")
	  public ResponseResult<String> addRoleToUser(
			  HttpServletRequest request,
			  @RequestBody(required = true) AddRolesForm form) {
		  
		  ResponseResult<String> result = new ResponseResult<>();
		  
		  if (form == null || (form.getAddRoleIds() == null && form.getRemoveRoleIds() == null) || form.getUserId() == null) {
			  return result.fail(ResultConstant.PARAMETER_REQUIRE_NULL);
		  }
		  
		  Ouser user = ouserService.getById(form.getUserId());
		  
		  List<Roles> adds = rolesService.createByParams(form.getAddRoleIds(), DeleteFlag.VALID.getCode()),
				  removes = rolesService.createByParams(form.getRemoveRoleIds(), DeleteFlag.DELETE.getCode());
		    		  
		  rolesService.addRoleToUser(adds, removes, user);
		  
		  return result.success();
	  }
	
	  /**
	   * 角色列表
	   * @param request
	   * @param creator
	   * @param state
	   * @return
	   */
	  @GetMapping(value="/list.re")
	  public ResponseResult<List<Roles>> rolePageList(
			  HttpServletRequest request) {
		  
		  return null;
	  }
	  
	  public static final class AddRolesForm {
		    
		  private Integer userId;
		  private String  addRoleIds;
		  private String removeRoleIds;
		  
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public String getAddRoleIds() {
			return addRoleIds;
		}
		public void setAddRoleIds(String addRoleIds) {
			this.addRoleIds = addRoleIds;
		}
		public String getRemoveRoleIds() {
			return removeRoleIds;
		}
		public void setRemoveRoleIds(String removeRoleIds) {
			this.removeRoleIds = removeRoleIds;
		}
		
		  
		  
	  }
	  
	  /**
	   * 首先查看用户是否存在
	   * 然后绑定角色到用户上
	   * @param request
	   * @param userId
	   * @param roleCodes
	   * @return
	   */
	 /* @ApiOperation("给用户添加角色")
	  @PostMapping(value="/addToUser.do")
	  @IgnoreSecurity()
	  public ResponseResult<List<Roles>> addRoleToUser(
			  HttpServletRequest request,
			  @RequestBody List<AdminRole> adminrs) {
		  
		  ResponseResult<List<Roles>> response = new ResponseResult<>();
          
		  Integer currentAdmin = currentAdminId(request);		  
		  for (AdminRole ar : adminrs) {
			if (ar.getDelflag() == 1) {
				ar.setCreator(currentAdmin);
				ar.setCreateTime(new Timestamp(new Date().getTime()));
			}
			ar.setModifier(currentAdmin);
		  }		  
		  roleService.insertOrUpdateRelation(adminrs);
		  *//**
		   * 清空当前用户的redis权限列表
		   *//*
		  authService.clearAuthsForAccount(getAccountName(request));
		  //获取当前用户拥有角色
		  List<Roles> adminRoles = roleService.getRolesOfAdmin(currentAdmin, true);			 
		  return response.success(adminRoles);
		  
	  }*/
	  
	 /* @ApiOperation("删除用户的指定角色")
	  @PostMapping(value="/delRelation.do")
	  @IgnoreSecurity()
	  public ResponseResult<Object> delflagFromUser(
			  HttpServletRequest request,
			  @ApiParam(value = "用户id", required = true)
			  @RequestParam(value="userId", required= true)Integer userId,
			  @ApiParam(value = "角色id数组", required = true)
			  @RequestParam(value="roleIds", required= true)int[] roleIds
			  ) {
		  ResponseResult<Object> response = new ResponseResult<>();
		  Admin admin = adminService.getById(new Admin(userId, DeleteFlag.VALID.getCode()));
		  if (admin == null) {
			return response.fail(ResultConstant.NOT_EXIST_ADMIN);
		  }
		  if (!roleService.removeRoleFromAdmin(userId, 
				roleIds, currentAdminId(request))) {
			return response.fail("删除失败");
		  }			
		  authService.clearAuthsForAccount(getAccountName(request));
		  //获取当前用户拥有角色
		  List<Roles> adminRoles = roleService.getRolesOfAdmin(userId, true);			 
		  return response.success(adminRoles);
		  
	  }
	  
	  @ApiOperation("移动角色")
	  @PatchMapping(value="/move.do")
	  @IgnoreSecurity()
	  public ResponseResult<List<Roles>> movingRole(
			  HttpServletRequest request,
			  @RequestBody Roles role) {
		  ResponseResult<List<Roles>> response = new ResponseResult<>();
		  role.setDelflag(DeleteFlag.VALID.getCode());
		  if (roleService.getById(role) == null) {
			  return response.fail(ResultConstant.NOT_EXIST_ROLE);
		  }
		  List<Roles> roles = roleService.updatePosition(role.getId(), role.getSort());
		  
		  return response.success(roles);
	  }*/
	  
}
