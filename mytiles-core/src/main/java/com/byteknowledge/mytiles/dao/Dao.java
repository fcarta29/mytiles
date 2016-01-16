package com.byteknowledge.mytiles.dao;

import java.util.List;
import java.util.UUID;

import com.byteknowledge.mytiles.model.UUIDEntity;

public interface Dao<E extends UUIDEntity> {
    
    public E get(final UUID id);
    
    public List<E> list();
    
    public void save(final E entity);
}
