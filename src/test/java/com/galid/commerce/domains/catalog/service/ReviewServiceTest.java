package com.galid.commerce.domains.catalog.service;

import com.galid.commerce.domains.catalog.domain.review.*;
import com.galid.commerce.domains.catalog.query.dao.MyOrderedItemDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {
    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private ReviewProductRepository reviewProductRepository;
    @Mock
    private CheckOrderedProductService checkOrderedProductService;
    @Mock
    private MyOrderedItemDao myOrderedItemDao;

    private static Long REVIEWER_ID = 1l;
    private static Long REVIEW_TARGET_PRODUCT_ID = 1l;

    @Test
    public void 리뷰_작성_서비스() throws Exception {
        //given
        willDoNothing().given(checkOrderedProductService)
                .checkOrderedProduct(anyLong(), anySet());
        given(reviewRepository.save(any(ReviewEntity.class)))
                .willReturn(createReviewEntity());
        given(reviewProductRepository.findByProductId(REVIEW_TARGET_PRODUCT_ID))
                .willReturn(createReviewProductEntity());
        given(myOrderedItemDao.myOrderedListFromLastMonth(REVIEWER_ID))
                .willReturn(anyList());
        ReviewRequest reviewRequest = createReviewRequest();

        //when
        reviewService.review(REVIEWER_ID, reviewRequest);

        //then
        verify(reviewRepository, atLeastOnce()).save(any(ReviewEntity.class));
        verify(checkOrderedProductService, atLeastOnce()).checkOrderedProduct(anyLong(), anySet());
        verify(reviewProductRepository, atLeastOnce()).findByProductId(anyLong());
    }

    private ReviewEntity createReviewEntity() {
        ReviewRequest reviewRequest = createReviewRequest();

        return ReviewEntity.builder()
                .review(new ReviewContent(
                        Rating.valueOf(reviewRequest.getRating()),
                        reviewRequest.getReview()
                ))
                .product(new Product(
                        reviewRequest.getProductId(),
                        reviewRequest.getProductName()
                ))
                .reviewerId(REVIEWER_ID)
                .build();
    }

    private ReviewRequest createReviewRequest() {
        ReviewRequest request = new ReviewRequest();
        request.setReview("TEST");
        request.setRating(Rating.ONE.getValue());
        request.setProductId(REVIEW_TARGET_PRODUCT_ID);
        request.setProductName("TEST");
        return request;
    }

    private ReviewProductEntity createReviewProductEntity() {
        ReviewProductEntity reviewProductEntity = new ReviewProductEntity(REVIEW_TARGET_PRODUCT_ID);
        return reviewProductEntity;
    }
}