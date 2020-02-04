package com.cfy.shiro.enums;

public enum PagesEnum {

	INDEX("index"),
	LOGIN("login"),
	KICK_OUT_2("kickOut2"),
	PAGE_403("403"),
	USERINFO("userInfo"),
	USERINFO_ADD("userInfoAdd"),
	USERINFO_DEL("userInfoDel"),
	;
	
	private String path;

	private PagesEnum(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
