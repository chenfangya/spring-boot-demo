package com.cfy.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * @author created by ChenFangYa
 * @date 2020年1月21日---下午5:40:42
 * @action
 */
@SpringBootApplication
@EnableAdminServer
public class AdminServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(AdminServerApplication.class, args);
	}
}
