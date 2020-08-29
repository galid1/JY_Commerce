package com.galid.commerce.domains.cart.infra;

import com.galid.commerce.domains.cart.query.dao.CartDao;
import com.galid.commerce.domains.cart.service.CartLineForm;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Component;
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
    public List<CartLineForm> getCartLineListInCartPage(Long memberId) {
        List<CartLineForm> cartLineFormList = em
                .createQuery("select new com.galid.commerce.domains.cart.service.CartLineForm(i.itemId, i.imagePath, i.name, i.price, cl.orderCount)" +
                        " from CartEntity c" +
                        " join c.cart cl" +
                        " on c.cartId = cl.cartId" +
                        " join MemberEntity m" +
                        " on c.memberId = m.memberId" +
                        " join ItemEntity i" +
                        " on cl.itemId = i.itemId" +
                        " where m.memberId = :memberId", CartLineForm.class)
                .setParameter("memberId", memberId)
                .getResultList();

        return cartLineFormList;
    }
}
