package com.cfy.redis.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.cfy.redis.RedisApplication;
import com.cfy.redis.lock.RedisTool;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RedisApplication.class }) // 必需指定启动类
public class SpringbootDemoApplicationTests {
	
	private static int TOTAL_PRE_THREADS = 10;
    private static long SECONDS = 10;
    protected static boolean isSkipLock = false;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private RedisTool redisTool;

	@Test
	public void testRedis() {
		redisTemplate.opsForValue().set("name", "zhangsan");
		Assert.assertEquals("zhangsan", redisTemplate.opsForValue().get("name"));
	}

	@Test
	public void testRedLock() throws InterruptedException {

		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			executor.submit(this::deal);
		}
		Thread.sleep(10000L);
	}
	
	@Test
	public void testRedisLock() throws InterruptedException {
	}

	private void deal() {
		String key = "test";
		boolean lock = redisTool.lock(key);
		if (lock) {
			// 执行逻辑操作
			System.out.println(String.format("lock is lock....  %s.... thread name is %s", "first if **********",
					Thread.currentThread().getName()));
			redisTool.delete(key);
			return;
		} else {
			// 设置失败次数计数器, 当到达5次时, 返回失败
			int failCount = 1;
			System.out.println(String.format("thread name is %s 未获取到lock ", Thread.currentThread().getName()));
			while (failCount <= 5) {
				System.out.println(String.format("thread name is %s 尝试获取lock failCount= %s",
						Thread.currentThread().getName(), failCount));
				// 等待100ms重试
				try {
					Thread.sleep(100L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (redisTool.lock(key)) {
					// 执行逻辑操作
					System.out.println(String.format("lock is lock....  %s.... thread name is %s",
							"second if **********", Thread.currentThread().getName()));
					redisTool.delete(key);
					return;
				} else {
					failCount++;
				}
			}
			throw new RuntimeException("现在创建的人太多了, 请稍等再试");
		}
	}
}
