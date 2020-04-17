package com.cfy.web.exception;

import lombok.Getter;
import lombok.Setter;

/**
*@author    created by ChenFangYa
*@date  2020年4月16日---下午5:40:30
*@action
*/
@Getter
@Setter
public class BizException extends RuntimeException{
	private int code;
	private String message;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BizException(int code, String message) {
		this.code = code;
		this.message = message;
    }
}
