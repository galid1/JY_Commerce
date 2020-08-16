package com.galid.commerce.domains.cart.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddToCartRequestForm {
    private Long itemId;
    private Integer orderCount;
}
