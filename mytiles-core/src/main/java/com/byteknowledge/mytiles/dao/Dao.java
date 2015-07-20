package com.byteknowledge.mytiles.dao;

import java.util.UUID;

import com.byteknowledge.mytiles.model.UUIDEntity;

public interface Dao<E extends UUIDEntity> {
    
    public E get(final UUID id);
    
    public void save(final E entity);
}
