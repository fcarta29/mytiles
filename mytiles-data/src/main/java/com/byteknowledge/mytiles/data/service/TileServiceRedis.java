package com.byteknowledge.mytiles.data.service;

import com.byteknowledge.mytiles.dao.TileDao;
import com.byteknowledge.mytiles.dto.TileDto;
import com.byteknowledge.mytiles.model.Tile;
import com.byteknowledge.mytiles.service.TileService;

public class TileServiceRedis extends AbstractServiceRedis<TileDto,TileDao,Tile> implements TileService {

    @Override
    public TileDto entityToDto(Tile entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Tile dtoToEntity(TileDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

}
