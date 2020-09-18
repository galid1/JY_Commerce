package com.galid.commerce.domains.order.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderSummaryDto {
    private List<OrderItemDto> orderItemList;
    private int totalAmount;

    public OrderSummaryDto(List<OrderItemDto> orderItemList) {
        this.orderItemList = orderItemList;
        this.totalAmount = orderItemList.stream()
                .mapToInt(orderItem -> orderItem.getTotalAmount())
                .sum();
    }
}
