package com.cfy.thymeleaf.enums;
/**
*@author    created by ChenFangYa
*@date  2020年1月21日---下午2:25:14
*@action
*/
public enum PagesEnum {

	MESSAGES_VIEW("messages/view"),
	MESSAGES_LIST("messages/list"),
	MESSAGES_FORM("messages/form"),
	INDEX("index"),
	FRAGMENT("fragment"),
	LAYOUT("layout"),
	HOME("home"),
	;
	
	private String path;

	private PagesEnum(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
