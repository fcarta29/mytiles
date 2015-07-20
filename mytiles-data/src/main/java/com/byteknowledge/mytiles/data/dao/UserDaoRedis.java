package com.byteknowledge.mytiles.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.byteknowledge.mytiles.dao.UserDao;
import com.byteknowledge.mytiles.model.User;

@Configuration
@Component("userDao")
public class UserDaoRedis extends AbstractDaoRedis<User> implements UserDao { 
    
    private static final String OBJECT_KEY = "User";
    
    @Bean(name="userRedisTemplate")
    public RedisTemplate<String,User> redisTemplate() {
        RedisTemplate<String,User> redisTemplate = new RedisTemplate<String,User>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }    
    
    @Autowired
    @Qualifier("userRedisTemplate")
    private RedisTemplate<String,User> redisTemplate;
    
    @Override
    public RedisTemplate<String,User> getRedisTemplate() {
        return redisTemplate;
    }
    
    @Override
    public String getObjectKey() {
        return OBJECT_KEY;
    }
}
