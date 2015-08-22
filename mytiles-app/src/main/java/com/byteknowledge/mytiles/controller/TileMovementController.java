package com.byteknowledge.mytiles.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.byteknowledge.mytiles.dto.TileMovement;

@Controller
public class TileMovementController {
    
    //private static final Object TILE_LOCK = new Object();
    //private static final ConcurrentHashMap<UUID,TileLock> TILE_MAP = new ConcurrentHashMap<UUID,TileLock>();
    
    private final static Logger LOG = Logger.getLogger(TileMovementController.class);
    
    @Autowired
    protected JedisConnectionFactory jedisConnectionFactory;
    
/*    @MessageMapping("/tile/lock")
    @SendTo(value={"/topic/tileLock"})
    public TileLock lockTile(final TileLock tileLock) {
        synchronized(TILE_LOCK) {
            if (tileLock != null) {
                final UUID tileLockId = UUID.fromString(tileLock.getTileId());
                if (TILE_MAP.contains(tileLockId)) {
                    return TILE_MAP.get(tileLockId);
                }
                TILE_MAP.put(tileLockId, tileLock);
                return tileLock;
            }
        }
        return null; // TODO[fcarta] need to see if nulls are actually sent, if so then we need to not use @SendTo?
    }
    
    @MessageMapping("/tile/release")
    public TileLock releaseTile(final TileLock tileLock) {
        if (tileLock != null) {
            return removeTileLock(UUID.fromString(tileLock.getTileId()));
        } 
        return null; // TODO[fcarta] need to see if nulls are actually sent, if so then we need to not use @SendTo?
    }    
    
    @SendTo(value={"/topic/tileRelease"})
    private TileLock removeTileLock(final UUID tileLockId) {
        TileLock tileLock = null;
        synchronized(TILE_LOCK) {
            if (TILE_MAP.contains(tileLockId)) {
                tileLock = TILE_MAP.get(tileLockId);
            }            
            TILE_MAP.remove(tileLockId);
        }
        return tileLock;
    }
*/
    
    protected RedisTemplate<String,TileMovement> getRedisTemplate() {
        final RedisTemplate<String,TileMovement> redisTemplate = new RedisTemplate<String,TileMovement>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<TileMovement>(TileMovement.class));
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<TileMovement>(TileMovement.class));       
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }    
    
    @MessageMapping("/tile/move")
    @SendTo(value={"/topic/tileUpdate"})
    public TileMovement moveTile(final TileMovement tileMovement) {
        if (tileMovement != null && hasPermissionToMove(tileMovement)) {
            LOG.debug(tileMovement);
            if (tileMovement != null) {
                //removeTileLock(UUID.fromString(tileMovement.getTileId()));
                getRedisTemplate().convertAndSend("data", tileMovement);
                return tileMovement;
            }
        }
        return null; // TODO[fcarta] need to see if nulls are actually sent, if so then we need to not use @SendTo?
    }

    public boolean hasPermissionToMove(final TileMovement tileMovement) {
        return Boolean.TRUE;
        /*synchronized(TILE_LOCK) {
            final UUID tileLockId = UUID.fromString(tileMovement.getTileId());
            if (TILE_MAP.contains(tileLockId)) {
                final TileLock tileLock = TILE_MAP.get(tileLockId);
                // check the user id strings 
                return StringUtils.equals(tileLock.getTileUserId(), tileMovement.getTileUserId());
            } else {
                
            }
        }*/
        //return Boolean.FALSE;
    }
}
