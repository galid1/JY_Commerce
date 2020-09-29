package com.galid.commerce.domains.order.query.dao;

import com.galid.commerce.domains.order.domain.OrderEntity;

import java.util.List;

public interface MyOrderDao {
    List<OrderEntity> getMyOrders(Long memberId, int offset, int limit);
}
