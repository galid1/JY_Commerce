package com.galid.commerce.domains.order.domain;

import com.galid.commerce.common.config.logging.BaseEntity;
import com.galid.commerce.domains.delivery.domain.DeliveryEntity;
import com.galid.commerce.domains.member.domain.MemberEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int totalAmount;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity orderer;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private DeliveryEntity deliveryInformation;
    @OneToMany
    @JoinColumn(name = "order_item_id")
    private List<OrderItemEntity> orderItemList = new ArrayList<>();

    @Builder
    public OrderEntity(MemberEntity orderer, DeliveryEntity deliveryInformation, OrderItemEntity... orderItemEntityList) {
        this.orderer = orderer;
        this.deliveryInformation = deliveryInformation;
        this.setOrderItemList(orderItemEntityList);
        this.status = OrderStatus.ORDERED_STATUS;
    }

    private void setOrderItemList(OrderItemEntity... orderItemEntityList) {
        Arrays.stream(orderItemEntityList)
                .forEach(orderItemEntity -> this.orderItemList.add(orderItemEntity));
        this.setTotalAmount();
    }

    private void setTotalAmount() {
        this.totalAmount = this.orderItemList.stream()
                .mapToInt(orderItem -> orderItem.getOrderItemAmount())
                .sum();
    }

}
