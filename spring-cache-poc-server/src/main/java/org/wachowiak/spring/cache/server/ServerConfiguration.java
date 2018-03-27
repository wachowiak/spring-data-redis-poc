package org.wachowiak.spring.cache.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import redis.embedded.RedisServer;

import java.io.IOException;

@Configuration
class ServerConfiguration {

    @Value("${redis.server.port:6379}")
    private int redisServerPort;

    @Bean
    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(destroyMethod = "stop")
    RedisServer redisServer() throws IOException {
        RedisServer redisServer = RedisServer
                .builder()
                .port(redisServerPort)
                .build();
        redisServer.start();
        return redisServer;
    }

}
