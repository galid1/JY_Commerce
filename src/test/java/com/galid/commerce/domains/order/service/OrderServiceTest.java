package com.galid.commerce.domains.order.service;

import com.galid.commerce.common.value.Address;
import com.galid.commerce.domains.cart.service.CartService;
import com.galid.commerce.domains.delivery.domain.DeliveryEntity;
import com.galid.commerce.domains.catalog.domain.item.ItemEntity;
import com.galid.commerce.domains.catalog.domain.item.ItemRepository;
import com.galid.commerce.domains.catalog.service.NotEnoughStockQuantityException;
import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.domains.member.service.MemberService;
import com.galid.commerce.domains.order.domain.OrderEntity;
import com.galid.commerce.domains.order.domain.OrderItemEntity;
import com.galid.commerce.domains.order.domain.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private MemberService memberService;
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private CartService cartService;

    @Test
    // ordererId, orderRequest를 매개변수로 받아 orderRepository.save를 orderRepository.save 호출 했는지 확인
    public void 책_주문() {
        //given
            // 주문자
        Long TEST_MEMBER_ID = 1l;
        MemberEntity TEST_MEMBER = createMember();
        given(memberService.findMember(TEST_MEMBER_ID))
                .willReturn(TEST_MEMBER);

            // 주문 상품
        Long TEST_ITEM_ID = 2l;
        int stockQuantity = 2;
        ItemEntity TEST_ITEM = createItem(stockQuantity);
        ReflectionTestUtils.setField(TEST_ITEM, "itemId", TEST_ITEM_ID);
        given(itemRepository.findById(TEST_ITEM_ID))
                .willReturn(Optional.of(TEST_ITEM));

            // 장바구니 비우기
        willDoNothing()
                .given(cartService)
                .removeCartLines(any(), any());

            // 주문 요청
        int orderCount = 2;
        OrderRequest orderRequest = createOrderRequest(TEST_ITEM_ID, orderCount);

            // 주문
        OrderEntity TEST_ORDER = createOrder(TEST_MEMBER, TEST_ITEM, orderCount);
        given(orderRepository.save(any(OrderEntity.class)))
                .willReturn(TEST_ORDER);

        //when
        Long orderId = orderService.order(TEST_MEMBER_ID, orderRequest);

        //then
        verify(orderRepository, atLeastOnce())
                .save(any(OrderEntity.class));
        verify(cartService, atLeastOnce())
                .removeCartLines(any(), any());
    }

    private OrderEntity createOrder(MemberEntity TEST_MEMBER, ItemEntity TEST_ITEM, int orderCount) {
        OrderEntity TEST_ORDER = OrderEntity.builder()
                .deliveryInformation(new DeliveryEntity(TEST_MEMBER.getAddress()))
                .orderer(TEST_MEMBER)
                .orderItemEntityList(List.of(
                        OrderItemEntity.builder()
                                .orderCount(orderCount)
                                .item(TEST_ITEM)
                                .build()
                ))
                .build();
        Long TEST_ORDER_ID = 1l;
        ReflectionTestUtils.setField(TEST_ORDER, "orderId", TEST_ORDER_ID);

        return TEST_ORDER;
    }

    private OrderRequest createOrderRequest(Long itemId, int orderCount) {
        List<OrderLineRequest> orderLineRequests = List.of(new OrderLineRequest(itemId, orderCount));

        OrderRequest orderRequest = new OrderRequest(orderLineRequests);

        return orderRequest;
    }

    @Test
    public void 재고수량_초과_주문시_에러() throws Exception {
        //given
        Long TEST_MEMBER_ID = 1l;
        MemberEntity TEST_MEMBER = createMember();
        ReflectionTestUtils.setField(TEST_MEMBER, "memberId", TEST_MEMBER_ID);
        given(memberService.findMember(any()))
                .willReturn(TEST_MEMBER);

        Long TEST_ITEM_ID = 2l;
        int stockQuantity = 1;
        ItemEntity TEST_ITEM = createItem(stockQuantity);
        ReflectionTestUtils.setField(TEST_ITEM, "itemId", TEST_ITEM_ID);
        given(itemRepository.findById(any()))
                .willReturn(Optional.of(TEST_ITEM));

        //when
        int orderCount = 2;

        //then
        assertThrows(NotEnoughStockQuantityException.class,
                () -> orderService.order(TEST_MEMBER.getMemberId(), createOrderRequest(TEST_ITEM_ID, orderCount)));
    }

    @Test
    public void 주문취소() throws Exception {
        //given
        Long TEST_ORDER_ID = 1l;
        OrderEntity TEST_ORDER = mock(OrderEntity.class);
        willDoNothing()
                .given(TEST_ORDER).cancel();
        given(orderRepository.findById(any()))
                .willReturn(Optional.of(TEST_ORDER));

        //when
        orderService.cancel(TEST_ORDER_ID);

        //then
        verify(TEST_ORDER, atLeastOnce())
                .cancel();
    }

    private ItemEntity createItem(int stockQuantity) {
        ItemEntity TEST_ITEM = ItemEntity.builder()
                .name("TEST")
                .price(1000)
                .stockQuantity(stockQuantity)
                .imagePath("TEST")
                .categoryId(1l)
                .build();
        return TEST_ITEM;
    }

    private MemberEntity createMember() {
        MemberEntity TEST_MEMBER = MemberEntity.builder()
                .authId("TEST")
                .authPw("TEST")
                .address(new Address("TEST", "TEST"))
                .build();

        return TEST_MEMBER;
    }
}