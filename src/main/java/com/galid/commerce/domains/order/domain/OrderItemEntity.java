package com.galid.commerce.domains.order.domain;

import com.galid.commerce.common.config.logging.BaseEntity;
import com.galid.commerce.domains.item.domain.ItemEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
public class OrderItemEntity extends BaseEntity {
    @Id @GeneratedValue
    private Long id;
    private int orderQuantity;
    private int orderItemAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity item;
}
