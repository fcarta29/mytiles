package com.byteknowledge.mytiles.dto;

public abstract class UUIDDto {
    
    protected final String id;
    
    protected UUIDDto(final String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
}
