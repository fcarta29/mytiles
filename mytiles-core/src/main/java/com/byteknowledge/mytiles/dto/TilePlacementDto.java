package com.byteknowledge.mytiles.dto;

import java.io.Serializable;

public class TilePlacementDto extends UUIDDto implements Serializable  {

    private static final long serialVersionUID = -3848404695700941011L;

    private final String tileId;
    private final String lastMovedById;
    private final String lastUpdatedTime;
    private final TileCoordinatesDto coordinates;
    
    private TilePlacementDto(final TilePlacementBuilder builder) {
        super(builder.tileId);
        this.tileId = builder.tileId;
        this.lastMovedById = builder.lastMovedById;
        this.lastUpdatedTime = builder.lastUpdatedTime;
        this.coordinates = builder.coordinates;
    }

    @Override
    public String getId() {
        return getTileId();
    }
    
    public String getTileId() {
        return tileId;
    }

    public String getLastMovedById() {
        return lastMovedById;
    }

    public String getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public TileCoordinatesDto getCoordinates() {
        return coordinates;
    }

    public static final class TilePlacementBuilder {
        private final String tileId;
        private final String lastUpdatedTime;

        private String lastMovedById;
        private TileCoordinatesDto coordinates;
        
        public TilePlacementBuilder(final String tileId, final String lastUpdatedTime) {
            this.tileId = tileId;
            this.lastUpdatedTime = lastUpdatedTime;
        }
        
        public TilePlacementBuilder setCoordinates(final TileCoordinatesDto coordinates) {
            this.coordinates = coordinates;
            return this;
        }
        
        public TilePlacementBuilder setLastMovedById(final String lastMovedById) {
            this.lastMovedById = lastMovedById;
            return this;
        }
        
        public TilePlacementDto build() {
            return new TilePlacementDto(this);
        }
    }
    
    public static final class TileCoordinatesDto {
        private final String x;
        private final String y;
        private final String z;
        
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lastUpdatedTime == null) ? 0 : lastUpdatedTime.hashCode());
        result = prime * result + ((tileId == null) ? 0 : tileId.hashCode());
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
        if (!(obj instanceof TilePlacementDto)) {
            return false;
        }
        TilePlacementDto other = (TilePlacementDto) obj;
        if (lastUpdatedTime == null) {
            if (other.lastUpdatedTime != null) {
                return false;
            }
        } else if (!lastUpdatedTime.equals(other.lastUpdatedTime)) {
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
        return "TilePlacementDto [tileId=" + tileId + ", lastMovedById=" + lastMovedById + ", lastUpdatedTime="
                + lastUpdatedTime + ", coordinates=" + coordinates + "]";
    }
}
