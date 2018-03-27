package org.wachowiak.spring.cache.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisShardInfo;

import java.io.Serializable;

@Configuration
public class CacheClientConfiguration {

    @Value("${redis.server.host:localhost}")
    private String redisServerHost;

    @Value("${redis.server.port:6379}")
    private int redisServerPort;

    @Bean
    RedisTemplate<Serializable, Serializable> redisTemplate() {
        RedisTemplate<Serializable, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    RedisConnectionFactory redisConnectionFactory() {
        JedisShardInfo jedisShardInfo = new JedisShardInfo(redisServerHost, redisServerPort);
        return new JedisConnectionFactory(jedisShardInfo);
    }

}
