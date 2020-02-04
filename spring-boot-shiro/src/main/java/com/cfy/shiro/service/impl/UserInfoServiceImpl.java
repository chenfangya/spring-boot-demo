package com.cfy.shiro.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cfy.shiro.dao.UserInfoDao;
import com.cfy.shiro.entity.UserInfo;
import com.cfy.shiro.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	@Resource
	private UserInfoDao userInfoDao;

	@Override
	public UserInfo findByUsername(String username) {
		return userInfoDao.findByUsername(username);
	}
}