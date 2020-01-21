package com.cfy.thymeleaf.entity;

import java.util.Calendar;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
*@author    created by ChenFangYa
*@date  2020年1月21日---下午1:49:10
*@action
*/
@Setter
@Getter
@ToString
public class Message {

	private Long id;

	@NotEmpty(message = "Text is required.")
	private String text;

	@NotEmpty(message = "Summary is required.")
	private String summary;

	private Calendar created = Calendar.getInstance();
}
