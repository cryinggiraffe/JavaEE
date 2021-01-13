package com.example.redis;

import com.example.redis.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringbootRedisApplicationTests {

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	RedisTemplate redisTemplate;

	@Test
	void contextLoads() {

		//stringRedisTemplate.opsForValue().set("msg","Hello Redis!");

		String msg = stringRedisTemplate.opsForValue().get("msg");
		System.out.println(msg);
	}

	@Test
	public void test2(){

		//redisTemplate.opsForValue().set("user",new User(1,"YM",22));

		User user = (User) redisTemplate.opsForValue().get("user");
		System.out.println(user);
	}

}
