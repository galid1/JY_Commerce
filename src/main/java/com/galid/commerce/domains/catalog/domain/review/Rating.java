package com.galid.commerce.domains.catalog.domain.review;

import lombok.Getter;

@Getter
public enum Rating {
    ONE(1.0), TWO(2.0), THREE(3.0), FOUR(4.0), FIVE(5.0);

    private Double value;

    Rating(Double value) {
        this.value = value;
    }
}
