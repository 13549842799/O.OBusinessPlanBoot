package com.cyz.ob.ouser.controller;

import java.util.List;

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
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.ouser.pojo.entity.Account;
import com.cyz.ob.ouser.pojo.entity.AccountBook;
import com.cyz.ob.ouser.pojo.entity.AccountBookDetail;
import com.cyz.ob.ouser.service.impl.AccountBookDetailService;
import com.cyz.ob.ouser.service.impl.AccountBookService;
import com.cyz.ob.ouser.service.impl.OuserService;
import com.github.pagehelper.PageInfo;
@RestController
@RequestMapping(value = "/api/accountbook")
public class AccountBookControlelr extends BasicController {
	
	@Autowired
	private OuserService ouserService;
	
	@Autowired
	private AccountBookService accountBookService;
	
	@Autowired
	private AccountBookDetailService detailService;
	
	@PostMapping(value = "/save.do")
    public ResponseResult<AccountBook> addAccount(HttpServletRequest request,
    		@RequestBody AccountBook accountBook) {
        ResponseResult<AccountBook> response = new ResponseResult<>();

        if (accountBook.getId() == null) {
        	Integer user = ouserService.currentUserId(request);
        	accountBook.create(user);
        	accountBookService.add(accountBook, Integer.class);
        } else {
        	accountBookService.update(accountBook);
        }
 
        return response.success(accountBook);
    }
	
	@PostMapping(value = "/saveDetail.do")
    public ResponseResult<AccountBookDetail> saveDetail(HttpServletRequest request,
    		@RequestBody AccountBookDetail accountBook) {
        ResponseResult<AccountBookDetail> response = new ResponseResult<>();

        if (accountBook.getId() == null) {
        	Integer user = ouserService.currentUserId(request);
        	accountBook.create(user);
        	detailService.add(accountBook, Long.class);
        } else {
        	detailService.update(accountBook);
        }
 
        return response.success(accountBook);
    }
	
	@DeleteMapping(value = "/{id}/delete.do")
	public ResponseResult<String> deleteAccount(HttpServletRequest request,
    		@PathVariable(name="id")Integer id) {
        ResponseResult<String> response = new ResponseResult<>();
        AccountBook am = new AccountBook();
        am.setId(id);
        accountBookService.delete(am);
        
        return response.success();
    }
	
	@DeleteMapping(value = "/{id}/deleteDetail.do")
	public ResponseResult<String> deleteDetail(HttpServletRequest request,
    		@PathVariable(name="id")Long id) {
        ResponseResult<String> response = new ResponseResult<>();
        AccountBookDetail am = new AccountBookDetail();
        am.setId(id);
        detailService.delete(am);
        
        return response.success();
    }
	
	@GetMapping(value = "/list.re")
    public ResponseResult<List<AccountBook>> list(HttpServletRequest request) {
        ResponseResult<List<AccountBook>> response = new ResponseResult<>();
        
        Integer user = ouserService.currentUserId(request);
        
        AccountBook book = new AccountBook();
        book.delflag();
        book.setCreator(user);
        return response.success(accountBookService.getList(book));
    }
	
	@GetMapping(value = "/detaillist.re")
    public ResponseResult<List<AccountBookDetail>> detaillist(HttpServletRequest request) {
        ResponseResult<List<AccountBookDetail>> response = new ResponseResult<>();
        
        Integer user = ouserService.currentUserId(request);
        
        AccountBookDetail book = new AccountBookDetail();
        book.delflag();
        book.setCreator(user);
        return response.success(detailService.getList(book));
    }

}
