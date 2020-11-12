package com.galid.commerce.domains.catalog.domain.review;

import java.util.Set;

public interface CheckOrderedProductService {
    void checkOrderedProduct(Long reviewTargetProductId, Set<Long> usersOrderedProductIdList);
}
