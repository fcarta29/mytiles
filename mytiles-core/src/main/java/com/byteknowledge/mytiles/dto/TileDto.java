package com.byteknowledge.mytiles.dto;

import java.io.Serializable;


public class TileDto extends UUIDDto implements Serializable {

    private static final long serialVersionUID = 793729365448185973L;

    private final String creatorId;
    private final String createdTime;
    private final String label; // TODO[fcarta] make into I18N id or mapping ?

    private TileDto(final TileBuilder builder) {
        super(builder.id);
        this.creatorId = builder.creatorId;
        this.createdTime = builder.createdTime;
        this.label = builder.label;
    }
    
    public String getCreatorId() {
        return creatorId;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public String getLabel() {
        return label;
    }
    
    public static final class TileBuilder {
        
        private final String label;
        private String id;
        private String creatorId;
        private String createdTime;
        
        public TileBuilder(final String label) {
            this.label = label;
        }
        
        public TileBuilder setId(final String id) {
            this.id = id;
            return this;
        }        
        
        public TileBuilder setCreatorId(final String creatorId) {
            this.creatorId = creatorId;
            return this;
        }
        
        public TileBuilder setCreatedTime(final String createdTime) {
            this.createdTime = createdTime;
            return this;
        }
        
        public TileDto build() {
            return new TileDto(this);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((label == null) ? 0 : label.hashCode());
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
        return "Tile [id=" + id + ", creatorId=" + creatorId + ", createdTime=" + createdTime 
                + ", label=" + label + "]";
    }

}
