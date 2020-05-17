package com.cyz.ob.ouser.service.impl;

import org.springframework.stereotype.Service;

import com.cyz.basic.service.impl.BasicServiceImplTemplate;
import com.cyz.ob.ouser.pojo.entity.AccountBookDetail;

@Service
public class AccountBookDetailService extends BasicServiceImplTemplate<AccountBookDetail, Long> {

	@Override
	public AccountBookDetail newEntity() {
		// TODO Auto-generated method stub
		return new AccountBookDetail();
	}

}
