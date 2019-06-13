package com.byteknowledge.mytiles.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.byteknowledge.mytiles.dao.Dao;
import com.byteknowledge.mytiles.dao.TileBagDao;
import com.byteknowledge.mytiles.model.TileBag;

@RestController
@RequestMapping("/tilebags")
public class TileBagRestController extends AbstractRestController<TileBag> {
	
	@Autowired
	private TileBagDao tileBagDao;

	@Override
	protected Dao<TileBag> getDao() {
		return tileBagDao;
	}

	@Override
	protected void merge(final TileBag persitedTileBag, final TileBag tileBag) {
		persitedTileBag.setName(tileBag.getName());
		persitedTileBag.setTiles(tileBag.getTiles());
	}

}
