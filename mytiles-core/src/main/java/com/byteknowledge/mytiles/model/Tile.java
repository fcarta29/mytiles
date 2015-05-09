package com.byteknowledge.mytiles.model;

public class Tile {

    private String id;
    private String message;
    private String lastMovedById;
    private Long lastUpdatedTime;

    // TODO[fcarta] make these a dimension class or something
    private Long x;
    private Long y;
    private Long z;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Tile other = (Tile) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
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
        return "Tile [id=" + id + ", message=" + message + ", lastMovedById=" + lastMovedById + ", lastUpdatedTime="
                + lastUpdatedTime + ", x=" + x + ", y=" + y + ", z=" + z + "]";
    }
}
