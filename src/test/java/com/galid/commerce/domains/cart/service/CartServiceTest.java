package com.galid.commerce.domains.cart.service;

import com.galid.commerce.common.BaseTest;
import com.galid.commerce.common.value.Address;
import com.galid.commerce.domains.cart.domain.CartEntity;
import com.galid.commerce.domains.cart.domain.CartRepository;
import com.galid.commerce.domains.item.domain.Book;
import com.galid.commerce.domains.item.domain.ItemEntity;
import com.galid.commerce.domains.item.domain.ItemRepository;
import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.domains.member.domain.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartServiceTest extends BaseTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartService cartService;

    @Test
    public void 장바구니_추가() throws Exception {
        //given
        MemberEntity member = createMember();
        ItemEntity item = createItem();

        //when
        int orderCount = 1;
        addToCart(member.getMemberId(), item.getItemId(), orderCount);

        //then
        Map<Long, Integer> cart = cartService.getCart(member.getMemberId());
        assertEquals(cart.get(item.getItemId()), orderCount, "장바구니에 담긴 수량과 주문수량이 일치");
    }

    @Test
    public void 장바구니_수량변경() throws Exception {
        //given
        Long memberId = createMember().getMemberId();
        Long itemId = createItem().getItemId();

        int orderCount = 1;
        addToCart(memberId, itemId, orderCount);

        //when
        int newOrderCount = 2;
        ModifyCartLineRequestForm modifyCartLineRequestForm = new ModifyCartLineRequestForm();
        modifyCartLineRequestForm.setItemId(itemId);
        modifyCartLineRequestForm.setOrderCount(newOrderCount);
        cartService.modifyCartLine(memberId, modifyCartLineRequestForm);

        //then
        Map<Long, Integer> cart = cartService.getCart(memberId);
        assertEquals(cart.get(itemId), newOrderCount, "주문수량은 변경된 주문수량과 일치");
    }

    @Test
    public void 장바구니_삭제() throws Exception {
        //given
        Long memberId = createMember().getMemberId();
        Long itemId = createItem().getItemId();
        addToCart(memberId, itemId, 1);

        //when
        cartService.removeItem(memberId, itemId);

        //then
        Map<Long, Integer> cart = cartService.getCart(memberId);

        assertEquals(cart.get(itemId), null, "삭제 후 카트에 존재하지 않아야 함");
    }

    // 장바구니 추가
    private void addToCart(Long memberId, Long itemId, int orderCount) {
        AddToCartRequestForm addToCartRequestForm = new AddToCartRequestForm();
        addToCartRequestForm.setItemId(itemId);
        addToCartRequestForm.setOrderCount(orderCount);

        //when
        cartService.addToCart(memberId, addToCartRequestForm);
    }

    private MemberEntity createMember() {
        MemberEntity memberEntity = MemberEntity.builder()
                .authId("TEST")
                .authPw("TEST")
                .address(new Address("TEST", "TEST"))
                .build();

        MemberEntity saveMember = memberRepository.save(memberEntity);
        createCart(saveMember.getMemberId());
        return saveMember;
    }

    private void createCart(Long memberId) {
        CartEntity cartEntity = new CartEntity(memberId);
        cartRepository.save(cartEntity);
    }

    private ItemEntity createItem() {
        ItemEntity itemEntity = Book.builder()
                .author("TEST")
                .imagePath("TEST")
                .isbn("TEST")
                .name("TEST")
                .price(1000)
                .stockQuantity(3)
                .build();

        return itemRepository.save(itemEntity);
    }



}