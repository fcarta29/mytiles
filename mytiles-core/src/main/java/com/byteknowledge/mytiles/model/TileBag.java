package com.byteknowledge.mytiles.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TileBag extends UUIDEntity implements Serializable {

    private static final long serialVersionUID = -3873513545261620269L;

    private UUID creatorId;
    private Long createdTime;
    private String name;
    private Set<Tile> tiles = new HashSet<Tile>();

    public UUID getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(UUID creatorId) {
        this.creatorId = creatorId;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(Set<Tile> tiles) {
        this.tiles = tiles;
    }
    
	public TileBag addTile(final Tile newTile) {
		tiles.add(newTile);
    	return this;
	}
	
	public TileBag removeTile(final Tile removeTile) {
    	tiles.remove(removeTile);
    	return this;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode());
        result = prime * result + ((creatorId == null) ? 0 : creatorId.hashCode());
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TileBag other = (TileBag) obj;
        if (createdTime == null) {
            if (other.createdTime != null)
                return false;
        } else if (!createdTime.equals(other.createdTime))
            return false;
        if (creatorId == null) {
            if (other.creatorId != null)
                return false;
        } else if (!creatorId.equals(other.creatorId))
            return false;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TileBag [id=" + getId() + ", creatorId=" + creatorId + ", createdTime=" + createdTime + ", name=" + name
                + ", tiles=" + tiles + "]";
    }

}
