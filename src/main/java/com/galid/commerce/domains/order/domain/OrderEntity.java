package com.galid.commerce.domains.order.domain;

import com.galid.commerce.common.config.logging.BaseEntity;
import com.galid.commerce.domains.delivery.domain.DeliveryEntity;
import com.galid.commerce.domains.member.domain.MemberEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order")
@Getter
public class OrderEntity extends BaseEntity {
    @Id @GeneratedValue
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
    private List<OrderItemEntity> orderItemList;
}
