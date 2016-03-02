package com.byteknowledge.mytiles.dao;

import java.util.Set;
import java.util.UUID;

import com.byteknowledge.mytiles.model.TilePlacement;

public interface TilePlacementDao {

	public Set<TilePlacement> get(final UUID tileBoardId, final UUID tileBagId);

	public void save(final TilePlacement tilePlacement);

	public void clearAll(final UUID tileBoardId, final UUID tileBagId);
}
