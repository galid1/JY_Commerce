package com.galid.commerce.domains.cart.service;

import com.galid.commerce.domains.cart.domain.*;
import com.galid.commerce.domains.cart.query.dao.CartDao;
import com.galid.commerce.domains.cart.query.dto.CartLineDto;
import com.galid.commerce.domains.item.domain.ItemEntity;
import com.galid.commerce.domains.item.domain.ItemRepository;
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
    private final ItemRepository itemRepository;

    public Long createCart(Long memberId) {
        return cartRepository.save(new CartEntity(memberId))
                .getCartId();
    }

    public void addItemToCart(Long memberId, AddToCartRequestForm addToCartRequestForm) {
        CartEntity cartEntity = cartRepository.findFirstByMemberId(memberId);

        CartLine newCartLine = new CartLine(cartEntity.getCartId(), addToCartRequestForm.getItemId(), addToCartRequestForm.getOrderCount());
        cartEntity.addItemToCart(newCartLine);
    }

    public List<CartLineDto> getCartInCartPage(Long memberId) {
        return cartDao.getCartLineListInCartPage(memberId);
    }

    public void modifyOrderCount(Long memberId, ModifyOrderCountRequestForm modifyOrderCountRequestForm) {
        // 엔티티 조회
        CartEntity cartEntity = cartRepository.findFirstByMemberId(memberId);
        ItemEntity itemEntity = itemRepository.findById(modifyOrderCountRequestForm.getItemId()).get();

        CartLine newCartLine = new CartLine(cartEntity.getCartId(), modifyOrderCountRequestForm.getItemId(), modifyOrderCountRequestForm.getOrderCount());

        CheckStockQuantityService checkStockQuantityService = new CheckStockQuantityService(newCartLine.getOrderCount(), itemEntity.getStockQuantity());
        cartEntity.modifyOrderCount(checkStockQuantityService,newCartLine);
    }

    public void removeCartLine(Long memberId, Long itemId) {
        CartEntity cartEntity = cartRepository.findFirstByMemberId(memberId);
        cartEntity.removeCartLine(itemId);
    }

    public void removeCartLines(Long memberId, List<Long> itemIds) {
        CartEntity cartEntity = cartRepository.findFirstByMemberId(memberId);

        itemIds.stream()
                .forEach(itemId -> cartEntity.removeCartLine(itemId));
    }
}
