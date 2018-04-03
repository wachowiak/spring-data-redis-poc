package org.wachowiak.spring.cache.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
class CacheClientConfiguration extends CachingConfigurerSupport{

    private static final Logger LOG = LoggerFactory.getLogger(CacheClientConfiguration.class);

    @Value("${redis.server.host:localhost}")
    private String redisServerHost;

    @Value("${redis.server.port:6379}")
    private int redisServerPort;

    @Value("${redis.server.cache.expiry:3600}")
    private long cacheExpiration;

    @Bean
    RedisConnectionFactory redisConnectionFactory() {
        LOG.info("Initializing new Redis connection factory. Hostname: {}. Port: {}", redisServerHost, redisServerPort);
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName(redisServerHost);
        connectionFactory.setPort(redisServerPort);
        return connectionFactory;
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        LOG.info("Initializing Redis cache manager. Cache expiration: {} s", cacheExpiration);
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        template.afterPropertiesSet();
        RedisCacheManager cacheMgr = new RedisCacheManager(template);
        cacheMgr.setUsePrefix(true);
        cacheMgr.setDefaultExpiration(cacheExpiration);
        return cacheMgr;
    }

    @Bean
    JedisPool jedisPool() {
        return new JedisPool(new JedisPoolConfig(), redisServerHost, redisServerPort);
    }

}
