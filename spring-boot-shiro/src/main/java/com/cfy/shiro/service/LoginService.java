package com.cfy.shiro.service;

import org.apache.shiro.session.Session;

import com.cfy.shiro.entity.LoginResult;

public interface LoginService {
	
    LoginResult login(String userName, String password);
    
    String getCurrentUserName();
    
    Session getSession();
    
    void logout();
}
