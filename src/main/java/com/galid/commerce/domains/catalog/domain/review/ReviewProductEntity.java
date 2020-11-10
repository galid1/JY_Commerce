package com.galid.commerce.domains.catalog.domain.review;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "review_product")
@Getter
@NoArgsConstructor
public class ReviewProductEntity {
    @Id @GeneratedValue
    private Long reviewProductId;

    @Column(unique = true)
    private Long productId;

    private double ratingAverage;
    private double totalRating;

    // 각 별점 평가 수 (상품이 별점을 각각 몇개 받았는지 보여줄 때 필요)
    private int oneCount;
    private int twoCount;
    private int threeCount;
    private int fourCount;
    private int fiveCount;

    private int totalCount;

    public ReviewProductEntity(Long productId) {
        this.productId = productId;
    }

    public void rate(Rating rating) {
        switch(rating) {
            case ONE:
                oneCount++;
                break;
            case TWO:
                twoCount++;
                break;
            case THREE:
                threeCount++;
                break;
            case FOUR:
                fourCount++;
                break;
            case FIVE:
                fiveCount++;
                break;
            default:
                throw new IllegalArgumentException("별점에 없는 수치 입니다.");
        }

        totalCount++;
        this.totalRating += rating.getValue();
        this.ratingAverage = totalRating/totalCount;
    }
}

