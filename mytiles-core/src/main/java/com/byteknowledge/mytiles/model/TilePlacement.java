package com.byteknowledge.mytiles.model;

import java.io.Serializable;
import java.util.UUID;

public class TilePlacement extends UUIDEntity implements Serializable  {

    private static final long serialVersionUID = -3848404695700941011L;

    private UUID tileId;
    private String lastMovedById;
    private Long lastUpdatedTime;

    // TODO[fcarta] make these a dimension class or something
    private Long x;
    private Long y;
    private Long z;

    @Override
    public UUID getId() {
        return getTileId();
    }
    
    @Override
    public void setId(UUID id) {
        setTileId(id);
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
        int result = 1;
        result = prime * result + ((tileId == null) ? 0 : tileId.hashCode());
        result = prime * result + ((lastUpdatedTime == null) ? 0 : lastUpdatedTime.hashCode());
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
        TilePlacement other = (TilePlacement) obj;
        if (tileId == null) {
            if (other.tileId != null)
                return false;
        } else if (!tileId.equals(other.tileId))
            return false;
        if (lastUpdatedTime == null) {
            if (other.lastUpdatedTime != null)
                return false;
        } else if (!lastUpdatedTime.equals(other.lastUpdatedTime))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TilePlacement [tileId=" + tileId + "s, lastMovedById=" + lastMovedById + ", lastUpdatedTime="
                + lastUpdatedTime + ", x=" + x + ", y=" + y + ", z=" + z + "]";
    }
}
