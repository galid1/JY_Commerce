package com.galid.commerce.domains.catalog.domain.review;

import com.galid.commerce.common.config.logging.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "review")
@Getter
@NoArgsConstructor
public class ReviewEntity extends BaseEntity {
    @Id @GeneratedValue
    private Long reviewId;

    @Embedded
    private Product product;
    @Embedded
    private Reviewer reviewer;
    @Embedded
    private Review review;

    @Builder
    public ReviewEntity(Product product, Reviewer reviewer, Review review) {
        setProduct(product);
        setReviewer(reviewer);
        setReview(review);
    }

    private void setProduct(Product reviewProduct) {
        if (reviewProduct == null)
            throw new IllegalArgumentException("no reviewproduct");
        this.product = reviewProduct;
    }

    private void setReviewer(Reviewer reviewer) {
        if (reviewer == null)
            throw new IllegalArgumentException("no reviewer");
        this.reviewer = reviewer;
    }

    private void setReview(Review review) {
        if (review == null)
            throw new IllegalArgumentException("no review");
        this.review = review;
    }

}
