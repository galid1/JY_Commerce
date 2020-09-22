package com.galid.commerce.domains.cart.service;

import com.galid.commerce.domains.cart.domain.CartEntity;
import com.galid.commerce.domains.cart.domain.CartLine;
import com.galid.commerce.domains.cart.domain.CartRepository;
import com.galid.commerce.domains.cart.domain.CheckStockQuantityService;
import com.galid.commerce.domains.cart.query.dao.CartDao;
import com.galid.commerce.domains.item.domain.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.AssertionErrors;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest(classes = {
        CartService.class,
        CheckStockQuantityService.class
})
class CartServiceTest {
    @Autowired
    private CartService cartService;

    @MockBean
    private CartRepository cartRepository;
    @MockBean
    private CartDao cartDao;
    @MockBean
    private CheckStockQuantityService checkStockQuantityService;

    private final Long TEST_MEMBER_ID = 1l;
    private final Long TEST_ITEM_ID = 2l;

    @BeforeEach
    public void init() {
        CartEntity cart = new CartEntity(1l);
        given(cartRepository.save(cart))
                .willReturn(cart);
        given(cartRepository.findFirstByMemberId(TEST_MEMBER_ID))
                .willReturn(cart);
    }

    @Test
    public void 장바구니_상품_추가() throws Exception {
        //given
        int orderCount = 1;
        AddToCartRequestForm request = createAddToCartRequestForm(orderCount);

        //when
        cartService.addItemToCart(TEST_MEMBER_ID, request);

        //then
        Map<Long, CartLine> cart = cartRepository.findFirstByMemberId(TEST_MEMBER_ID).getCart();
        assertEquals("장바구니에 추가된 주문 수량이 일치해야함.", cart.get(TEST_ITEM_ID).getOrderCount(), orderCount);
    }

    @Test
    public void 장바구니_수량_변경() throws Exception {
        //given
        // 장바구니 담기
        int orderCount = 1;
        AddToCartRequestForm request = createAddToCartRequestForm(orderCount);
        cartService.addItemToCart(TEST_MEMBER_ID, request);

        int newOrderCount = 2;
        ModifyOrderCountRequestForm modifyRequest = createModifyOrderCountRequestForm(newOrderCount);

        //when
        cartService.modifyOrderCount(TEST_MEMBER_ID, modifyRequest);

        //then
        Map<Long, CartLine> cart = cartRepository.findFirstByMemberId(TEST_MEMBER_ID).getCart();
        assertEquals("장바구니 주문 수량이 변경한 주문수량과 일치해야함.", cart.get(TEST_ITEM_ID).getOrderCount(), newOrderCount);
    }

    private ModifyOrderCountRequestForm createModifyOrderCountRequestForm(int newOrderCount) {
        ModifyOrderCountRequestForm modifyRequest = new ModifyOrderCountRequestForm();
        modifyRequest.setItemId(TEST_ITEM_ID);
        modifyRequest.setOrderCount(newOrderCount);

        return modifyRequest;
    }

    private AddToCartRequestForm createAddToCartRequestForm(int orderCount) {
        AddToCartRequestForm request = new AddToCartRequestForm();
        request.setItemId(TEST_ITEM_ID);
        request.setOrderCount(orderCount);
        return request;
    }

    @Test
    public void 장바구니_삭제() throws Exception {
        //given
        // 장바구니 담기
        int orderCount = 1;
        AddToCartRequestForm request = createAddToCartRequestForm(orderCount);
        cartService.addItemToCart(TEST_MEMBER_ID, request);

        //when
        cartService.removeCartLine(TEST_MEMBER_ID, TEST_ITEM_ID);

        //then
        Map<Long, CartLine> cart = cartRepository.findFirstByMemberId(TEST_MEMBER_ID).getCart();
        assertEquals("장바구니에서 삭제시 해당 품목은 NULL", cart.get(TEST_ITEM_ID), null);
    }
}