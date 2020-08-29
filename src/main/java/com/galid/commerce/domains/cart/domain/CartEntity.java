package com.galid.commerce.domains.cart.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "cart")
@Getter
@NoArgsConstructor
public class CartEntity {
    @Id @GeneratedValue
    private Long cartId;

    // Member를 참조하는 외래키 역할
    private Long memberId;

    // Map을 이용하므로써 @ElementCollection의 단점을 보완
    // itemId를 키로 사용
    // 1. 수정시, 모든 로우를 삭제 후, 수정된 로우를 추가하는 문제
    // 2. 삭제시, 모든 로우를 삭제 후, 삭제 대상을 제외한 모든 로우를 다시 입력하는 문제
    @ElementCollection
    @CollectionTable(
            name = "cart_line"
    )
    private Map<Long, CartLine> cart = new HashMap<>();

    public CartEntity(Long memberId) {
        this.memberId = memberId;
    }

    public void addItemToCart(CartLine cartLine) {
        Long mapKey = cartLine.getItemId();

        // 기존 아이템이 존재한다면 수량을 더함
        if (cart.containsKey(mapKey)) {
            CartLine existCartLine = cart.get(cartLine.getItemId());
            int newOrderCount = existCartLine.getOrderCount() + cartLine.getOrderCount();
            cart.replace(mapKey, new CartLine(cartId, cartLine.getItemId(), newOrderCount));
        }
        else {
            cart.put(mapKey, cartLine);
        }
    }

    public void modifyOrderCount(CartLine newCartLine) {
        this.cart.replace(newCartLine.getItemId(), newCartLine);
    }

    public void removeCartLine(Long cartItemId) {
        this.cart.remove(cartItemId);
    }

}
