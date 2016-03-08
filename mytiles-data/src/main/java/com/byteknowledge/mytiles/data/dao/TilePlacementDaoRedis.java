package com.byteknowledge.mytiles.data.dao;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import com.byteknowledge.mytiles.dao.TilePlacementDao;
import com.byteknowledge.mytiles.model.TilePlacement;

@Configuration
@Repository("tilePlacementDao")
public class TilePlacementDaoRedis implements TilePlacementDao {

    private static final String TILEPLACEMENTS_FOR_TILEBOARD_KEY = "TilePlacement:TileBoard:{0}:TileBag:{1}";
    private static final String TILEPLACEMENT_FOR_TILEBOARD_KEY = "Tile{0}";
    
    private static final Logger LOG = Logger.getLogger(TilePlacementDaoRedis.class);
    
    @Autowired
    protected JedisConnectionFactory jedisConnectionFactory;
    
    @Autowired
    @Qualifier("tilePlacementRedisTemplate")
    private RedisTemplate<String,TilePlacement> redisTemplate = new RedisTemplate<String,TilePlacement>();
    
    @Bean(name="tilePlacementRedisTemplate")
    protected RedisTemplate<String,TilePlacement> getRedisTemplate() {
        final RedisTemplate<String,TilePlacement> redisTemplate = new RedisTemplate<String,TilePlacement>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        // NOTE[fcarta] - TX seem to perform really poorly disabling for now        
        //redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<TilePlacement>(TilePlacement.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

	@Override
	public List<TilePlacement> list(final UUID tileBoardId, final UUID tileBagId) {
		LOG.info("Getting : " + tileBoardId + ", " + tileBagId);
		return (List<TilePlacement>) (List<?>) redisTemplate.opsForHash().values(
				MessageFormat.format(TILEPLACEMENTS_FOR_TILEBOARD_KEY, tileBoardId, tileBagId));
	}

	@Override
	public void save(final TilePlacement tilePlacement) {
		LOG.info("Saving : " + tilePlacement + "," + 
				MessageFormat.format(TILEPLACEMENTS_FOR_TILEBOARD_KEY, tilePlacement.getTileBoardId(), 
						tilePlacement.getTileBagId()));
		redisTemplate.opsForHash().put(
				MessageFormat.format(TILEPLACEMENTS_FOR_TILEBOARD_KEY, tilePlacement.getTileBoardId(), 
						tilePlacement.getTileBagId()), 
				MessageFormat.format(TILEPLACEMENT_FOR_TILEBOARD_KEY, tilePlacement.getTileId()), tilePlacement);
    	LOG.info("Saved : " + tilePlacement);
    }	

	@Override
	public void clearAll(final UUID tileBoardId, final UUID tileBagId) {
		redisTemplate.delete(MessageFormat.format(TILEPLACEMENTS_FOR_TILEBOARD_KEY, tileBoardId, tileBagId));
	}    
}
