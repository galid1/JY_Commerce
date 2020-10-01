package com.galid.commerce.domains.order.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class MyOrderSummaryDto {
    private List<MyOrderDto> myOrderList;
    private int total;
}
