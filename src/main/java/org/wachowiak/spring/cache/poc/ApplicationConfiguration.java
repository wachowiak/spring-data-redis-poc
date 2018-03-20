package org.wachowiak.spring.cache.poc;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.embedded.RedisServer;

import java.io.Serializable;

@Configuration
class ApplicationConfiguration {

    @Profile("redis")
    @Bean
    CacheManager redisCacheManager(){
        return new RedisCacheManager(redisTemplate());
    }

    @Bean
    RedisTemplate<Serializable, Serializable> redisTemplate() {
        RedisTemplate<Serializable, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        redisConnectionFactory.setHostName("localhost");
        redisConnectionFactory.setPort(123);
        redisConnectionFactory.afterPropertiesSet();
        return redisConnectionFactory;
    }

    @Bean
    RedisServer redisServer(){
        return RedisServer
                .builder()
                .build();
    }
}
