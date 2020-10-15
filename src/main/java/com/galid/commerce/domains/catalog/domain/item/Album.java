package com.galid.commerce.domains.catalog.domain.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Album")
@Getter
@NoArgsConstructor
public class Album extends ItemEntity{
    private String artist;
    private String etc;

    @Builder
    public Album(String artist, String etc) {
        this.artist = artist;
        this.etc = etc;
    }
}