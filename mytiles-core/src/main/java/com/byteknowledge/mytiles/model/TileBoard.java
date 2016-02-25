package com.byteknowledge.mytiles.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TileBoard extends UUIDEntity implements Serializable {

    private static final long serialVersionUID = -7821259691280564975L;

    private UUID creatorId;
    private UUID ownerId;
    private UUID tileBagId;
    private Set<User> participantIds = new HashSet<User>();
    private String name;
    private Long createdTime;
    private Long lastUpdatedTime;

    public UUID getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(UUID creatorId) {
        this.creatorId = creatorId;
    }
    
    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public UUID getTileBagId() {
        return tileBagId;
    }

    public void setTileBagId(UUID tileBagId) {
        this.tileBagId = tileBagId;
    }

    public Set<User> getParticipantIds() {
        return participantIds;
    }

    public void setParticipantIds(Set<User> participantIds) {
        this.participantIds = participantIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Long lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
    
    public TileBoard addParticipant(final User newParticipant) {
    	participantIds.add(newParticipant);
    	return this;
    }
    
    public TileBoard removeParticipant(final User removeParticipant) {
    	participantIds.remove(removeParticipant);
    	return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((creatorId == null) ? 0 : creatorId.hashCode());
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((tileBagId == null) ? 0 : tileBagId.hashCode());
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
        TileBoard other = (TileBoard) obj;
        if (creatorId == null) {
            if (other.creatorId != null)
                return false;
        } else if (!creatorId.equals(other.creatorId))
            return false;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        if (tileBagId == null) {
            if (other.tileBagId != null)
                return false;
        } else if (!tileBagId.equals(other.tileBagId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TileBoard [id=" + getId() + ", creatorId=" + creatorId + ", ownerId=" + ownerId + ", tileBagId=" + tileBagId
                + ", participantIds=" + participantIds + ", name=" + name + ", createdTime=" + createdTime
                + ", lastUpdatedTime=" + lastUpdatedTime + "]";
    }

    
}
