package com.cyz.ob.common.config.security;

import org.springframework.data.redis.core.RedisTemplate;

import com.cyz.basic.config.security.authentication.UsernamePasswordAuthenticationToken;
import com.cyz.basic.config.security.authentication.dao.DaoAuthenticationProvider;
import com.cyz.basic.config.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.cyz.basic.config.security.provisioning.UserDetailServiceSupport;


/**
 * 
 * @author cyz
 *
 */
public class MyProvider extends DaoAuthenticationProvider{
	
	private final static String LOGIN_MAX_COUNT_PREFIX = "login_max_count";
	
	private final RedisTemplate<String, Object>  redisTemplate;
	
	public MyProvider(RedisTemplate<String, Object>  redisTemplate, UserDetailServiceSupport detailsService) {
		this.redisTemplate = redisTemplate;
		this.detailsService = detailsService;
		this.setPasswordEncoder(new BCryptPasswordEncoder(16));
	}	
	

	@Override
	public int getCurrentLoginCount(UsernamePasswordAuthenticationToken authentication) {
		Object countObj = redisTemplate.opsForValue().get(LOGIN_MAX_COUNT_PREFIX + authentication.getPrincipal());
		return countObj == null ? 0 : Integer.parseInt(countObj.toString());
	}

	@Override
	public void updateLoginCount(int laststCount, UsernamePasswordAuthenticationToken authentication) {
		System.out.println("更新尝试登录次数");
		if (laststCount == 1) {
			redisTemplate.opsForValue().set(LOGIN_MAX_COUNT_PREFIX + authentication.getPrincipal()
			    , laststCount, maxLoginCount.getExpire(), maxLoginCount.getUnit());
		} else {
			redisTemplate.opsForValue().increment(LOGIN_MAX_COUNT_PREFIX + authentication.getPrincipal(), 1);
		}
	}

}
