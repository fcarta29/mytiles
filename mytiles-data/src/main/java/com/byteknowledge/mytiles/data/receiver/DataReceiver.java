package com.byteknowledge.mytiles.data.receiver;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.byteknowledge.mytiles.dao.TilePlacementDao;
import com.byteknowledge.mytiles.dto.TileMovement;
import com.byteknowledge.mytiles.model.TilePlacement;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReceiver {
    
    private static final Logger LOGGER = Logger.getLogger(DataReceiver.class);
    
	@Autowired
	private TilePlacementDao tilePlacementDao;
    
	private ObjectMapper mapper = new ObjectMapper();
	
    private CountDownLatch latch;

    @Autowired
    public DataReceiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message) {
        LOGGER.info("Received <" + message + ">");
        
        try {
	        final TileMovement tileMovement = mapper.readValue(message, TileMovement.class);
	        
	        final TilePlacement tilePlacement = new TilePlacement();
	        tilePlacement.setTileId(UUID.fromString(tileMovement.getTileId()));
	        tilePlacement.setLastMovedById(tileMovement.getTileUserId());
	        tilePlacement.setLastUpdatedTime(tileMovement.getTileTimestamp());
	        tilePlacement.setX(tileMovement.getTileX());
	        tilePlacement.setY(tileMovement.getTileY());
	        tilePlacement.setZ(tileMovement.getTileZ());
	        
	        tilePlacementDao.save(tilePlacement);
        } catch (Exception ex) {
        	LOGGER.error("Failed to update tile placement ", ex);
        }
	        
        latch.countDown();
    }
}
