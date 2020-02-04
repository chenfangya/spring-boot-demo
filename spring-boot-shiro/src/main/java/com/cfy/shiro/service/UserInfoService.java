package com.cfy.shiro.service;

import com.cfy.shiro.entity.UserInfo;

public interface UserInfoService {
	
    /**通过username查找用户信息*/
    public UserInfo findByUsername(String username);
}