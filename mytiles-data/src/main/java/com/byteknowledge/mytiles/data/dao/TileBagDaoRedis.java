package com.byteknowledge.mytiles.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.byteknowledge.mytiles.dao.TileBagDao;
import com.byteknowledge.mytiles.model.TileBag;

@Repository("tileBagDao")
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
    public String getObjectKey() {
        return OBJECT_KEY;
    }

}
