package com.galid.commerce.domains.catalog.service;

import com.galid.commerce.domains.catalog.domain.review.*;
import com.galid.commerce.domains.catalog.query.dao.MyOrderedItemDao;
import com.galid.commerce.domains.order.domain.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewProductRepository reviewProductRepository;
    private final CheckOrderedProductService checkOrderedProductService;
    private final MyOrderedItemDao orderedProductDao;

    @Transactional
    public Long review(Long reviewerId, ReviewRequest reviewRequest) {
        // 사용자가 주문한 상품목록에 리뷰대상이 존재하는지 확인
        Set<Long> orderedItemIdSet = getOrderedItemIdSet(orderedProductDao.myOrderedListFromLastMonth(reviewerId));
        checkOrderedProductService.checkOrderedProduct(reviewRequest.getProductId(), orderedItemIdSet);

        // 리뷰 생성
        ReviewEntity reviewEntity = createReview(reviewerId, reviewRequest);
        ReviewEntity savedReview = reviewRepository.save(reviewEntity);

        // 리뷰 수 관리
        reviewProductRepository.findByProductId(reviewRequest.getProductId())
                .rate(Rating.valueOf(reviewRequest.getRating()));

        return savedReview.getReviewId();
    }

    private ReviewEntity createReview(Long reviewerId, ReviewRequest reviewRequest) {
        return ReviewEntity.builder()
                .review(new ReviewContent(
                        Rating.valueOf(reviewRequest.getRating()),
                        reviewRequest.getReview()
                ))
                .product(new Product(
                        reviewRequest.getProductId(),
                        reviewRequest.getProductName()
                ))
                .reviewerId(reviewerId)
                .build();
    }

    private Set<Long> getOrderedItemIdSet(List<OrderEntity> myOrderedItemIdListFromLastMonth) {
        Set<Long> collect = myOrderedItemIdListFromLastMonth
                .stream()
                .map(o -> o.getOrderItemList())
                .flatMap(olList -> olList.stream().map(ol -> ol.getItem().getItemId()))
                .collect(Collectors.toSet());

        return collect;
    }

}
