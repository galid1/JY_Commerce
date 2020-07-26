package com.galid.commerce.domains.order.service;

import com.galid.commerce.domains.delivery.domain.DeliveryEntity;
import com.galid.commerce.domains.item.domain.ItemEntity;
import com.galid.commerce.domains.item.service.ItemService;
import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.domains.member.domain.MemberRepository;
import com.galid.commerce.domains.member.service.MemberService;
import com.galid.commerce.domains.order.domain.OrderEntity;
import com.galid.commerce.domains.order.domain.OrderItemEntity;
import com.galid.commerce.domains.order.domain.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final ItemService itemService;

    @Transactional
    public Long order(Long orderer, Long itemId, int count) {
        MemberEntity memberEntity = memberService.findMember(orderer);
        ItemEntity itemEntity = itemService.findItem(itemId);

        DeliveryEntity deliveryEntity = DeliveryEntity.builder()
                .address(memberEntity.getAddress())
                .build();

        OrderItemEntity orderItemEntity = OrderItemEntity.builder()
                .item(itemEntity)
                .orderQuantity(count)
                .build();

        OrderEntity orderEntity = OrderEntity.builder()
                .orderer(memberEntity)
                .deliveryInformation(deliveryEntity)
                .orderItemEntityList(orderItemEntity)
                .build();

        return orderRepository.save(orderEntity).getOrderId();
    }

}

