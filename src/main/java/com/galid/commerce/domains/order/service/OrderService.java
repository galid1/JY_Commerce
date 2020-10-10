package com.galid.commerce.domains.order.service;

import com.galid.commerce.domains.cart.service.CartService;
import com.galid.commerce.domains.delivery.domain.DeliveryEntity;
import com.galid.commerce.domains.catalog.domain.ItemEntity;
import com.galid.commerce.domains.catalog.domain.ItemRepository;
import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.domains.member.service.MemberService;
import com.galid.commerce.domains.order.domain.OrderEntity;
import com.galid.commerce.domains.order.domain.OrderItemEntity;
import com.galid.commerce.domains.order.domain.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final CartService cartService;
    private final ItemRepository itemRepository;

    public Long order(Long ordererId, OrderRequest orderRequest) {
        // 엔티티 조회
        MemberEntity orderer = memberService.findMember(ordererId);

        // 배송지 생성
        DeliveryEntity deliveryEntity = DeliveryEntity.builder()
                .address(orderer.getAddress())
                .build();

        // 주문상품 생성
        List<OrderItemEntity> orderItemEntityList = orderRequest.getOrderLineList()
                .stream()
                .map(ol -> {
                    ItemEntity itemEntity = itemRepository.findById(ol.getItemId())
                            .get();
                    return new OrderItemEntity(itemEntity, ol.getOrderCount());
                })
                .collect(Collectors.toList());

        // 주문 상품 재고 줄이기
        orderItemEntityList.stream()
                .forEach(oi -> oi.removeStockQuantity());

        // 주문 생성
        OrderEntity orderEntity = OrderEntity.builder()
                .orderer(orderer)
                .deliveryInformation(deliveryEntity)
                .orderItemEntityList(orderItemEntityList)
                .build();

        // 장바구니 비우기
        List<Long> itemIdList = orderRequest.getOrderLineList().stream()
                .map(ol -> ol.getItemId())
                .collect(Collectors.toList());
        cartService.removeCartLines(orderer.getMemberId(), itemIdList);

        // 주문 저장
        return orderRepository.save(orderEntity).getOrderId();
    }

    public void cancel(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId).get();
        order.cancel();
    }


}

