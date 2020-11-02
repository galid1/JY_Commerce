package com.galid.commerce.domains.catalog.domain.review;

import lombok.Getter;

@Getter
public enum Rating {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

    private int value;

    Rating(int value) {
        this.value = value;
    }

    public static Rating valueOf(int rating) {
        for(Rating r : Rating.values()) {
            if (r.getValue() == rating)
                return r;
        }
        return null;
    }
}
