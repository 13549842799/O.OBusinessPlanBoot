package com.cyz.ob.additional.service;

import com.cyz.basic.service.impl.BasicServiceImplTemplate;
import com.cyz.basic.util.StrUtil;
import com.cyz.ob.additional.pojo.entity.Msg;

/**
 * 
 * @author cyz
 *
 */
public class MsgService extends BasicServiceImplTemplate<Msg> {
	
    public boolean validMsg(String phoneNo, String verificationCode, byte type) {
		
		/*String code = manager.msgCode(phoneNo, type);
		if (!StrUtil.equalsNotNull(code, verificationCode)) {
			return false;
		}
		manager.deleteCode(phoneNo, type);*/
		return true;
	}

	@Override
	public Msg newEntity() {
		
		return new Msg();
	}

}
