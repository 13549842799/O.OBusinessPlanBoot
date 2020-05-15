package com.cyz.ob.ouser.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyz.basic.controller.BasicController;
import com.cyz.basic.pojo.ResponseResult;
import com.cyz.basic.util.StrUtil;
import com.cyz.ob.additional.pojo.entity.Msg;
import com.cyz.ob.additional.service.MsgService;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.common.constant.ResultConstant;
import com.cyz.ob.ouser.pojo.entity.Ouser;
import com.cyz.ob.ouser.service.impl.OuserService;
import com.github.pagehelper.PageInfo;


/**
 * 用户模块接口控制类
 * @author cyz
 *
 */
@RestController
@RequestMapping("/api/ouser")
public class OuserController extends BasicController{
	
	@Autowired
	private OuserService service;
	
	@Autowired
	private MsgService msgService;

	/**
	 * 获取账号信息
	 * @param request
	 * @param accountname 用户名
	 * @return
	 */
	@GetMapping(value="/admin_main.re")
    public ResponseResult<Ouser> searchAdminMain(HttpServletRequest request, @RequestParam(required=true,value="accountname")String accountname){
	    
	    ResponseResult<Ouser> response = new ResponseResult<>();
	    Ouser user = service.getByUsername(accountname);
	    user.setPassword(null);
	    return response.success(user);
	   
    }
	
	/**
	 * 账号信息修改-判断账号昵称否重复
	 * @param request
	 * @param nikename 账号昵称
	 * @return
	 */
    @GetMapping(value="/check_nikename_exist.do")
    public ResponseResult<String> checkAccountRepeat(HttpServletRequest request,
		   @RequestParam(required=true,value="nikename")String nikename){
	    
	    ResponseResult<String> response = new ResponseResult<>();
	    
	    Ouser user = service.currentUser(request);
	    
	    if (user.getNikename().equals(nikename)) {
	    	return response.fail("新昵称不能与原昵称相同");
	    }
	    
	    boolean exist = service.checkNikenameExist(nikename);
	    if (exist) {
			return response.fail("此昵称已存在");
		}		    
	    return response.success();	   
   }

    /**
     * 账号信息修改-修改密码
     * @param request
     * @param oldPass 旧密码
     * @param newPass 新密码
     * @param verificationCode 验证码
     * @param phoneNo 手机号
     * @return
     */
	 @RequestMapping(value="/alter_password.do",method=RequestMethod.PATCH)
	 public ResponseResult<String> alterPassword(HttpServletRequest request,
			   @RequestParam(required=false,value="oldPass")String oldPass,
			   @RequestParam(required=false,value="newPass")String newPass,
			   @RequestParam(required=false,value="verificationCode")String verificationCode,
			   @RequestParam(required=false,value="phoneNo")String phoneNo){
		    
		    ResponseResult<String> response = new ResponseResult<>();
		    
		    if (StrUtil.isEmpty(oldPass)) {
				return response.fail("请输入旧密码");
			}
		    if (StrUtil.isEmpty(newPass)) {
				return response.fail("请输入新密码");
			}
		    //验证码检验
		    if(!msgService.validMsg(phoneNo, verificationCode, Msg.PASSWORD)) {
		    	return response.fail("验证码错误");
		    }
		    switch (service.alterPassword(oldPass,newPass)) {
				case 1:
					return response.success("密码修改成功");
				case 0:
					return response.fail("密码错误");
				default:
					return response.fail("两次密码不相同");
			}		    		   
	   }
	 
	 @PostMapping("/bindPhone.do")
	 public ResponseResult<String> bindPhone(HttpServletRequest request,
			   @RequestParam(required=false,value="password")String password,
			   @RequestParam(required=false,value="newPhone")String newPhone,
			   @RequestParam(required=false,value="verificationCode")String verificationCode
			   ) {
		   ResponseResult<String> response = new ResponseResult<>();
		   
		   return response.success();
	 }
	 
	 /**
	  * 账号信息修改-修改昵称
	  * @param request
	  * @param admin
	  * @return
	  */
	 @PostMapping("/alterNikeName.do")
	 public ResponseResult<Object> alterNikeName(
		HttpServletRequest request,@RequestBody Ouser user){
		 
	    ResponseResult<Object> response = new ResponseResult<>();
        if (user == null  || StrUtil.isEmpty(user.getNikename())) {
			return response.error(ResultConstant.PARAMETER_ERROR);
	    }
        String username = service.currentUsername(request);
        if (service.checkNikenameExist(user.getNikename())) {
			return response.fail("此昵称已存在");
	    } 
        Ouser cacheUser = service.getSimpleByUsername(username);
        cacheUser.setNikename(user.getNikename());
        if (service.update(cacheUser) == 1) {              
		    return response.success();
	    } 
		return response.error("未知错误");
		   
	}
	
	@GetMapping("/page.re")
	public ResponseResult<PageInfo<Ouser>> pages(HttpServletRequest request,
		Ouser ouser,
		PageEntity<Ouser> pageParam) {
		ResponseResult<PageInfo<Ouser>> result = new ResponseResult<>();
		
		pageParam.setParams(ouser);
		PageInfo<Ouser> page = service.getPage(pageParam);
		
		return result.success(page);
	} 
	
	@GetMapping(value="/loginUser.re")
    public ResponseResult<Ouser> searchLoginUser(HttpServletRequest request){
	    ResponseResult<Ouser> response = new ResponseResult<>();
	    Ouser user = service.currentUser(request);
	    user.setPassword(null);
	    return response.success(user);
	   
    }
	
	
	@GetMapping(value="/login")
    public ResponseResult<Ouser> l(HttpServletRequest request){
		ResponseResult<Ouser> response = new ResponseResult<>();
	    return response.success();
	   
    }
	
	@PostMapping(value="/logout")
    public ResponseResult<Ouser> O(HttpServletRequest request){
		ResponseResult<Ouser> response = new ResponseResult<>();
	    return response.success();
	   
    }

}
