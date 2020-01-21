package com.cfy.thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

import com.cfy.thymeleaf.dao.InMemoryMessageRepository;
import com.cfy.thymeleaf.dao.MessageRepository;
import com.cfy.thymeleaf.entity.Message;

/**
*@author    created by ChenFangYa
*@date  2020年1月21日---下午1:48:13
*@action
*/
@SpringBootApplication
public class ThymeleafApplication {

	@Bean
	public MessageRepository messageRepository() {
		return new InMemoryMessageRepository();
	}

	@Bean
	public Converter<String, Message> messageConverter() {
		return new Converter<String, Message>() {
			@Override
			public Message convert(String id) {
				return messageRepository().findMessage(Long.valueOf(id));
			}
		};
		
	}

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafApplication.class, args);
	}
}
