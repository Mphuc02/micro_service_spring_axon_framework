package dev.common_service.config;

import dev.common_service.properties.RedisProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {
    private final RedisProperties redisProperties;

    @Bean
    public JedisConnectionFactory factory(){
        JedisConnectionFactory redisFactory = new JedisConnectionFactory();
        redisFactory.setHostName(redisProperties.getHost());
        redisFactory.setPort(redisProperties.getPort());
        return redisFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory());
        return template;
    }
}