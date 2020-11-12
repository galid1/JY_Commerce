package com.galid.commerce.domains.catalog.domain.review;

import com.galid.commerce.domains.catalog.infra.CheckOrderedProductServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CheckOrderedProductServiceTest {

    @Test
    public void 주문한_상품은_리뷰요청_가능() throws Exception {
        //given
        Long reviewTargetProductId = 1l;
        Set<Long> orderedProductIdSet = Set.of(1l, 2l, 3l, 4l);

        //when, then
        CheckOrderedProductService service = new CheckOrderedProductServiceImpl();
        assertDoesNotThrow(() -> service.checkOrderedProduct(reviewTargetProductId, orderedProductIdSet));
    }

    @Test
    public void 주문한_상품이_아닌것은_리뷰요청시_에러() throws Exception {
        //given
        Long reviewTargetProductId = 5l;
        Set<Long> orderedProductIdSet = Set.of(1l, 2l, 3l, 4l);

        //when, then
        CheckOrderedProductService service = new CheckOrderedProductServiceImpl();
        assertThrows(NotOrderedProductReviewException.class, () -> service.checkOrderedProduct(reviewTargetProductId, orderedProductIdSet));
    }
}