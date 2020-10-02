package com.galid.commerce.domains.order.service;

import com.galid.commerce.domains.order.domain.OrderEntity;
import com.galid.commerce.domains.order.query.dao.MyOrderDao;
import com.galid.commerce.domains.order.query.dto.MyOrderDto;
import com.galid.commerce.domains.order.query.dto.MyOrderSummaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MyOrderService {
    private final MyOrderDao myOrderDao;

    public MyOrderSummaryDto getMyOrderSummary(Long ordererId, Pageable pageable) {
        Page<OrderEntity> myOrders = myOrderDao.getMyOrders(ordererId, pageable);

        List<MyOrderDto> contents = myOrders.stream()
                .map(o -> new MyOrderDto(o.getOrderId(),
                        o.getCreatedDate(),
                        o.getOrderItemList().get(0).getItem().getImagePath(),
                        o.getOrderItemList().get(0).getItem().getName(),
                        o.getTotalAmount()))
                .collect(Collectors.toList());
        int total = contents.size();

        return new MyOrderSummaryDto(contents, total);
    }
}
