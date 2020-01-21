package com.cfy.web.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * @author created by ChenFangYa
 * @date 2020年1月21日---上午11:25:37
 * @action
 */
@Configuration
public class DruidConfig {

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource druid() {
		return new DruidDataSource();
	}

	@Bean
	public ServletRegistrationBean<StatViewServlet> myDruidServlet() {
		ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
		bean.addInitParameter("allow", "127.0.0.1");
		bean.addInitParameter("resetEnable", "false");
		bean.addInitParameter("loginUsername", "admin");
		bean.addInitParameter("loginPassword", "admin");
		return bean;
	}

	@Bean
	public FilterRegistrationBean<WebStatFilter> myStatFilter() {
		FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>(new WebStatFilter());
		bean.addUrlPatterns("/*");
		bean.addInitParameter("exclusions", "*.js,*.gif,/druid/*");
		return bean;
	}
}
