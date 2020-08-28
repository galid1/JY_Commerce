package com.galid.commerce.domains.cart.query;

import com.galid.commerce.domains.cart.service.CartLineForm;

import java.util.List;

public interface CartDao {
    List<CartLineForm> getCartInCartPage(Long memberId);
}
