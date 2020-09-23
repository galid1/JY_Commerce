package com.galid.commerce.domains.item.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Movie")
@Getter
@NoArgsConstructor
public class Movie extends ItemEntity{
    private String director;
    private String actor;

    @Builder
    public Movie(String director, String actor) {
        this.director = director;
        this.actor = actor;
    }
}
