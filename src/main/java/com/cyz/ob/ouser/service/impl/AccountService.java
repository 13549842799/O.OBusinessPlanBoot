package com.cyz.ob.ouser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cyz.basic.util.StrUtil;
import com.cyz.ob.basic.entity.PageEntity;
import com.cyz.ob.basic.service.PageServiceTemplate;
import com.cyz.ob.common.util.DesUtil;
import com.cyz.ob.ouser.mapper.AccountMapper;
import com.cyz.ob.ouser.pojo.entity.Account;

@Service
public class AccountService extends PageServiceTemplate<Account, PageEntity<Account>, Integer> {
	
	@Autowired
	private AccountMapper mapper;
	
	@Value("own.account.key")
	private String key;

	@Override
	public Account newEntity() {
		return new Account();
	}
	
	public String encryptPassword(String password){
		if (StrUtil.isEmpty(password) || StrUtil.isEmpty(key)) {
			return null;
		}
		key +=key += key;
		return DesUtil.getInstance().encrypt(password, key);
	}
	
	public String decryptPassword(String encryptedPassword, int adminId){
		key +=key += key;
		if (StrUtil.isEmpty(encryptedPassword) || StrUtil.isEmpty(key)) {
			return null;
		}
		return DesUtil.getInstance().decrypt(encryptedPassword, key);
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean checkKey(String key) {
		return this.key.equals(key);
	}

}
