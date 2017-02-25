package com.byteknowledge.mytiles.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.byteknowledge.mytiles.dao.Dao;
import com.byteknowledge.mytiles.dao.TileDao;
import com.byteknowledge.mytiles.model.Tile;

@RestController
@RequestMapping("/tiles")
public class TileRestController extends AbstractRestController<Tile> {
	
	@Autowired
	private TileDao tileDao;

	@Override
	protected Dao<Tile> getDao() {
		return tileDao;
	}

	@Override
	protected void merge(final Tile persitedTile, final Tile tile) {
		persitedTile.setLabel(tile.getLabel());
	}

}
