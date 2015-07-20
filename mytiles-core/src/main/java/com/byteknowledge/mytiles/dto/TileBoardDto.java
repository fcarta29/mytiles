package com.byteknowledge.mytiles.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TileBoardDto extends UUIDDto implements Serializable {

    private static final long serialVersionUID = -5612894327383415628L;

    private final String creatorId;
    private final String ownerId;
    private final String tileBagId;
    private final Set<UserDto> participantIds;
    private final String name;
    private final String createdTime;
    private final String lastUpdatedTime;

    private TileBoardDto(final TileBoardBuilder builder) {
        super(builder.id);
        this.creatorId = builder.creatorId;
        this.ownerId = builder.ownerId;
        this.tileBagId = builder.tileBagId;
        this.participantIds = builder.participantIds;
        this.name = builder.name;
        this.createdTime = builder.createdTime;
        this.lastUpdatedTime = builder.lastUpdatedTime;
    }
    
    public String getCreatorId() {
        return creatorId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getTileBagId() {
        return tileBagId;
    }

    public Set<UserDto> getParticipantIds() {
        return participantIds;
    }

    public String getName() {
        return name;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public String getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public static final class TileBoardBuilder {
        
        private final String creatorId;
        private final String name;
        private String id;
        private String ownerId;
        private String tileBagId;
        private Set<UserDto> participantIds = new HashSet<UserDto>();
        private String createdTime;
        private String lastUpdatedTime;        
        
        public TileBoardBuilder(final String creatorId, final String name) {
            this.creatorId = creatorId;
            this.name = name;
        }
        
        public TileBoardBuilder setId(final String id) {
            this.id = id;
            return this;
        }            
        
        public TileBoardBuilder setOwnerId(final String ownerId) {
            this.ownerId = ownerId;
            return this;
        } 
        
        public TileBoardBuilder setTileBagId(final String tileBagId) {
            this.tileBagId = tileBagId;
            return this;
        }         
        
        public TileBoardBuilder addParticipant(final UserDto participant) {
            this.participantIds.add(participant);
            return this;
        }
                
        public TileBoardBuilder setCreatedTime(final String createdTime) {
            this.createdTime = createdTime;
            return this;
        }     
        
        public TileBoardBuilder setLastUpdatedTime(final String lastUpdatedTime) {
            this.lastUpdatedTime = lastUpdatedTime;
            return this;
        }        
        
        public TileBoardDto build() {
            return new TileBoardDto(this);
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
        if (!(obj instanceof TileBoardDto)) {
            return false;
        }
        TileBoardDto other = (TileBoardDto) obj;
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
        return "TileBoard [id=" + getId() + ", creatorId=" + creatorId + ", ownerId=" + ownerId + ", tileBagId=" + tileBagId
                + ", participantIds=" + participantIds + ", name=" + name + ", createdTime=" + createdTime
                + ", lastUpdatedTime=" + lastUpdatedTime + "]";
    }
}
