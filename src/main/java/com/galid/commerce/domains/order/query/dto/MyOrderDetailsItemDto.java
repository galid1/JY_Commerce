package com.galid.commerce.domains.order.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MyOrderDetailsItemDto {
    private Long itemId;
    private String itemImagePath;
    private String itemName;
    private int itemPrice;
}
