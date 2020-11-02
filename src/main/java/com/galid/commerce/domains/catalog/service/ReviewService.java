package com.galid.commerce.domains.catalog.service;

import com.galid.commerce.domains.catalog.domain.review.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewProductRepository reviewProductRepository;

    @Transactional
    public Long review(Long reviewerId, ReviewRequest reviewRequest) {
        // 리뷰 생성
        ReviewEntity reviewEntity = ReviewEntity.builder()
                .review(new Review(
                        Rating.valueOf(reviewRequest.getRating()),
                        reviewRequest.getReview()
                ))
                .product(new Product(
                        reviewRequest.getProductId(),
                        reviewRequest.getProductName()
                ))
                .reviewerId(reviewerId)
                .build();
        ReviewEntity savedReview = reviewRepository.save(reviewEntity);

        // 리뷰 수 관리
        reviewProductRepository.findByProductId(reviewRequest.getProductId())
                .rate(Rating.valueOf(reviewRequest.getRating()));

        return savedReview.getReviewId();
    }

}
