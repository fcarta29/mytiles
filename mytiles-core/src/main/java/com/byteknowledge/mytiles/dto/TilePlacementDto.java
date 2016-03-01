package com.byteknowledge.mytiles.dto;

import java.io.Serializable;

public class TilePlacementDto implements Serializable  {

    private static final long serialVersionUID = -3848404695700941011L;

    private final String lastMovedById;
    private final String lastUpdatedTime;
    private final TileCoordinatesDto coordinates;
    
    private TilePlacementDto(final TilePlacementBuilder builder) {
        this.lastMovedById = builder.lastMovedById;
        this.lastUpdatedTime = builder.lastUpdatedTime;
        this.coordinates = builder.coordinates;
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

    	private final TileCoordinatesDto coordinates;
    	private String lastMovedById;
        private String lastUpdatedTime;

        public TilePlacementBuilder(final TileCoordinatesDto coordinates) {
            this.coordinates = coordinates;
        }
        
        public TilePlacementBuilder setlastUpdatedTime(final String lastUpdatedTime) {
            this.lastUpdatedTime = lastUpdatedTime;
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
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordinates == null) ? 0 : coordinates.hashCode());
		result = prime * result + ((lastMovedById == null) ? 0 : lastMovedById.hashCode());
		result = prime * result + ((lastUpdatedTime == null) ? 0 : lastUpdatedTime.hashCode());
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
		if (coordinates == null) {
			if (other.coordinates != null) {
				return false;
			}
		} else if (!coordinates.equals(other.coordinates)) {
			return false;
		}
		if (lastMovedById == null) {
			if (other.lastMovedById != null) {
				return false;
			}
		} else if (!lastMovedById.equals(other.lastMovedById)) {
			return false;
		}
		if (lastUpdatedTime == null) {
			if (other.lastUpdatedTime != null) {
				return false;
			}
		} else if (!lastUpdatedTime.equals(other.lastUpdatedTime)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TilePlacementDto [lastMovedById=" + lastMovedById + ", lastUpdatedTime=" + lastUpdatedTime
				+ ", coordinates=" + coordinates + "]";
	}

}
