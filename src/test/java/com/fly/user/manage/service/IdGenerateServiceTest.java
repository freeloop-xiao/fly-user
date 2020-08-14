package com.fly.user.manage.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/8/9 12:18
 */
@SpringBootTest
public class IdGenerateServiceTest {


    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void createIncr() {
        String key = "serNo";
        Long value = redisTemplate.opsForValue().increment(key);
        System.out.println(value);

         value = redisTemplate.opsForValue().increment(key);
        System.out.println(value);

         value = redisTemplate.opsForValue().increment(key);
        System.out.println(value);

         value = redisTemplate.opsForValue().increment(key);
        System.out.println(value);

    }
}
