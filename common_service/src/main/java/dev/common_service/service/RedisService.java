package dev.common_service.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final Gson gson;

    public void save(String key, Object object){
        log.info(String.format("Put value %s to Redis", key, Object.class));
        redisTemplate.opsForValue().set(key, object);
    }

    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }
}