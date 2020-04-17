package com.cfy.redis.lock;
/**
*@author    created by ChenFangYa
*@date  2020年4月10日---下午3:16:45
*@action
*/

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DistributedLock {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private RedisScript<Boolean> lockScript;
	@Autowired
	private RedisScript<Long> unlockScript;
	
	public Boolean distributedLock(String key, String uuid, String secondsToLock) {
		Boolean locked = false;
		try {
			String millSeconds = String.valueOf(Integer.parseInt(secondsToLock) * 100);
			locked = redisTemplate.execute(lockScript, Collections.singletonList(key), uuid, millSeconds);
			log.info("distributedLock.key:{}, uuid:{}, timeToLock:{}, locked:{}, millSeconds:{}", key, uuid, secondsToLock, locked, millSeconds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locked;
	}
	public void distributedUnLock(String key, String uuid) {
		long unlocked = redisTemplate.execute(unlockScript, Collections.singletonList(key), uuid);
		log.info("distributedUnLock.key:{}, uuid:{}, unlocked:{}", key, uuid, unlocked);
	}
}
