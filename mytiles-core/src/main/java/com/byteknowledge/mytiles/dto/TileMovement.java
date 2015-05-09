package com.byteknowledge.mytiles.dto;

import java.io.Serializable;

public class TileMovement implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tileId;
    private String tileUserId;
    private Long tileTimestamp;

    // TODO[fcarta] make this into a dimension class or something
    private Long tileX;
    private Long tileY;
    private Long tileZ;

    public String getTileId() {
        return tileId;
    }

    public void setTileId(String tileId) {
        this.tileId = tileId;
    }

    public String getTileUserId() {
        return tileUserId;
    }

    public void setTileUserId(String tileUserId) {
        this.tileUserId = tileUserId;
    }
    
    public Long getTileTimestamp() {
        return tileTimestamp;
    }

    public void setTileTimestamp(Long tileTimestamp) {
        this.tileTimestamp = tileTimestamp;
    }

    public Long getTileX() {
        return tileX;
    }

    public void setTileX(Long tileX) {
        this.tileX = tileX;
    }

    public Long getTileY() {
        return tileY;
    }

    public void setTileY(Long tileY) {
        this.tileY = tileY;
    }

    public Long getTileZ() {
        return tileZ;
    }

    public void setTileZ(Long tileZ) {
        this.tileZ = tileZ;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tileId == null) ? 0 : tileId.hashCode());
        result = prime * result + ((tileTimestamp == null) ? 0 : tileTimestamp.hashCode());
        result = prime * result + ((tileUserId == null) ? 0 : tileUserId.hashCode());
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
        TileMovement other = (TileMovement) obj;
        if (tileId == null) {
            if (other.tileId != null)
                return false;
        } else if (!tileId.equals(other.tileId))
            return false;
        if (tileTimestamp == null) {
            if (other.tileTimestamp != null)
                return false;
        } else if (!tileTimestamp.equals(other.tileTimestamp))
            return false;
        if (tileUserId == null) {
            if (other.tileUserId != null)
                return false;
        } else if (!tileUserId.equals(other.tileUserId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TileMovement [tileId=" + tileId + ", tileUserId=" + tileUserId + ", tileTimestamp=" + tileTimestamp
                + ", tileX=" + tileX + ", tileY=" + tileY + ", tileZ=" + tileZ + "]";
    }
}
