package com.galid.commerce.domains.cart.service;

import com.galid.commerce.domains.cart.domain.*;
import com.galid.commerce.domains.cart.query.dao.CartDao;
import com.galid.commerce.domains.cart.query.dto.CartLineDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final CartDao cartDao;
    private final CheckStockQuantityService checkStockQuantityService;

    public Long createCart(Long memberId) {
        return cartRepository.save(new CartEntity(memberId))
                .getCartId();
    }

    public void addItemToCart(Long memberId, AddToCartRequestForm addToCartRequestForm) {
        CartEntity cartEntity = cartRepository.findFirstByMemberId(memberId);

        CartLine newCartLine = new CartLine(cartEntity.getCartId(),
                addToCartRequestForm.getItemId(),
                addToCartRequestForm.getOrderCount());
        cartEntity.addItemToCart(checkStockQuantityService, newCartLine);
    }

    public List<CartLineDto> getCartInCartPage(Long memberId) {
        return cartDao.getCartLineListInCartPage(memberId);
    }

    public void modifyOrderCount(Long memberId, ModifyOrderCountRequestForm modifyOrderCountRequestForm) {
        // 엔티티 조회
        CartEntity cartEntity = cartRepository.findFirstByMemberId(memberId);

        CartLine newCartLine = new CartLine(cartEntity.getCartId(), modifyOrderCountRequestForm.getItemId(), modifyOrderCountRequestForm.getOrderCount());
        cartEntity.modifyOrderCount(checkStockQuantityService, newCartLine);
    }

    public void removeCartLines(Long memberId, List<Long> itemIds) {
        CartEntity cartEntity = cartRepository.findFirstByMemberId(memberId);

        itemIds.stream()
                .forEach(itemId -> cartEntity.removeCartLine(itemId));
    }

    public void removeCartLine(Long memberId, Long itemId) {
        removeCartLines(memberId, List.of(itemId));
    }
}
