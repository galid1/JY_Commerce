package com.galid.commerce.domains.cart.service;

import com.galid.commerce.domains.cart.domain.CartEntity;
import com.galid.commerce.domains.cart.domain.CartRepository;
import com.galid.commerce.domains.item.domain.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public Map<Long, Integer> getCart(Long memberId) {
        return cartRepository.findFirstByMemberId(memberId)
                .get()
                .getCart();
    }

    public void addToCart(Long memberId, AddToCartRequestForm addToCartRequestForm) {
        Optional<CartEntity> findCart = cartRepository.findFirstByMemberId(memberId);

        if (findCart.isPresent())
            findCart.get().addToCart(addToCartRequestForm.getItemId(), addToCartRequestForm.getOrderCount());
        else {
            CartEntity cart = createCart(memberId);
            cart.addToCart(addToCartRequestForm.getItemId(), addToCartRequestForm.getOrderCount());
        }
    }

    private CartEntity createCart(Long memberId) {
        return cartRepository.save(new CartEntity(memberId));
    }

    public void removeItem(Long memberId, Long itemId) {

    }

    public void modifyCartLine(Long memberId, ModifyCartLineRequestForm modifyCartLineRequestForm) {
        CartEntity cartEntity = cartRepository.findFirstByMemberId(memberId).get();

        cartEntity.modifyOrderCount(modifyCartLineRequestForm.getItemId(), modifyCartLineRequestForm.getOrderCount());
    }
}
