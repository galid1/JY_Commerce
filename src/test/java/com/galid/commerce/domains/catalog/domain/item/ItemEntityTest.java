package com.galid.commerce.domains.catalog.domain.item;

import com.galid.commerce.domains.catalog.service.NotEnoughStockQuantityException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class ItemEntityTest {
    @Test
    public void 주문시_재고량_차감() throws Exception {
        //given
        int STOCK_QUANTITY = 10;
        ItemEntity itemEntity = createItemEntity(STOCK_QUANTITY);
        
        //when
        int ORDER_QUANTITY = 2;
        itemEntity.removeStockQuantity(ORDER_QUANTITY);
        
        //then
        assertThat(itemEntity.getStockQuantity(), is(STOCK_QUANTITY - ORDER_QUANTITY));
    }

    @Test
    public void 재고량을_초과하는_주문시_에러() throws Exception {
        //given
        int STOCK_QUANTITY = 10;
        ItemEntity itemEntity = createItemEntity(STOCK_QUANTITY);

        //when, then
        int OVER_ORDER_QUANTITY = 20;
        assertThrows(NotEnoughStockQuantityException.class,
                () -> itemEntity.removeStockQuantity(OVER_ORDER_QUANTITY));
    }

    @Test
    public void 재고량_추가() throws Exception {
        //given
        int STOCK_QUANTITY = 10;
        ItemEntity itemEntity = createItemEntity(STOCK_QUANTITY);

        //when
        int ADD_STOCK_QUANTITY = 10;
        itemEntity.addStockQuantity(ADD_STOCK_QUANTITY);

        //then
        assertThat(itemEntity.getStockQuantity(), is(STOCK_QUANTITY + ADD_STOCK_QUANTITY));
    }

    private ItemEntity createItemEntity(int stockQuantity) {
        return ItemEntity.builder()
                .categoryId(1l)
                .imagePath("TEST")
                .name("TEST")
                .price(1000)
                .stockQuantity(stockQuantity)
                .build();
    }

}