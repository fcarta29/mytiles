package com.byteknowledge.mytiles.data.service;

import com.byteknowledge.mytiles.dao.Dao;
import com.byteknowledge.mytiles.dto.UUIDDto;
import com.byteknowledge.mytiles.model.AbstractUUIDEntity;
import com.byteknowledge.mytiles.service.Service;

public abstract class AbstractServiceRedis<T extends UUIDDto, D extends Dao<E>, E extends AbstractUUIDEntity> 
        implements Service<T,D,E> {

}
