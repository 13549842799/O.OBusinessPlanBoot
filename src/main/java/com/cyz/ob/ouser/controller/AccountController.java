package com.cyz.ob.ouser.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyz.basic.controller.BasicController;
import com.cyz.basic.pojo.ResponseResult;
import com.cyz.basic.util.StrUtil;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.ouser.pojo.entity.Account;
import com.cyz.ob.ouser.service.impl.AccountService;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping(value = "/api/accountManager")
public class AccountController extends BasicController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping(value = "/add.do")
    public ResponseResult<Account> addAccount(HttpServletRequest request,
    		@RequestBody Account account) {
        ResponseResult<Account> response = new ResponseResult<>();
        account.setPassword(accountService.encryptPassword(account.getPassword()));
        if (account.getId() == null) {
        	if (StrUtil.isEmpty(account.getPassword())) {
            	return response.fail("必须填写密码");
            }
        	accountService.add(account, Integer.class);
        } else {
        	accountService.update(account);
        }
 
        return response.success(account);
    }
	
	@DeleteMapping(value = "/{id}/delete.do")
	public ResponseResult<String> deleteAccount(HttpServletRequest request,
    		@PathVariable(name="id")Integer id) {
        ResponseResult<String> response = new ResponseResult<>();
        Account am = new Account();
        am.setId(id);
        accountService.delete(am);
        
        return response.success();
    }
	
	@PostMapping(value = "/update.do")
    public ResponseResult<Account> updateAccount(HttpServletRequest request,
    		Account account) {
        ResponseResult<Account> response = new ResponseResult<>();
        if (account != null && StrUtil.isNotEmpty(account.getPassword())) {
        	account.setPassword(accountService.encryptPassword(account.getPassword()));
        }
        accountService.update(account);
        
        return response.success(account);
    }
	
	@GetMapping(value = "/page.re")
    public ResponseResult<PageInfo<Account>> list(HttpServletRequest request,
    		Account manager,
    		PageEntity<Account> params) {
        ResponseResult<PageInfo<Account>> response = new ResponseResult<>();
        manager.delflag();
        params.setParams(manager);
        System.out.println();
        return response.success(accountService.getPage(params));
    }
    
    @GetMapping(value = "/{id}/read.re")
    public ResponseResult<Account> getOne(HttpServletRequest request,
    		@PathVariable(name="id")Integer id) {
        ResponseResult<Account> response = new ResponseResult<>();
        System.out.println("进入read");
        return response.success(accountService.getById(id));
    }

}
