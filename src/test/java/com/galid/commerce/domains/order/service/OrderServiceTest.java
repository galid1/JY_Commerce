package com.galid.commerce.domains.order.service;

import com.galid.commerce.common.BaseTest;
import com.galid.commerce.common.value.Address;
import com.galid.commerce.domains.item.domain.ItemEntity;
import com.galid.commerce.domains.item.service.ItemService;
import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.domains.member.domain.MemberRepository;
import com.galid.commerce.domains.order.domain.OrderEntity;
import com.galid.commerce.domains.order.domain.OrderRepository;
import com.galid.commerce.domains.order.domain.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class OrderServiceTest extends BaseTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void 주문() {
        //given
        MemberEntity TEST_MEMBER = MemberEntity.builder()
                .authId("TEST")
                .authPw("TEST")
                .address(new Address("TEST", "TEST"))
                .build();
        em.persist(TEST_MEMBER);

        ItemEntity TEST_ITEM = ItemEntity.builder()
                .name("TEST")
                .price(1000)
                .stockQuantity(2)
                .build();
        em.persist(TEST_ITEM);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(TEST_MEMBER.getMemberId(), TEST_ITEM.getItemId(), 2);

        //then
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .get();

        assertEquals(orderEntity.getStatus(), OrderStatus.ORDERED_STATUS);
        assertEquals(orderEntity.getOrderer(), TEST_MEMBER);
        assertEquals(orderEntity.getOrderItemList().size(), 1);
        assertEquals(orderEntity.getTotalAmount(), 1000 * orderCount);
    }

}