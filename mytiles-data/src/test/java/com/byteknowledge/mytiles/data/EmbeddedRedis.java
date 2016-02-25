package com.byteknowledge.mytiles.data;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import redis.embedded.RedisServer;

@Component
@PropertySource("classpath:/test-redis.properties")
public class EmbeddedRedis {

	@Value("${redis.port}") 
    private int redisPort;

    private RedisServer redisServer;

    @PostConstruct
    public void startRedis() throws Exception {
        redisServer = new RedisServer(redisPort);
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() throws Exception {
        redisServer.stop();
    }
}