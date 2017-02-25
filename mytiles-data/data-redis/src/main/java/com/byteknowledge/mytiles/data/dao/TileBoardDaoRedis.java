package com.byteknowledge.mytiles.data.dao;

import java.text.MessageFormat;
import java.util.Set;
import java.util.UUID;

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
    private static final String TILEBOARD_CREATOR_INDEX_KEY = "TileBoard:Creator:{0}";
    private static final String TILEBOARD_OWNER_INDEX_KEY = "TileBoard:Owner:{0}";
    private static final String TILEBOARD_PARTICIPANT_INDEX_KEY = "TileBoard:Participant:{0}";
    
    @Bean(name="tileBoardRedisTemplate")
    public RedisTemplate<String,TileBoard> redisTemplate() {
        return initRedisTemplate();
    }    
    
    @Autowired
    @Qualifier("tileBoardRedisTemplate")
    private RedisTemplate<String,TileBoard> redisTemplate = new RedisTemplate<String,TileBoard>();
    
    @Override
    public String getObjectKey() {
        return OBJECT_KEY;
    }
    
    @Override
    public RedisTemplate<String,TileBoard> getRedisTemplate() {
    	return redisTemplate;
    }    
    
    protected void setIndexes(final TileBoard tileBoard) {
    	// Override to set custom keys for lookup
    	initRedisTemplate().opsForSet().add(MessageFormat.format(TILEBOARD_CREATOR_INDEX_KEY,tileBoard.getCreatorId()), 
    			tileBoard);
    	initRedisTemplate().opsForSet().add(MessageFormat.format(TILEBOARD_OWNER_INDEX_KEY,tileBoard.getOwnerId()), 
    			tileBoard);
    	for (final UUID participant : tileBoard.getParticipantIds()) {
    		initRedisTemplate().opsForSet().add(MessageFormat.format(TILEBOARD_PARTICIPANT_INDEX_KEY,participant), 
    				tileBoard);
    	}
    }

	public Set<TileBoard> getTileBoardsByCreator(final UUID creatorId) {
		return initRedisTemplate().opsForSet().members(MessageFormat.format(TILEBOARD_CREATOR_INDEX_KEY,creatorId));
	}    
    
	public Set<TileBoard> getTileBoardsByOwner(final UUID ownerId) {
		return initRedisTemplate().opsForSet().members(MessageFormat.format(TILEBOARD_OWNER_INDEX_KEY,ownerId));
	}
	
	public Set<TileBoard> getTileBoardsByParticipant(final UUID participantId) {
		return initRedisTemplate().opsForSet().members(
				MessageFormat.format(TILEBOARD_PARTICIPANT_INDEX_KEY,participantId));
	}
	
	public Set<TileBoard> getTileBoardsByOwnerOrParticipant(final UUID userId) {
		return initRedisTemplate().opsForSet().union(MessageFormat.format(TILEBOARD_OWNER_INDEX_KEY,userId), 
				MessageFormat.format(TILEBOARD_PARTICIPANT_INDEX_KEY,userId));
	}
}
