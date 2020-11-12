package com.galid.commerce.domains.catalog.infra;

import com.galid.commerce.domains.catalog.domain.review.CheckOrderedProductService;
import com.galid.commerce.domains.catalog.domain.review.NotOrderedProductReviewException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CheckOrderedProductServiceImpl implements CheckOrderedProductService {
    public void checkOrderedProduct(Long reviewTargetProductId, Set<Long> usersOrderedProductIdList) {
        if (! usersOrderedProductIdList.contains(reviewTargetProductId))
            throw new NotOrderedProductReviewException("구매한 상품만 리뷰작성이 가능합니다.");
    }
}
