package com.galid.commerce.domains.catalog.domain.review;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@AllArgsConstructor
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewContent {
    @Enumerated(EnumType.STRING)
    private Rating rating;
    private String comment;
}
