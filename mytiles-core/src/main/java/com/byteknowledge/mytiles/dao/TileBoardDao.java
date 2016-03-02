package com.byteknowledge.mytiles.dao;

import java.util.Set;
import java.util.UUID;

import com.byteknowledge.mytiles.model.TileBoard;

public interface TileBoardDao extends Dao<TileBoard> {

	public Set<TileBoard> getTileBoardsByCreator(final UUID creatorId);
	
	public Set<TileBoard> getTileBoardsByOwner(final UUID ownerId);
	
	public Set<TileBoard> getTileBoardsByParticipant(final UUID ownerId);
	
	public Set<TileBoard> getTileBoardsByOwnerOrParticipant(final UUID userId);
	
}
