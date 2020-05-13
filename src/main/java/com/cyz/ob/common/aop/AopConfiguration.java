package com.cyz.ob.common.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 
 * @author cyz
 *
 */
@EnableAspectJAutoProxy
@Configuration
public class AopConfiguration {
	
	@Bean
	public RecordAop recordAop() {
		return new RecordAop();
	}

}
