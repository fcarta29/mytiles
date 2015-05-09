package com.byteknowledge.mytiles.controller;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.byteknowledge.mytiles.dto.TileMovement;
import com.byteknowledge.mytiles.model.Tile;

@Controller
public class TileMovementController {
    
    private static final Object TILE_LOCK = new Object();
    private static final ConcurrentHashMap<String,Tile> TILE_MAP = new ConcurrentHashMap<String,Tile>();
    
    private final static Logger LOG = Logger.getLogger(TileMovementController.class);
    
    @MessageMapping("/tile/move")
    @SendTo("/topic/tileUpdate") //TODO[fcarta] add push to tile persist channel also
    public TileMovement moveTile(final TileMovement tileMovement) {
        LOG.debug(tileMovement);
        final Tile tile = movementToTile(tileMovement);
        if (tile != null) {
            synchronized(TILE_LOCK) {
                TILE_MAP.put(tile.getId(), tile);
            }
            return tileMovement;
        }
        return null;
    }
    
    private Tile movementToTile(final TileMovement tileMovement) {
        Tile tile = null;
        if (TILE_MAP.contains(tileMovement.getTileId())) {
            tile = TILE_MAP.get(tileMovement.getTileId());
        } else {
            tile = new Tile();
            tile.setId(tileMovement.getTileId());
        }
        tile.setLastMovedById(tileMovement.getTileUserId());
        tile.setLastUpdatedTime(tileMovement.getTileTimestamp());
        tile.setX(tileMovement.getTileX());
        tile.setY(tileMovement.getTileY());
        tile.setZ(tileMovement.getTileZ());
        return tile;
    }
}
