package com.galid.commerce.domains.catalog.domain.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CheckOrderedProductService {
    public void checkOrderedProduct(Long reviewTargetProductId, Set<Long> usersOrderedProductIdList) {
        if (! usersOrderedProductIdList.contains(reviewTargetProductId))
            throw new NotOrderedProductReviewException("구매한 상품만 리뷰작성이 가능합니다.");
    }
}
