package com.byteknowledge.mytiles.dao;

import java.util.List;
import java.util.UUID;

import com.byteknowledge.mytiles.model.TilePlacement;

public interface TilePlacementDao {

	public List<TilePlacement> list(final UUID tileBoardId, final UUID tileBagId);

	public void save(final TilePlacement tilePlacement);

	public void clearAll(final UUID tileBoardId, final UUID tileBagId);
}
