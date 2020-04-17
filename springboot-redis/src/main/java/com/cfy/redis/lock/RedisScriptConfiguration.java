package com.cfy.redis.lock;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.script.RedisScript;

import lombok.extern.slf4j.Slf4j;

/**
*@author    created by ChenFangYa
*@date  2020年4月10日---下午3:36:14
*@action
*/
@Configuration
@Slf4j
public class RedisScriptConfiguration {

	public RedisScript<Long> limitScript() {
		RedisScript redisScript = null;
		return redisScript;
	}
}
