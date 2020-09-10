package com.galid.commerce.domains.order.infra.dao;

import com.galid.commerce.domains.order.query.dao.OrderDao;
import com.galid.commerce.domains.order.query.dto.OrderItemDto;
import com.galid.commerce.domains.order.query.dto.OrderSummaryDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

@Repository
public class JpaOrderDao implements OrderDao {
    private EntityManager em;
    private JPAQueryFactory query;

    public JpaOrderDao(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public OrderSummaryDto getOrderSummary(Long memberId) {
        List<OrderItemDto> orderItemList = em.createQuery("select new com.galid.commerce.domains.order.query.dto.OrderItemDto(i.itemId, i.name, i.price, cl.orderCount)" +
                " from CartEntity c" +
                " join c.cart cl" +
                " on c.cartId = cl.cartId" +
                " join MemberEntity m" +
                " on c.memberId = m.memberId" +
                " join ItemEntity i" +
                " on cl.itemId = i.itemId" +
                " where m.memberId = :memberId", OrderItemDto.class)
                .setParameter("memberId", memberId)
                .getResultList();

        return new OrderSummaryDto(orderItemList);
    }
}
