package com.cyz.ob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RestController;


/**
 * 当前系统急切需要的模块是登录模块，权限模块，文学模块， 学习系统树模块
 * 登录模块：
 * @author cyz
 *
 */
@RestController
@SpringBootApplication
@EnableCaching
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	

}
