package com.galid.commerce.domains.order.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineRequest {
    private Long itemId;
    private int orderCount;
}
