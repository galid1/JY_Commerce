package com.galid.commerce.domains.order.domain;

import com.galid.commerce.common.config.logging.BaseEntity;
import com.galid.commerce.domains.delivery.domain.DeliveryEntity;
import com.galid.commerce.domains.member.domain.MemberEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private List<OrderItemEntity> orderItemList;

    public OrderEntity() {

    }
}
