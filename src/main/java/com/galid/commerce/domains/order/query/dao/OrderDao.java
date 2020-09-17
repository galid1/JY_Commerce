package com.galid.commerce.domains.order.query.dao;

import com.galid.commerce.domains.order.query.dto.OrderSummaryDto;

import java.util.List;

public interface OrderDao {
    OrderSummaryDto getOrderSummaryInCart(Long memberId, List<Long> itemIdList);
}
