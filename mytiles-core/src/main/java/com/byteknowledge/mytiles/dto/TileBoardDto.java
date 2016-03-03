package com.byteknowledge.mytiles.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.byteknowledge.mytiles.dto.TileBagDto.TileBagBuilder;
import com.byteknowledge.mytiles.dto.UserDto.UserBuilder;

public class TileBoardDto extends UUIDDto implements Serializable {

    private static final long serialVersionUID = -5612894327383415628L;

    private final UserDto creator;
    private final UserDto owner;
    private final TileBagDto tileBag;
    private final Set<UserDto> participants;
    private final String name;
    private final String createdTime;
    private final String lastUpdatedTime;

    private TileBoardDto(final TileBoardBuilder builder) {
        super(builder.id);
        this.creator = builder.creatorBuilder.build();
        this.owner = builder.ownerBuilder.build();
        this.tileBag = builder.tileBagBuilder.build();
        this.participants = new HashSet<UserDto>();
        for (final UserBuilder participantBuilder : builder.participantBuilders) {
        	this.participants.add(participantBuilder.build());
        }
        this.name = builder.name;
        this.createdTime = builder.createdTime;
        this.lastUpdatedTime = builder.lastUpdatedTime;
    }
    
    public UserDto getCreator() {
        return creator;
    }

    public UserDto getOwner() {
        return owner;
    }

    public TileBagDto getTileBag() {
        return tileBag;
    }

    public Set<UserDto> getParticipants() {
        return participants;
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
        
    	private final UserBuilder creatorBuilder;
    	private final UserBuilder ownerBuilder;
        private final String name;
        
        private String id;
        private TileBagBuilder tileBagBuilder;
        private List<UserBuilder> participantBuilders = new ArrayList<UserBuilder>();
        private String createdTime;
        private String lastUpdatedTime;        
        
        public TileBoardBuilder(final UserBuilder creatorBuilder, final UserBuilder ownerBuilder, final String name) {
            this.creatorBuilder = creatorBuilder;
            this.ownerBuilder = ownerBuilder;
            this.name = name;
        }
        
        public TileBoardBuilder setId(final String id) {
            this.id = id;
            return this;
        }     
        
        public TileBoardBuilder setTileBag(final TileBagBuilder tileBagBuilder) {
        	this.tileBagBuilder = tileBagBuilder;        	
        	return this;
        }
        
        public TileBoardBuilder addParticipant(final UserBuilder participantBuilder) {
            this.participantBuilders.add(participantBuilder);
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
		int result = super.hashCode();
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		if (!(obj instanceof TileBoardDto)) {
			return false;
		}
		TileBoardDto other = (TileBoardDto) obj;
		if (creator == null) {
			if (other.creator != null) {
				return false;
			}
		} else if (!creator.equals(other.creator)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (owner == null) {
			if (other.owner != null) {
				return false;
			}
		} else if (!owner.equals(other.owner)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TileBoardDto [creator=" + creator + ", owner=" + owner + ", tileBag=" + tileBag + ", participants="
				+ participants + ", name=" + name + ", createdTime=" + createdTime + ", lastUpdatedTime="
				+ lastUpdatedTime + ", id=" + id + "]";
	}
    
}
