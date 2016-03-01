package com.byteknowledge.mytiles.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.byteknowledge.mytiles.dto.TileDto.TileBuilder;

public class TileBagDto extends UUIDDto implements Serializable {

    private static final long serialVersionUID = -8919382365757243476L;

    private final String createdTime;
    private final String name;
    private final List<TileDto> tiles;

    private TileBagDto(final TileBagBuilder builder) {
        super(builder.id);
        this.createdTime = builder.createdTime;
        this.name = builder.name;
        this.tiles = new ArrayList<TileDto>();
        for (final TileBuilder tileBuilder : builder.tileBuilders) {
        	this.tiles.add(tileBuilder.build());
        }
    }    

    public String getCreatedTime() {
        return createdTime;
    }

    public String getName() {
        return name;
    }

    public List<TileDto> getTiles() {
        return tiles;
    }

    public static final class TileBagBuilder {
        
        private final String name;
        private String id;
        private String createdTime;
        private List<TileBuilder> tileBuilders = new ArrayList<TileBuilder>();
    
        public TileBagBuilder(final String name) {
            this.name = name;
        }
        
        public TileBagBuilder setId(final String id) {
            this.id = id;
            return this;
        }            
                
        public TileBagBuilder setCreatedTime(final String createdTime) {
            this.createdTime = createdTime;
            return this;
        }        
        
        public TileBagBuilder addTile(final TileBuilder tileBuilder) {
            this.tileBuilders.add(tileBuilder);
            return this;
        }
        
        public TileBagDto build() {
            return new TileBagDto(this);
        }
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (!(obj instanceof TileBagDto)) {
			return false;
		}
		TileBagDto other = (TileBagDto) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TileBagDto [createdTime=" + createdTime + ", name=" + name + ", tiles=" + tiles
				+ ", id=" + id + "]";
	}    

}
