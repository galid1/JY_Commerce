package com.galid.commerce.domains.cart.query.dao;

import com.galid.commerce.domains.cart.query.dto.CartLineDto;

import java.util.List;

public interface CartDao {
    List<CartLineDto> getCartLineListInCartPage(Long memberId);
}
