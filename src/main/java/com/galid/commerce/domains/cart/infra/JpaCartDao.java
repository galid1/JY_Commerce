package com.galid.commerce.domains.cart.infra;

import com.galid.commerce.domains.cart.query.dao.CartDao;
import com.galid.commerce.domains.cart.query.dto.CartLineDto;
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
    public List<CartLineDto> getCartLineListInCartPage(Long memberId) {
        List<CartLineDto> cartLineDtoList = em
                .createQuery("select new com.galid.commerce.domains.cart.query.dto.CartLineDto(i.itemId, i.imagePath, i.name, i.price, cl.orderCount, i.stockQuantity)" +
                        " from CartEntity c" +
                        " join c.cart cl" +
                        " on c.cartId = cl.cartId" +
                        " join ItemEntity i" +
                        " on cl.itemId = i.itemId" +
                        " where c.memberId = :memberId", CartLineDto.class)
                .setParameter("memberId", memberId)
                .getResultList();

        return cartLineDtoList;
    }
}
