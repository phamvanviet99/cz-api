package com.phamvanviet.demo.domain.services;

import com.phamvanviet.demo.domain.model.TokenInfo;
import com.phamvanviet.demo.domain.utils.CacheKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
public class CacheManagerService {

    private final long EXPIRE_TIME = 60 * 60 * 1000;

//    @Autowired
//    private RedisTemplate redisTemplate;

    @Autowired
    private RedisTemplate<String, TokenInfo> tokenInfoRedisTemplate;

    public void setToken(String token, Integer userId) {
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setUserId(userId);
        tokenInfoRedisTemplate.opsForValue().set(
                CacheKey.genKey(token), tokenInfo, Duration.ofMillis(EXPIRE_TIME));
    }

    public TokenInfo getToken(String token) {
        try {
            String key = CacheKey.genKey(token);
            return tokenInfoRedisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteToken(String token) {
        String key = CacheKey.genKey(token);
        tokenInfoRedisTemplate.delete(key);
    }
}
