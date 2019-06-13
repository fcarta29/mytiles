package com.byteknowledge.mytiles.data.controller;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.byteknowledge.mytiles.dao.Dao;
import com.byteknowledge.mytiles.dao.TileBoardDao;
import com.byteknowledge.mytiles.model.TileBoard;

@RestController
@RequestMapping("/tileboards")
public class TileBoardRestController extends AbstractRestController<TileBoard> {
	
	@Autowired
	private TileBoardDao tileBoardDao;	
	
	@Override
	protected Dao<TileBoard> getDao() {
		return tileBoardDao;
	}

	@Override
	protected void merge(final TileBoard persitedTileBoard, final TileBoard tileBoard) {
		persitedTileBoard.setName(tileBoard.getName());
		persitedTileBoard.setTileBagId(tileBoard.getTileBagId());
	}

    @RequestMapping(value = "/owner/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Set<TileBoard> getTileBoardsByOwner(@PathVariable("id") final UUID ownerId) {
    	return tileBoardDao.getTileBoardsByOwner(ownerId);
    }
    
    @RequestMapping(value = "/participant/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Set<TileBoard> getTileBoardsByParticipant(@PathVariable("id") final UUID participantId) {
    	return tileBoardDao.getTileBoardsByParticipant(participantId);
    }
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Set<TileBoard> getTileBoardsByUser(@PathVariable("id") final UUID userId) {
    	return tileBoardDao.getTileBoardsByOwnerOrParticipant(userId);
    }
}
