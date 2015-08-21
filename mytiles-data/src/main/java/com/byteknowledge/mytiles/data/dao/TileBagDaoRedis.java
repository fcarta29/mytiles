package com.byteknowledge.mytiles.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.byteknowledge.mytiles.dao.TileBagDao;
import com.byteknowledge.mytiles.model.TileBag;

@Configuration
@Component("tileBagDao")
public class TileBagDaoRedis extends AbstractDaoRedis<TileBag> implements TileBagDao {

    private static final String OBJECT_KEY = "TileBag";
    
    @Bean(name="tileBagRedisTemplate")
    public RedisTemplate<String,TileBag> redisTemplate() {
        return getRedisTemplate();
    }    
    
    @Autowired
    @Qualifier("tileBagRedisTemplate")
    private RedisTemplate<String,TileBag> redisTemplate = new RedisTemplate<String,TileBag>();
    
    @Override
    public RedisTemplate<String,TileBag> getEntityRedisTemplate() {
        return redisTemplate;
    }
    
    @Override
    public String getObjectKey() {
        return OBJECT_KEY;
    }

    @Override
    public Class<TileBag> getObjectClass() {
        return TileBag.class;
    }

}
