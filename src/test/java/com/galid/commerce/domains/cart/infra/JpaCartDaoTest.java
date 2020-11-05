package com.galid.commerce.domains.cart.infra;

import com.galid.commerce.common.BaseTest;
import com.galid.commerce.domains.cart.domain.CartRepository;
import com.galid.commerce.domains.cart.query.dto.CartLineDto;
import com.galid.commerce.domains.cart.service.AddToCartRequestForm;
import com.galid.commerce.domains.cart.service.CartService;
import com.galid.commerce.domains.catalog.domain.item.ItemEntity;
import com.galid.commerce.domains.catalog.domain.item.ItemRepository;
import com.galid.commerce.domains.member.service.MemberService;
import com.galid.commerce.domains.member.service.SignUpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JpaCartDaoTest extends BaseTest {
    @Autowired
    private JpaCartDao dao;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private EntityManager em;

    private Long TEST_MEMBER_ID;
    private Long TEST_MEMBER_ID2;
    private Long TEST_ITEM_ID;
    private Long TEST_ITEM_ID2;

    @BeforeEach
    public void init() {
        TEST_MEMBER_ID = createMember("M1");
        TEST_ITEM_ID = createItem("i1");
        TEST_ITEM_ID2 = createItem("i2");

        TEST_MEMBER_ID2 = createMember("M2");
        createItem("i3");
        createItem("i4");
    }

    @Test
    public void Cart페이지_DTO요청() throws Exception {
        //given
        // member1의 장바구니에 아이템 추가
        addItemToCart(TEST_MEMBER_ID, TEST_ITEM_ID);
        addItemToCart(TEST_MEMBER_ID, TEST_ITEM_ID2);

        // member2의 장바구니에 아이템 추가
        addItemToCart(TEST_MEMBER_ID2, TEST_ITEM_ID);
        addItemToCart(TEST_MEMBER_ID2, TEST_ITEM_ID2);

        //when
        List<CartLineDto> cartInCartPage = dao.getCartLineListInCartPage(TEST_MEMBER_ID);
        em.clear();

        //then
        Map<Long, com.galid.commerce.domains.cart.domain.CartLine> cart = cartRepository.findFirstByMemberId(TEST_MEMBER_ID)
                .getCart();
        assertEquals(cartInCartPage.size(), cart.size(), "dao로 조회한 아이템 사이즈와, 사용자의 장바구니 사이즈가 일치해야함");
    }

// ============ HELPER =============
    private Long createMember(String authId) {
        return memberService.signUp(new SignUpRequest(authId, "TEST", authId, "TEST", "TEST", "TEST"));
    }

    private Long createItem(String itemName) {
        ItemEntity itemEntity = ItemEntity.builder()
                .imagePath("TEST")
                .name(itemName)
                .price(1000)
                .stockQuantity(3)
                .build();

        return itemRepository.save(itemEntity)
                .getItemId();
    }

    private void addItemToCart(Long memberId, Long itemId) {
        int orderCount = 1;
        AddToCartRequestForm request = new AddToCartRequestForm();
        request.setItemId(itemId);
        request.setOrderCount(orderCount);

        //when
        cartService.addItemToCart(memberId, request);
    }

}