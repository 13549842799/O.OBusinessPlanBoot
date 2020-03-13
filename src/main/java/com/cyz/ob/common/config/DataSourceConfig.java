package com.cyz.ob.common.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;



/**
 * 数据库配置类
 * @author cyz
 *
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DataSourceConfig {
	
   @Autowired
   private DataSource dataSource;
   
   @Value("${mybatis.mapper-locations}")
   private String mapperLocations;
   
   @Value("${mybatis.type-aliases-package}")
   private String typeAliasesPackage;
   /**
    * 自定义一个SqlSessionFactoryBean来代替SpringBoot的，其中必须要配上beanName，@ConditionalOnMissingBean表示如果系统没有SqlSessionFactoryBean则使用这个
    * 
    * @return
    */
   @Bean("sqlSessionFactory")
   public SqlSessionFactoryBean sqlSessionFactoryBean() {

	   SqlSessionFactoryBean factory = new SqlSessionFactoryBean();	   
	   //配置数据源
	   DruidDataSource source = (DruidDataSource)dataSource;

	   factory.setDataSource(dataSource);
	   org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
	   configuration.setCallSettersOnNulls(true);
	   factory.setConfiguration(configuration);
	   factory.setTypeAliasesPackage(typeAliasesPackage); //配置类型别名
	   //配置分页插件
	   /*PageInterceptor pageInterceptor = new PageInterceptor();
	   Properties ps = new Properties();
	   ps.put("helperDialect", "mysql");
	   pageInterceptor.setProperties(ps);
	   Interceptor[] is = {pageInterceptor};
	   factory.setPlugins(is);*/
	   try {
		   PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		   // 能加载多个，所以可以配置通配符(如：classpath*:mapper/**/*.xml)
	       factory.setMapperLocations(resourcePatternResolver.getResources(mapperLocations));
	   } catch (IOException e) {
		   e.printStackTrace();
	   }

	   return factory;
   }
}
