package com.galid.commerce.domains.order.query.dao;

import com.galid.commerce.domains.order.domain.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MyOrderDao {
    Page<OrderEntity> getMyOrders(Long ordererId, Pageable pageable);
}
