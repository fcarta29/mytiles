package com.byteknowledge.mytiles.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.byteknowledge.mytiles.dao.TileBoardDao;
import com.byteknowledge.mytiles.model.TileBoard;

@Repository("tileBoardDao")
public class TileBoardDaoRedis extends AbstractDaoRedis<TileBoard> implements TileBoardDao {

    private static final String OBJECT_KEY = "TileBoard";
    
    @Bean(name="tileBoardRedisTemplate")
    public RedisTemplate<String,TileBoard> redisTemplate() {
        return getRedisTemplate();
    }    
    
    @Autowired
    @Qualifier("tileBoardRedisTemplate")
    private RedisTemplate<String,TileBoard> redisTemplate = new RedisTemplate<String,TileBoard>();
    
    @Override
    public String getObjectKey() {
        return OBJECT_KEY;
    }
    
}
