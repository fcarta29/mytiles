package com.byteknowledge.mytiles.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.byteknowledge.mytiles.dao.TilePlacementDao;
import com.byteknowledge.mytiles.model.TilePlacement;

@Configuration
@Component("tilePlacementDao")
public class TilePlacementDaoRedis extends AbstractDaoRedis<TilePlacement> implements TilePlacementDao {

    private static final String OBJECT_KEY = "TilePlacement";
    
    @Bean(name="tilePlacementRedisTemplate")
    public RedisTemplate<String,TilePlacement> redisTemplate() {
        return getRedisTemplate();
    }    
    
    @Autowired
    @Qualifier("tilePlacementRedisTemplate")
    private RedisTemplate<String,TilePlacement> redisTemplate = new RedisTemplate<String,TilePlacement>();
    
    @Override
    public RedisTemplate<String,TilePlacement> getEntityRedisTemplate() {
        return redisTemplate;
    }
    
    @Override
    public String getObjectKey() {
        return OBJECT_KEY;
    }
    
    @Override
    public Class<TilePlacement> getObjectClass() {
    	return TilePlacement.class;
    }
    
}
