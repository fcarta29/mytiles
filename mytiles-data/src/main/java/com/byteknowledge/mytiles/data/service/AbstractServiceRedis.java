package com.byteknowledge.mytiles.data.service;

import com.byteknowledge.mytiles.dao.Dao;
import com.byteknowledge.mytiles.dto.UUIDDto;
import com.byteknowledge.mytiles.model.UUIDEntity;
import com.byteknowledge.mytiles.service.Service;

public abstract class AbstractServiceRedis<T extends UUIDDto, D extends Dao<E>, E extends UUIDEntity> 
        implements Service<T,D,E> {

}
