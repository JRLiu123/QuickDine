package com.sky.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * ClassName: RedisConfiguration
 * Package: com.sky.config
 * Description:
 *
 * @Author Jingran Liu
 * @Create 2025/1/18 23:56
 * @Version 1.0
 */
@Configuration // configuration class
@Slf4j //record logs
public class RedisConfiguration {
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        log.info("Starting creating redis template object...");
        RedisTemplate redisTemplate = new RedisTemplate();

        // Setting redis connection factory
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // Setting Key Serializer in Spring Data Redis
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        return redisTemplate;

    }
}
