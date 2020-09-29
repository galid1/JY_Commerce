package com.galid.commerce.domains.order.service;

import com.galid.commerce.domains.order.domain.OrderEntity;
import com.galid.commerce.domains.order.domain.OrderRepository;
import com.galid.commerce.domains.order.query.dao.MyOrderDao;
import com.galid.commerce.domains.order.query.dto.MyOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MyOrderService {
    private final MyOrderDao myOrderDao;

    public List<MyOrderDto> getMyOrderSummary(Long memberId) {
        List<OrderEntity> myOrders = myOrderDao.getMyOrders(memberId, 0, 100);

        return myOrders.stream()
                .map(o -> new MyOrderDto(o.getOrderId(),
                        o.getCreatedDate(),
                        o.getOrderItemList().get(0).getItem().getImagePath(),
                        o.getOrderItemList().get(0).getItem().getName(),
                        o.getTotalAmount()))
                .collect(Collectors.toList());
    }
}
