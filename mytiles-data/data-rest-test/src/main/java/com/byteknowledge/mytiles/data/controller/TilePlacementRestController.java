package com.byteknowledge.mytiles.data.controller;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.byteknowledge.mytiles.dao.TilePlacementDao;
import com.byteknowledge.mytiles.model.TilePlacement;

@RestController
@CrossOrigin
@RequestMapping("/tileplacements")
public class TilePlacementRestController {
	
	@Autowired
	private TilePlacementDao tilePlacementDao;

    @RequestMapping(value = "/tileboard/{tileBoardId}/tilebag/{tileBagId}", method = RequestMethod.GET, 
    		produces = "application/json")
    public @ResponseBody List<TilePlacement> get(@PathVariable("tileBoardId") final UUID tileBoardId,
    		@PathVariable("tileBagId") final UUID tileBagId) {
    	return tilePlacementDao.list(tileBoardId, tileBagId);
    }
    
    @RequestMapping(value = "/tileboard/{tileBoardId}/tilebag/{tileBagId}", method = RequestMethod.POST, 
    		consumes = "application/json")
    public void save(@PathVariable("tileBoardId") final UUID tileBoardId,
    		@PathVariable("tileBagId") final UUID tileBagId, final @RequestBody TilePlacement tilePlacement) {
    	tilePlacement.setTileBoardId(tileBoardId);
    	tilePlacement.setTileBagId(tileBagId);
        tilePlacement.setLastUpdatedTime(Calendar.getInstance().getTimeInMillis());
    	
    	tilePlacementDao.save(tilePlacement);
    }
    
    @RequestMapping(value = "/tileboard/{tileBoardId}/tilebag/{tileBagId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("tileBoardId") final UUID tileBoardId, 
    		@PathVariable("tileBagId") final UUID tileBagId) {
    	tilePlacementDao.clearAll(tileBoardId, tileBagId);
    }
}
