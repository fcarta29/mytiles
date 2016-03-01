package com.byteknowledge.mytiles.model;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class TilePlacement implements Serializable {

    private static final long serialVersionUID = -3848404695700941011L;

    private UUID tileBoardId;
    private UUID tileBagId;
    private UUID tileId;
    private String lastMovedById = StringUtils.EMPTY;
    private Long lastUpdatedTime = Long.valueOf(0);

    // TODO[fcarta] make these a dimension class or something
    private Long x = Long.valueOf(0);
    private Long y = Long.valueOf(0);
    private Long z = Long.valueOf(0);
    
    public UUID getTileBoardId() {
		return tileBoardId;
	}

	public void setTileBoardId(UUID tileBoardId) {
		this.tileBoardId = tileBoardId;
	}

	public UUID getTileBagId() {
		return tileBagId;
	}

	public void setTileBagId(UUID tileBagId) {
		this.tileBagId = tileBagId;
	}
    
    public UUID getTileId() {
        return tileId;
    }

    public void setTileId(UUID id) {
        this.tileId = id;
    }	
	
	public String getLastMovedById() {
        return lastMovedById;
    }

    public void setLastMovedById(String lastMovedById) {
        this.lastMovedById = lastMovedById;
    }

    public Long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Long lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }    
    
    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public Long getZ() {
        return z;
    }

    public void setZ(Long z) {
        this.z = z;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((tileBagId == null) ? 0 : tileBagId.hashCode());
		result = prime * result + ((tileBoardId == null) ? 0 : tileBoardId.hashCode());
		result = prime * result + ((tileId == null) ? 0 : tileId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof TilePlacement)) {
			return false;
		}
		TilePlacement other = (TilePlacement) obj;
		if (tileBagId == null) {
			if (other.tileBagId != null) {
				return false;
			}
		} else if (!tileBagId.equals(other.tileBagId)) {
			return false;
		}
		if (tileBoardId == null) {
			if (other.tileBoardId != null) {
				return false;
			}
		} else if (!tileBoardId.equals(other.tileBoardId)) {
			return false;
		}
		if (tileId == null) {
			if (other.tileId != null) {
				return false;
			}
		} else if (!tileId.equals(other.tileId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TilePlacement [tileBoardId=" + tileBoardId + ", tileBagId=" + tileBagId + ", tileId=" + tileId
				+ ", lastMovedById=" + lastMovedById + ", lastUpdatedTime=" + lastUpdatedTime + ", x=" + x + ", y=" + y
				+ ", z=" + z + "]";
	}
	
}
