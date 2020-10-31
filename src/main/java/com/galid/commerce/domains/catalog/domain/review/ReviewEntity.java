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
    private ReviewProduct reviewProduct;
    @Embedded
    private Reviewer reviewer;
    @Embedded
    private Review review;

    @Builder
    public ReviewEntity(ReviewProduct reviewProduct, Reviewer reviewer, Review review) {
        setReviewProduct(reviewProduct);
        setReviewer(reviewer);
        setReview(review);
    }

    private void setReviewProduct(ReviewProduct reviewProduct) {
        if (reviewProduct == null)
            throw new IllegalArgumentException("no reviewproduct");
        this.reviewProduct = reviewProduct;
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
