package com.agoda.auth_service.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Service
@Slf4j
public class TokenService {

    @Autowired
    private final StringRedisTemplate redisTemplate;

    public void saveToken(String key,String value,Long TTL){
        redisTemplate.opsForValue().set(key,value,TTL, TimeUnit.MILLISECONDS);
        log.info("Token saved: " + key);
    }
    public String getToken(String key){
        log.info("Token retrieved: " + key);
        return redisTemplate.opsForValue().get(key);
    }
}
