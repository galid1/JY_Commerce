package com.galid.commerce.domains.cart.query.dao;

import com.galid.commerce.domains.cart.service.CartLineForm;

import java.util.List;

public interface CartDao {
    List<CartLineForm> getCartLineListInCartPage(Long memberId);
}
