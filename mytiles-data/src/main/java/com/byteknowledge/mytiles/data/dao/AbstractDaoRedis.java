package com.byteknowledge.mytiles.data.dao;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.byteknowledge.mytiles.dao.Dao;
import com.byteknowledge.mytiles.model.UUIDEntity;

public abstract class AbstractDaoRedis<E extends UUIDEntity> implements Dao<E> {

    private final static Logger LOG = Logger.getLogger(AbstractDaoRedis.class);
    
    @Autowired
    protected JedisConnectionFactory jedisConnectionFactory;
    
    abstract public RedisTemplate<String,E> getEntityRedisTemplate();
    
    protected RedisTemplate<String,E> getRedisTemplate() {
    	final RedisTemplate<String,E> redisTemplate = getEntityRedisTemplate();
    	redisTemplate.setConnectionFactory(jedisConnectionFactory);
    	redisTemplate.setKeySerializer(new StringRedisSerializer());
    	redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<E>(getObjectClass()));
    	redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<E>(getObjectClass()));       
    	return redisTemplate;
    }
    
    abstract public String getObjectKey();
    
    abstract public Class<E> getObjectClass();
    
    @SuppressWarnings("unchecked")
    @Override
    public E get(final UUID id) {
        return (E) getRedisTemplate().opsForHash().get(getObjectKey(), id.toString());
    }

    public void save(final E entity) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
        getRedisTemplate().opsForHash().put(getObjectKey(), entity.getId().toString(), entity);
    }
}
