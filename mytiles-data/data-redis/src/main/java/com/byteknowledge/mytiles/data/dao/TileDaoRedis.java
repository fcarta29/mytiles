package com.byteknowledge.mytiles.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.byteknowledge.mytiles.dao.TileDao;
import com.byteknowledge.mytiles.model.Tile;

@Repository("tileDao")
public class TileDaoRedis extends AbstractDaoRedis<Tile> implements TileDao {

    private static final String OBJECT_KEY = "Tile";
    
    @Bean(name="tileRedisTemplate")
    public RedisTemplate<String,Tile> redisTemplate() {
        return initRedisTemplate();
    }    
    
    @Autowired
    @Qualifier("tileRedisTemplate")
    private RedisTemplate<String,Tile> redisTemplate;
    
    @Override
    public String getObjectKey() {
        return OBJECT_KEY;
    }
    
    @Override
    public RedisTemplate<String,Tile> getRedisTemplate() {
    	return redisTemplate;
    }    
}
