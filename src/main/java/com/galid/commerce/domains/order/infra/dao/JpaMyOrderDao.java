package com.galid.commerce.domains.order.infra.dao;

import com.galid.commerce.domains.order.domain.OrderEntity;
import com.galid.commerce.domains.order.query.dao.MyOrderDao;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.galid.commerce.domains.delivery.domain.QDeliveryEntity.deliveryEntity;
import static com.galid.commerce.domains.member.domain.QMemberEntity.memberEntity;
import static com.galid.commerce.domains.order.domain.QOrderEntity.orderEntity;

@Repository
public class JpaMyOrderDao implements MyOrderDao {
    private JPAQueryFactory query;

    public JpaMyOrderDao(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<OrderEntity> getMyOrders(Long ordererId, int offset, int limit) {
        List<OrderEntity> result = query.select(orderEntity)
                .from(orderEntity)
                .join(orderEntity.orderer, memberEntity).fetchJoin()
                .join(orderEntity.deliveryInformation, deliveryEntity).fetchJoin()
                .where(orderEntity.orderer.memberId.eq(ordererId))
                .offset(offset)
                .limit(limit)
                .fetch();

        return result;
    }
}
