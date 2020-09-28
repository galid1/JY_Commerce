package com.galid.commerce.domains.order.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MyOrderDto {
    private Long orderId;
    private String representativeImagePath;
    private String representativeItemName;
    private int totalAmount;
}
