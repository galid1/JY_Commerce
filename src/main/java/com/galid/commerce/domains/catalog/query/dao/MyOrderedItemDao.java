package com.galid.commerce.domains.catalog.query.dao;

import com.galid.commerce.domains.order.domain.OrderEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static com.galid.commerce.domains.order.domain.QOrderEntity.orderEntity;

@Repository
public class MyOrderedItemDao {
    private JPAQueryFactory query;

    public MyOrderedItemDao(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<OrderEntity> myOrderedListFromLastMonth(Long memberId) {
        List<OrderEntity> fetch = query
                .select(orderEntity)
                .from(orderEntity)
                .where(orderEntity.orderer.memberId.eq(memberId))
                .where(orderEntity.createdDate.after(LocalDateTime.now().minusMonths(1l)))
                .fetch();

        return fetch;
    }
}
