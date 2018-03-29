package org.wachowiak.spring.cache.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import redis.embedded.RedisServer;

@Configuration
class ServerConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(ServerConfiguration.class);

    @Value("${redis.server.port:6379}")
    private int redisServerPort;

    @Bean
    static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(destroyMethod = "stop")
    RedisServer redisServer() {
        LOG.info("Initializing Redis server on port {}", redisServerPort);
        RedisServer redisServer = RedisServer
                .builder()
                .port(redisServerPort)
                .build();
        redisServer.start();
        LOG.info("Redis server initialized");
        return redisServer;
    }

}
