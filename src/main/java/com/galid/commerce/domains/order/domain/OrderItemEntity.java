package com.galid.commerce.domains.order.domain;

import com.galid.commerce.common.config.logging.BaseEntity;
import com.galid.commerce.domains.catalog.domain.ItemEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemEntity extends BaseEntity {
    @Id @GeneratedValue
    private Long orderItemId;
    private int orderCount;
    private int orderItemAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    @Builder
    public OrderItemEntity(ItemEntity item, int orderCount) {
        this.item = item;
        this.orderCount = orderCount;
        this.calculateOrderItemAmount();
    }

    private void calculateOrderItemAmount() {
        this.orderItemAmount = this.item.getPrice() * orderCount;
    }

    // ==== 비즈니스 로직 ====
    public void removeStockQuantity() {
        this.item.removeStockQuantity(orderCount);
    }

    public void cancel() {
        this.item.addStockQuantity(this.orderCount);
    }
}
