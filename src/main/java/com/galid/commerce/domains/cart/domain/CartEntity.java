package com.galid.commerce.domains.cart.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "cart")
@Getter
@NoArgsConstructor
public class CartEntity {
    @Id @GeneratedValue
    private Long cartId;

    private Long memberId;

    // key : itemId
    // value : orderCount
    @ElementCollection
    @JoinTable(name="cart_item", joinColumns=@JoinColumn(name="cart_id"))
    @MapKeyColumn (name="item_id")
    @Column(name="order_count")
    private Map<Long, Integer> cart = new HashMap<>();

    public CartEntity(Long memberId) {
        this.memberId = memberId;
    }

    public void addToCart(Long itemId, Integer orderCount) {
        if (isInCart(itemId)) {
            Integer beforeOrderCount = cart.get(itemId);
            cart.put(itemId, beforeOrderCount + orderCount);
        }
        else {
            cart.put(itemId, orderCount);
        }
    }

    public void modifyOrderCount(Long itemId, Integer orderCount) {
        if (!isInCart(itemId))
            throw new IllegalArgumentException("수정하려는 아이템이 카트에 존재하지 않습니다.");

        cart.put(itemId, orderCount);
    }

    private boolean isInCart(Long itemId) {
        return cart.containsKey(itemId);
    }

    public void removeAll() {
        cart.clear();
    }

    public void removeCartLine(Long itemId) {
        cart.remove(itemId);
    }
}
