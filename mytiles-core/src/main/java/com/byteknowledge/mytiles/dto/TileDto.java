package com.byteknowledge.mytiles.dto;

import java.io.Serializable;

import com.byteknowledge.mytiles.dto.TilePlacementDto.TilePlacementBuilder;


public class TileDto extends UUIDDto implements Serializable {

    private static final long serialVersionUID = 793729365448185973L;

    private final String createdTime;
    private final String label;
    private final TilePlacementDto placement;

    private TileDto(final TileBuilder builder) {
        super(builder.id);
        this.createdTime = builder.createdTime;
        this.label = builder.label;
        this.placement = builder.placementBuilder.build();
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public String getLabel() {
        return label;
    }
    
    public TilePlacementDto getPlacement() {
		return placement;
	}

	public static final class TileBuilder {
        
        private final String label;
        private String id;
        
        private String createdTime;
        private TilePlacementBuilder placementBuilder;
        
        public TileBuilder(final String label) {
        	this.label = label;
        }
        
        public TileBuilder setId(final String id) {
            this.id = id;
            return this;
        }        
        
        public TileBuilder setCreatedTime(final String createdTime) {
            this.createdTime = createdTime;
            return this;
        }
        
        public TileBuilder setPlacement(final TilePlacementDto.TilePlacementBuilder placementBuilder) {
        	this.placementBuilder = placementBuilder;
        	return this;
        }
        
        public TileDto build() {
            return new TileDto(this);
        }
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((label == null) ? 0 : label.hashCode());
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
		if (!(obj instanceof TileDto)) {
			return false;
		}
		TileDto other = (TileDto) obj;
		if (label == null) {
			if (other.label != null) {
				return false;
			}
		} else if (!label.equals(other.label)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TileDto [createdTime=" + createdTime + ", label=" + label + ", placement="
				+ placement + ", id=" + id + "]";
	}

}
