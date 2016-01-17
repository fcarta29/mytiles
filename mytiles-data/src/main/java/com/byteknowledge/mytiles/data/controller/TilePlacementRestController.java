package com.byteknowledge.mytiles.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.byteknowledge.mytiles.dao.Dao;
import com.byteknowledge.mytiles.dao.TilePlacementDao;
import com.byteknowledge.mytiles.model.TilePlacement;

@RestController
@RequestMapping("/tileplacements")
public class TilePlacementRestController extends AbstractRestController<TilePlacement> {
	
	@Autowired
	private TilePlacementDao tilePlacementDao;

	@Override
	protected Dao<TilePlacement> getDao() {
		return tilePlacementDao;
	}

	@Override
	protected void merge(final TilePlacement persitedTilePlacement, final TilePlacement tilePlacement) {
		persitedTilePlacement.setX(tilePlacement.getX());
		persitedTilePlacement.setY(tilePlacement.getY());
		persitedTilePlacement.setZ(tilePlacement.getZ());
	}

}
