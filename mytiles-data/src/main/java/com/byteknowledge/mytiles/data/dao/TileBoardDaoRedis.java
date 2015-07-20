package com.byteknowledge.mytiles.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.byteknowledge.mytiles.dao.TileBoardDao;
import com.byteknowledge.mytiles.model.TileBoard;

@Configuration
@Component("tileBoardDao")
public class TileBoardDaoRedis extends AbstractDaoRedis<TileBoard> implements TileBoardDao {

    private static final String OBJECT_KEY = "TileBoard";
    
    @Bean(name="tileBoardRedisTemplate")
    public RedisTemplate<String,TileBoard> redisTemplate() {
        RedisTemplate<String,TileBoard> redisTemplate = new RedisTemplate<String,TileBoard>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }    
    
    @Autowired
    @Qualifier("tileBoardRedisTemplate")
    private RedisTemplate<String,TileBoard> redisTemplate;
    
    @Override
    public RedisTemplate<String,TileBoard> getRedisTemplate() {
        return redisTemplate;
    }
    
    @Override
    public String getObjectKey() {
        return OBJECT_KEY;
    }
}