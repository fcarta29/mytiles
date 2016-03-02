package com.byteknowledge.mytiles.dto;

import java.io.Serializable;

public final class TileCoordinatesDto implements Serializable {
	
	private static final long serialVersionUID = -175414022023089785L;

	private final String x;
    private final String y;
    private final String z;
    
    public TileCoordinatesDto(final Long x, final Long y, final Long z) {
    	this(x.toString(), y.toString(), z.toString());
    }
    
    public TileCoordinatesDto(final String x, final String y, final String z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getZ() {
        return z;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        result = prime * result + ((z == null) ? 0 : z.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof TileCoordinatesDto)) {
            return false;
        }
        TileCoordinatesDto other = (TileCoordinatesDto) obj;
        if (x == null) {
            if (other.x != null) {
                return false;
            }
        } else if (!x.equals(other.x)) {
            return false;
        }
        if (y == null) {
            if (other.y != null) {
                return false;
            }
        } else if (!y.equals(other.y)) {
            return false;
        }
        if (z == null) {
            if (other.z != null) {
                return false;
            }
        } else if (!z.equals(other.z)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TileCoordinatesDto [x=" + x + ", y=" + y + ", z=" + z + "]";
    }
}