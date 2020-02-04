package com.cfy.shiro.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.cfy.shiro.entity.LoginResult;
import com.cfy.shiro.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

	@Override
	public LoginResult login(String userName, String password) {
		LoginResult loginResult = new LoginResult();
		if (userName == null || userName.isEmpty()) {
			loginResult.setLogin(false);
			loginResult.setResult("用户名为空");
			return loginResult;
		}
		String msg = "";
		// 1、获取Subject实例对象
		Subject currentUser = SecurityUtils.getSubject();

//        // 2、判断当前用户是否登录
//        if (currentUser.isAuthenticated() == false) {
//
//        }

		// 3、将用户名和密码封装到UsernamePasswordToken
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

		// 4、认证
		try {
			currentUser.login(token);// 传到MyAuthorizingRealm类中的方法进行认证
			Session session = currentUser.getSession();
			session.setAttribute("userName", userName);
			loginResult.setLogin(true);
			return loginResult;
			// return "/index";
		} catch (UnknownAccountException e) {
			msg = "UnknownAccountException -- > 账号不存在：";
			log.error(msg, e);
		} catch (IncorrectCredentialsException e) {
			msg = "IncorrectCredentialsException -- > 密码不正确：";
			log.error(msg, e);
		} catch (AuthenticationException e) {
			msg = "用户验证失败";
			log.error(msg, e);
		}

		loginResult.setLogin(false);
		loginResult.setResult(msg);

		return loginResult;
	}

	@Override
	public void logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
	}

	@Override
	public String getCurrentUserName() {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		return (String) session.getAttribute("userName");
	}

	@Override
	public Session getSession() {
		Subject currentUser = SecurityUtils.getSubject();
		return currentUser.getSession();
	}
}
