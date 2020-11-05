package com.galid.commerce.domains.order.service;

import com.galid.commerce.common.value.Address;
import com.galid.commerce.domains.catalog.domain.item.ItemEntity;
import com.galid.commerce.domains.delivery.domain.DeliveryEntity;
import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.domains.order.domain.OrderEntity;
import com.galid.commerce.domains.order.domain.OrderItemEntity;
import com.galid.commerce.domains.order.query.dao.MyOrderDao;
import com.galid.commerce.domains.order.query.dto.MyOrderDetailsDto;
import com.galid.commerce.domains.order.query.dto.MyOrderSummaryDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@ExtendWith(MockitoExtension.class)
class MyOrderServiceTest {
    @Mock
    private MyOrderDao myOrderDao;

    @InjectMocks
    private MyOrderService myOrderService;

    private Long TEST_ORDERER_ID = 1l;
    private Long TEST_ORDER_ID = 1l;

    @Test
    public void 내_주문_내역_가져오기() throws Exception {
        //given
        List<OrderEntity> content = List.of(createOrder());;
        PageRequest pageable = PageRequest.of(0, 1);
        int total = content.size();
        PageImpl<OrderEntity> orderList = new PageImpl<>(content, pageable, total);

        given(myOrderDao.getMyOrders(anyLong(), any(Pageable.class)))
                .willReturn(orderList);

        //when
        MyOrderSummaryDto myOrderSummary = myOrderService.getMyOrderSummary(TEST_ORDERER_ID, pageable);

        //then
        assertNotNull("주문목록 조회 결과는 NULL이 아니다.", myOrderSummary);
        verify(myOrderDao, atLeastOnce())
                .getMyOrders(TEST_ORDERER_ID, pageable);
    }

    @Test
    public void 주문상세_가져오기() throws Exception {
        //given
        OrderEntity order = createOrder();

        given(myOrderDao.getMyOrderDetails(TEST_ORDER_ID))
                .willReturn(Optional.of(order));

        //when
        MyOrderDetailsDto myOrderDetails = myOrderService.getMyOrderDetails(TEST_ORDER_ID);

        //then
        assertNotNull("주문상세내역 조회 결과는 NULL이 아니다.", myOrderDetails);
        verify(myOrderDao, atLeastOnce())
                .getMyOrderDetails(TEST_ORDER_ID);
    }

    private OrderEntity createOrder() {
        MemberEntity member = MemberEntity.builder()
                .name("JJY")
                .address(new Address("TEST", "TEST"))
                .authId("GALID")
                .authPw("TEST")
                .phone("TEST")
                .build();
        ReflectionTestUtils.setField(member, "memberId", TEST_ORDERER_ID);

        DeliveryEntity delivery = DeliveryEntity.builder()
                .address(member.getAddress())
                .build();

        ItemEntity item = ItemEntity.builder()
                .stockQuantity(2)
                .categoryId(1l)
                .price(1000)
                .imagePath("TEST")
                .name("TEST")
                .build();

        OrderItemEntity orderItem = OrderItemEntity.builder()
                .item(item)
                .orderCount(1)
                .build();

        OrderEntity orderEntity = OrderEntity.builder()
                .orderer(member)
                .deliveryInformation(delivery)
                .orderItemEntityList(List.of(orderItem))
                .build();

        ReflectionTestUtils.setField(orderEntity, "orderId", TEST_ORDER_ID);

        return  orderEntity;
    }
}