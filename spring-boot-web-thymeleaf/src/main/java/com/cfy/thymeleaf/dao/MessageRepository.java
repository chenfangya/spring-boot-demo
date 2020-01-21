package com.cfy.thymeleaf.dao;

import com.cfy.thymeleaf.entity.Message;

/**
*@author    created by ChenFangYa
*@date  2020年1月21日---下午1:52:29
*@action
*/
public interface MessageRepository {

	Iterable<Message> findAll();

	Message save(Message message);

	Message findMessage(Long id);

	void deleteMessage(Long id);
}
