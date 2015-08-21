package com.byteknowledge.mytiles.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.byteknowledge.mytiles.dao.TileDao;
import com.byteknowledge.mytiles.model.Tile;

@Configuration
@Component("tileDao")
public class TileDaoRedis extends AbstractDaoRedis<Tile> implements TileDao {

    private static final String OBJECT_KEY = "Tile";
    
    @Bean(name="tileRedisTemplate")
    public RedisTemplate<String,Tile> redisTemplate() {
        return getRedisTemplate();
    }    
    
    @Autowired
    @Qualifier("tileRedisTemplate")
    private RedisTemplate<String,Tile> redisTemplate = new RedisTemplate<String,Tile>();
    
    @Override
    public RedisTemplate<String,Tile> getEntityRedisTemplate() {
        return redisTemplate;
    }
    
    @Override
    public String getObjectKey() {
        return OBJECT_KEY;
    }
    
    @Override
    public Class<Tile> getObjectClass() {
    	return Tile.class;
    }
}
