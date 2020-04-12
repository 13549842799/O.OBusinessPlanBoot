package com.cyz.ob.common.config.security;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

import com.cyz.basic.config.security.authentication.UsernamePasswordAuthenticationToken;
import com.cyz.basic.config.security.authentication.dao.CustomSecurityProvider;
import com.cyz.basic.config.security.authentication.dao.DaoAuthenticationProvider;
import com.cyz.basic.config.security.core.userdetails.UserDetails;
import com.cyz.basic.config.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.cyz.basic.config.security.exception.AuthenticationException;
import com.cyz.basic.config.security.exception.BadCredentialsException;
import com.cyz.basic.config.security.provisioning.UserDetailServiceSupport;


/**
 * 
 * @author cyz
 *
 */
public class MyProvider extends DaoAuthenticationProvider{
	
	private final static int DEF_MAX_LOGIN_COUNT = 5;
	
	private int loginMaxCount = DEF_MAX_LOGIN_COUNT;
	
	private long expire = 0;
	
	private TimeUnit unit = TimeUnit.MINUTES;
	
	private final static String LOGIN_MAX_COUNT_PREFIX = "login_max_count";
	
	private final RedisTemplate<String, Object>  redisTemplate;
	
	public MyProvider(RedisTemplate<String, Object>  redisTemplate, UserDetailServiceSupport detailsService) {
		this.redisTemplate = redisTemplate;
		this.detailsService = detailsService;
		this.setPasswordEncoder(new BCryptPasswordEncoder(16));
	}	

	public void setLoginMaxCount(int loginMaxCount) {
		this.loginMaxCount = loginMaxCount;
	}
	
	public void setExpireTime(long expire, TimeUnit unit) {
		this.expire = expire;
		this.unit = unit;
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

		if (authentication.getCredentials() == null) {
			logger.debug("Authentication failed: no credentials provided");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
		}
		System.out.println("进入private");
		Object countObj = redisTemplate.opsForValue().get(LOGIN_MAX_COUNT_PREFIX + userDetails.getUsername());
		int count = countObj == null ? 0 : Integer.parseInt(countObj.toString());
		
		if (count >= loginMaxCount) {
			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.overload",
					"Wrong password"));
		}

		String presentedPassword = authentication.getCredentials().toString();

		if (!getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
			logger.debug("Authentication failed: password does not match stored value");
			
			if (count == 1) {
				redisTemplate.opsForValue().set(LOGIN_MAX_COUNT_PREFIX + userDetails.getUsername(), count, expire, unit);
			} else {
				redisTemplate.opsForValue().increment(LOGIN_MAX_COUNT_PREFIX + userDetails.getUsername(), 1);
			}

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
		}
	}
}
