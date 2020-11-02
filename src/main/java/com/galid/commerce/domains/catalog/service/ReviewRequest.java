package com.galid.commerce.domains.catalog.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ReviewRequest {
    private Long productId;
    private String productName;
    private int rating;
    private String review;
}
