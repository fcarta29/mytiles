package com.byteknowledge.mytiles.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
