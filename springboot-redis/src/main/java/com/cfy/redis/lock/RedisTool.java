package com.cfy.redis.lock;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisTool {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
 
    //锁名称
    public static final String LOCK_PREFIX = "redis_lock";
    //加锁失效时间，毫秒
    public static final int LOCK_EXPIRE = 300; // ms
    
    public boolean lock(String key){
    	String lock = LOCK_PREFIX + key;
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
  
            long expireAt = System.currentTimeMillis() + LOCK_EXPIRE + 1;
            Boolean acquire = connection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());
            if (Boolean.TRUE.equals(acquire)) {
                return true;
            } else {
  
                byte[] value = connection.get(lock.getBytes());
  
                if (Objects.nonNull(value) && value.length > 0) {
  
                    long expireTime = Long.parseLong(new String(value));
                     // 如果锁已经过期
                    if (expireTime < System.currentTimeMillis()) {
                        // 重新加锁，防止死锁
                        byte[] oldValue = connection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + LOCK_EXPIRE + 1).getBytes());
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }
            return false;
        });
    }
  
    /**
     * 删除锁
     *
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(LOCK_PREFIX +key);
    }
    
    
    
    
    
    
    /**
	 * setNx
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean setNx(String key, String value) {
		return redisTemplate.opsForValue().setIfAbsent(key, value);
	}

	/**
	 * setNx
	 * 
	 * @param key
	 * @param value
	 * @param seconds 过期时间，单位秒
	 * @return
	 */
	public Boolean setNx(String key, String value, Long seconds) {
		return redisTemplate.opsForValue().setIfAbsent(key, value, seconds, TimeUnit.SECONDS);
	}

	/**
	 * 删除key
	 * 
	 * @param key
	 * @return
	 */
	public Boolean deleteKey(String key) {
		return redisTemplate.delete(key);
	}

	/**
	 * 获取NX设置的值
	 * 
	 * @param key
	 * @return
	 */
	public String getNX(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * 设置key的过期时间
	 * 
	 * @param key
	 * @param seconds
	 * @return
	 */
	public Boolean expireKey(String key, Long seconds) {
		return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
	}
  
}