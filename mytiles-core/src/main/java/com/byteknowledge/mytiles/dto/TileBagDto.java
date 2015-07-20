package com.byteknowledge.mytiles.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TileBagDto extends UUIDDto implements Serializable {

    private static final long serialVersionUID = -8919382365757243476L;

    private final String creatorId;
    private final String createdTime;
    private final String name;
    private final List<TileDto> tiles;

    private TileBagDto(final TileBagBuilder builder) {
        super(builder.id);
        this.creatorId = builder.creatorId;
        this.createdTime = builder.createdTime;
        this.name = builder.name;
        this.tiles = builder.tiles;
    }    
    
    public String getCreatorId() {
        return creatorId;
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
        
        private final String creatorId;
        private final String name;
        private String id;
        private String createdTime;
        private List<TileDto> tiles = new ArrayList<TileDto>();
    
        public TileBagBuilder(final String creatorId, final String name) {
            this.creatorId = creatorId;
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
        
        public TileBagBuilder addTile(final TileDto tile) {
            this.tiles.add(tile);
            return this;
        }
        
        public TileBagDto build() {
            return new TileBagDto(this);
        }
    }    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((creatorId == null) ? 0 : creatorId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        if (!(obj instanceof TileBagDto)) {
            return false;
        }
        TileBagDto other = (TileBagDto) obj;
        if (creatorId == null) {
            if (other.creatorId != null) {
                return false;
            }
        } else if (!creatorId.equals(other.creatorId)) {
            return false;
        }
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
        return "TileBag [id=" + getId() + ", creatorId=" + creatorId + ", createdTime=" + createdTime + ", name=" + name
                + ", tiles=" + tiles + "]";
    }

}
