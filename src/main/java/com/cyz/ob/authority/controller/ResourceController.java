package com.cyz.ob.authority.controller;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyz.basic.Exception.AddErrorException;
import com.cyz.basic.controller.BasicController;
import com.cyz.basic.enumeration.DeleteFlag;
import com.cyz.basic.pojo.ResponseResult;
import com.cyz.ob.authority.pojo.Resources;
import com.cyz.ob.authority.service.ResourceService;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.common.constant.ResultConstant;
import com.cyz.ob.ouser.service.impl.OuserService;
import com.github.pagehelper.PageInfo;


/**
 * 资源相关接口
 * @author cyz
 *
 */
@RequestMapping("/api/authority/resource")
@RestController
public class ResourceController extends BasicController{
	
	  @Autowired
	  private OuserService ouserService;

	  @Autowired
	  private ResourceService resourceService;
	  
	  /**
	   * 添加资源
	   * @param request
	   * @param resource 资源对象
	   * @return
	   */
	  @PostMapping(value="/add.do")
	  public ResponseResult<Object>  addResource(
		HttpServletRequest request, @RequestBody Resources resource){
		  
		ResponseResult<Object> response = new ResponseResult<>();
		
		String errorMessage = validUtil.validReturnFirstError(resource);
		
		if (errorMessage != null) {
			return response.fail(errorMessage);
		}
		
		Resources parent = null;
		if (resource.getPid() != null) {
			parent = resourceService.getById(resource.getPid());
			
			if (parent == null || parent.getId() == null) {
				return response.fail(ResultConstant.NOT_EXIST_PARENT);
			}
			
			if (parent.getLocking() == 1) {
				return response.fail(ResultConstant.RESOURCE_LOCKING);
			}
		}
		
		Integer userId = ouserService.currentUserId(request);
		resource.setCreator(userId);
		resource.setCreateTime(LocalDateTime.now());		
		try {
			resourceService.add(resource, Integer.class);
			//synSuperAdminAuths (ouserService.currentUsername(request), resource.getId());
			resource = resourceService.getById(resource.getId());
			return response.success(resource);
		} catch (AddErrorException e) {
			e.printStackTrace();
			return response.fail(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return response.fail(e.getMessage());
		}
	  }
	  
	  
	  /**
	   * 删除资源
	   * @param request
	   * @param id
	   * @return
	   */
	  @PostMapping(value="/delete.do")
	  public ResponseResult<Object>  delete(HttpServletRequest request,
			  @RequestBody Resources resources  ){
		  
		  ResponseResult<Object> response = new ResponseResult<>();
		  Resources r = new Resources();
		  r.setId(resources.getId());
		  try {
             return resourceService.delete(r) ? response.success() : response.fail("");
		  } catch (Exception e) {
			e.printStackTrace();
			return response.error("发生未知异常",e.getMessage());
		  }
		  
	  }
	  
	  /**
	   * 更新资源, 移动时不能移动到自级子集中
	   * @param id
	   * @param pid
	   * @param name
	   * @param request_url
	   * @param order
	   * @param style
	   * @param describes
	   * @param type
	   * @param key
	   * @param state
	   * @return
	   */
	  @PostMapping(value="/update.do")
	  public ResponseResult<Object>  updateResource(
		HttpServletRequest request,
	    @RequestBody Resources resources){
		  
		ResponseResult<Object> response = new ResponseResult<>();
		
		String err = validUtil.validReturnFirstError(resources);
		
		if (err != null) {
			return response.fail(err);
		}

		Resources old = resourceService.getById(resources.getId());		
		if (old == null) {
			return response.fail(ResultConstant.NOT_EXIST_RESOURCE);
		}
		
		if (old.getLocking() == 1) {
			return response.fail(ResultConstant.RESOURCE_LOCKING);
		}
	
		Resources parent = resourceService.getById(resources.getPid());
		if (parent == null) {
			return response.fail(ResultConstant.NOT_EXIST_PARENT);
		}
		if (parent.getType() == Resources.INTERFACE) {
			return response.fail(ResultConstant.TYPE_ERROR_FATHER);
		}
        // 处理更改信息
		
		// end
		
		switch (resourceService.updateFull(resources)) {
		case 0:			
			return response.fail("更新失败");
        case 1:	
			return response.success(resources);
		default:
			return response.error("更新异常");
		}
	  }
	  
	  @GetMapping("/one.re")
	  public ResponseResult<Resources> resource(
			  HttpServletRequest request, 
			  @RequestParam("id") Integer id
			  ) {
		  
		  ResponseResult<Resources> result = new ResponseResult<>();
		  
		  Resources r = resourceService.getById(id);
		  
		  return result.success(r);
	  }
	  
	  @GetMapping("/page.re")
	  public ResponseResult<PageInfo<Resources>> pageList(
			  HttpServletRequest request, 
			  PageEntity<Resources> pageParam, Resources resources
			  ) {
		  pageParam.setParams(resources);
		  ResponseResult<PageInfo<Resources>> result = new ResponseResult<>();
		  PageInfo<Resources> page = resourceService.getPage(pageParam);

		  return result.success(page);
	  }
	  
	  @GetMapping("/list.re")
	  public ResponseResult<List<Resources>> list(HttpServletRequest request, Resources resources) {		 
		  ResponseResult<List<Resources>> result = new ResponseResult<>();
		  List<Resources> list = resourceService.getSimpleList(resources);

		  return result.success(list);
	  }
	  
	  
	  /**
	   * 获取树形格式的资源列表
	   * @param request
	   * @param pid 父id
	   * @param name 名称
	   * @param type  类型
	   * @param state 状态
	   * @return
	   */
	  @GetMapping(value="/tree.re")
	  public ResponseResult<Object>  treeResources(
		HttpServletRequest request,
	    @RequestParam(required=false,value="pid")Integer pid,
        @RequestParam(required=false,value="name")String name, 
        @RequestParam(required=false,value="type")Byte type,
        @RequestParam(required=false,value="state")Byte state){
		  
		Resources params = new Resources();
		params.setPid(pid);
		params.setName(name);
		params.setType(type);
		params.setState(state);
		ResponseResult<Object> response = new ResponseResult<>();
		Queue<Resources> queue = new LinkedList<>(resourceService.getList(params));
		List<Resources> list = resourceService.getResourceTree(queue, null);
		list.forEach(o->o.createPath());
		return response.success(list);	  
	  }
	
	  /**
	   * 根据用户获取对应模块资源列表
	   * @param request
	   * @return
	   */
	  @GetMapping(value="/resources.re")
	  public ResponseResult<Object>  selectResourceForAdmin(HttpServletRequest request){
		  
		  ResponseResult<Object> response = new ResponseResult<>();
		  
		  try {
			Integer userId = ouserService.currentUserId(request);
			List<Resources> resources = resourceService.getResourcesForUser(userId);
			return response.success(resourceService.getResourceTree(resources, null));
		  }  catch (Exception e) {
			e.printStackTrace();
			return response.error("发生未知异常",e.getMessage());
		  }
		  
	  }
	  
	  /**
	   * 
	   * 超级管理员的权限无需页面操作，只在代码中同步更新
	   * 当创建一个新的资源时，则把此资源添加到超级管理员角色中，然后刷新redis权限列表
	   * @param accountname
	   * @param resourceId
	   */
	  /**
	   * 20200415 因为权限逻辑修改，当资源增加时没有修改管理员权限，因为用户只要和权限关联
	   */
	  /*private void synSuperAdminAuths (String accountname, int resourceId) {
		  Authority auth = new Authority(1, resourceId, Authority.AWARD, DeleteFlag.VALID.getCode());
		  auth.setLevel(Authority.READWRITE);
		  authService.add(auth);
		  authService.clearAuthsForRole(1);
	  }*/
	  
	  /**
	   * 启用/禁用 资源
	   * @param request
	   * @param id
	   * @return
	   */
	  @PostMapping(value="/state.do")
	  public ResponseResult<Object>  stateUpdate(HttpServletRequest request,
              @RequestParam(required=true,value="id")int id){
		  
		  ResponseResult<Object> response = new ResponseResult<>();
		  
		  try {
			if (resourceService.state(id)) {
				return response.success();
			}
			return response.fail("更新失败");
		  } catch (Exception e) {
			e.printStackTrace();
			return response.error("发生未知异常",e.getMessage());
		  }	  
	  }
	
}
