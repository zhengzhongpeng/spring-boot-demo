package com.example.springbootdemo.common.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;




}
