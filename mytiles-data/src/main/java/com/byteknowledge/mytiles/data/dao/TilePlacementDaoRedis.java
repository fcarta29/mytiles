package com.byteknowledge.mytiles.data.dao;

import java.text.MessageFormat;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import com.byteknowledge.mytiles.dao.TilePlacementDao;
import com.byteknowledge.mytiles.model.TilePlacement;

@Repository("tilePlacementDao")
public class TilePlacementDaoRedis implements TilePlacementDao {

    private static final String TILEPLACEMENTS_FOR_TILEBOARD_KEY = "TilePlacement:TileBoard:{0}:TileBag:{1}";
    
    @Autowired
    protected JedisConnectionFactory jedisConnectionFactory;
    
    protected RedisTemplate<String,TilePlacement> getRedisTemplate() {
        final RedisTemplate<String,TilePlacement> redisTemplate = new RedisTemplate<String,TilePlacement>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<TilePlacement>(TilePlacement.class));
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<TilePlacement>(TilePlacement.class)); 
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

	@Override
	public Set<TilePlacement> get(final UUID tileBoardId, final UUID tileBagId) {
		return getRedisTemplate().opsForSet().members(
				MessageFormat.format(TILEPLACEMENTS_FOR_TILEBOARD_KEY, tileBoardId, tileBagId));
	}

	@Override
	public void save(final TilePlacement tilePlacement) {
    	getRedisTemplate().opsForSet().add(
    			MessageFormat.format(TILEPLACEMENTS_FOR_TILEBOARD_KEY, tilePlacement.getTileBoardId(), 
    					tilePlacement.getTileBagId()), tilePlacement);
    }	

	@Override
	public void clearAll(final UUID tileBoardId, final UUID tileBagId) {
		getRedisTemplate().delete(MessageFormat.format(TILEPLACEMENTS_FOR_TILEBOARD_KEY, tileBoardId, tileBagId));
	}    
}
