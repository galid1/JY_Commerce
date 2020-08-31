package com.galid.commerce.domains.cart.query.dao;

import com.galid.commerce.domains.cart.query.dto.CartLine;

import java.util.List;

public interface CartDao {
    List<CartLine> getCartLineListInCartPage(Long memberId);
}
