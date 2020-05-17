package com.cyz.ob.ouser.service.impl;

import org.springframework.stereotype.Service;

import com.cyz.basic.service.impl.BasicServiceImplTemplate;
import com.cyz.ob.ouser.pojo.entity.AccountBook;

@Service
public class AccountBookService extends BasicServiceImplTemplate<AccountBook, Integer> {

	@Override
	public AccountBook newEntity() {
		// TODO Auto-generated method stub
		return new AccountBook();
	}

}
