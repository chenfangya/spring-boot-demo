package com.cfy.shiro.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginResult {
	
    private boolean isLogin = false;
    private String result;
}
