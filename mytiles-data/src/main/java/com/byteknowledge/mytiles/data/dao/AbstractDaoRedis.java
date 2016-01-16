package com.byteknowledge.mytiles.data.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.byteknowledge.mytiles.dao.Dao;
import com.byteknowledge.mytiles.model.UUIDEntity;

@Configuration
public abstract class AbstractDaoRedis<E extends UUIDEntity> implements Dao<E> {

    private final static Logger LOG = Logger.getLogger(AbstractDaoRedis.class);
    
    @Autowired
    protected JedisConnectionFactory jedisConnectionFactory;
    
    private Class<E> typeOfEntity;
    
    @SuppressWarnings("unchecked")
	public AbstractDaoRedis() {
    	this.typeOfEntity = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    public abstract String getObjectKey();
    
    protected RedisTemplate<String,E> getRedisTemplate() {
        final RedisTemplate<String,E> redisTemplate = new RedisTemplate<String,E>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<E>(typeOfEntity));
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<E>(typeOfEntity));       
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @SuppressWarnings("unchecked")
    public E get(final UUID id) {
        return (E) getRedisTemplate().opsForHash().get(getObjectKey(), id.toString());
    }
    
    @SuppressWarnings("unchecked")
	public List<E> list() {
    	return (List<E>) (List<?>) getRedisTemplate().opsForHash().values(getObjectKey());
    }

    public void save(final E entity) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
        getRedisTemplate().opsForHash().put(getObjectKey(), entity.getId().toString(), entity);
    }
}
