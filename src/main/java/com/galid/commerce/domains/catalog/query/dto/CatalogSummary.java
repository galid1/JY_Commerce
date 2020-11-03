package com.galid.commerce.domains.catalog.query.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CatalogSummary {
    private Long itemId;
    private String imagePath;
    private String name;
    private int price;
    private double reviewRating;
    private int reviewCount;

    @QueryProjection
    public CatalogSummary(Long itemId, String imagePath, String name, int price, double reviewRating, int reviewCount) {
        this.itemId = itemId;
        this.imagePath = imagePath;
        this.name = name;
        this.price = price;
        this.reviewCount = reviewCount;
        setReviewRating(reviewRating);
    }

    public void setReviewRating(double reviewRating) {
        int integer = (int)reviewRating;

        double decimal = reviewRating - integer;

        if (decimal > 0.8) {
            this.reviewRating = integer+1;
        } else if (decimal > 0.3){
            this.reviewRating = integer+0.5;
        } else {
            this.reviewRating = integer;
        }
    }
}
