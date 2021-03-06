package com.byteknowledge.mytiles.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class TileBoard extends AbstractUUIDEntity {

    private static final long serialVersionUID = -7821259691280564975L;

    private UUID creatorId;
    private UUID ownerId;
    private UUID tileBagId;
    private Set<UUID> participantIds = new HashSet<UUID>();
    private String name = StringUtils.EMPTY;
    private Long createdTime = Long.valueOf(0);
    private Long lastUpdatedTime = Long.valueOf(0);

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

    public Set<UUID> getParticipantIds() {
        return participantIds;
    }

    public void setParticipantIds(Set<UUID> participantIds) {
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
    	participantIds.add(newParticipant.getId());
    	return this;
    }
    
    public TileBoard removeParticipant(final User removeParticipant) {
    	participantIds.remove(removeParticipant.getId());
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
    	return ToStringBuilder.reflectionToString(this);
    }
}
