package com.galid.commerce.domains.cart.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@ExtendWith(MockitoExtension.class)
class CartEntityTest {
    private Long CART_ID = 1l;
    private Long CART_ITEM_ID = 1l;

    @Test
    public void 장바구니_추가() throws Exception {
        //given
        CartEntity cartEntity = createCartEntity();
        int ORDER_COUNT = 1;
        int TARGET_STOCK_QUANTITY = 2;
        CartLine cartLine = new CartLine(CART_ID, CART_ITEM_ID, ORDER_COUNT);

        //when
        cartEntity.addItemToCart(TARGET_STOCK_QUANTITY, cartLine);

        //then
        assertThat(cartEntity.getCart().get(CART_ITEM_ID).getOrderCount(), is(ORDER_COUNT));
    }

    @Test
    public void 장바구니_수량변경() throws Exception {
        //given
        CartEntity cartEntity = createCartEntity();
        int BEFORE_ORDER_COUNT = 1;
        cartEntity.addItemToCart(100, new CartLine(CART_ID, CART_ITEM_ID, BEFORE_ORDER_COUNT));

        //when
        int NEW_ORDER_COUNT = 2;
        cartEntity.modifyOrderCount(100, new CartLine(CART_ID, CART_ITEM_ID, NEW_ORDER_COUNT));

        //then
        assertThat(cartEntity.getCart().get(CART_ITEM_ID).getOrderCount(), is(NEW_ORDER_COUNT));
    }

    @Test
    public void 장바구니_아이템_삭제() throws Exception {
        //given
        CartEntity cartEntity = createCartEntity();
        cartEntity.addItemToCart(100, new CartLine(CART_ID, CART_ITEM_ID, 1));

        //when
        cartEntity.removeCartLine(CART_ITEM_ID);

        //then
        assertThat(cartEntity.getCart().containsKey(CART_ITEM_ID), is(false));
    }

    private CartEntity createCartEntity() {
        CartEntity cartEntity = new CartEntity(1l);
        ReflectionTestUtils.setField(cartEntity, "cartId", CART_ID);
        return cartEntity;
    }
}