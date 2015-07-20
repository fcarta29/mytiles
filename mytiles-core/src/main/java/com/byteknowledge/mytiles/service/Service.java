package com.byteknowledge.mytiles.service;

import com.byteknowledge.mytiles.dao.Dao;
import com.byteknowledge.mytiles.dto.UUIDDto;
import com.byteknowledge.mytiles.model.UUIDEntity;

public interface Service<T extends UUIDDto, D extends Dao<E>, E extends UUIDEntity> {

    public static final String USER1_ID = "0ac0d6d4-f761-4481-b2a7-03770da268f6";
    public static final String USER2_ID = "7dc8368a-8d7d-426a-8009-9e040211c106";
    public static final String TILE1_ID = "8863c47b-a7e0-4f42-8689-3432106f15cc";
    public static final String TILE2_ID = "db442fda-cf92-4787-a776-74cac38800be";
    public static final String TILEBAG_ID = "7194d3ea-b2be-4e1f-b606-8d5867bb5a34";
    public static final String TILEBOARD_ID = "7270496-d622-4351-a4df-d4f28b31fe5c";
    
    public T entityToDto(final E entity);
    
    public E dtoToEntity(final T dto);
}
