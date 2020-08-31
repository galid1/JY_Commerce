package com.galid.commerce.domains.cart.infra;

import com.galid.commerce.domains.cart.query.dao.CartDao;
import com.galid.commerce.domains.cart.query.dto.CartLine;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaCartDao implements CartDao {
    private EntityManager em;
    private JPAQueryFactory query;

    public JpaCartDao(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<CartLine> getCartLineListInCartPage(Long memberId) {
        List<CartLine> cartLineList = em
                .createQuery("select new com.galid.commerce.domains.cart.service.CartLine(i.itemId, i.imagePath, i.name, i.price, cl.orderCount)" +
                        " from CartEntity c" +
                        " join c.cart cl" +
                        " on c.cartId = cl.cartId" +
                        " join MemberEntity m" +
                        " on c.memberId = m.memberId" +
                        " join ItemEntity i" +
                        " on cl.itemId = i.itemId" +
                        " where m.memberId = :memberId", CartLine.class)
                .setParameter("memberId", memberId)
                .getResultList();

        return cartLineList;
    }
}
