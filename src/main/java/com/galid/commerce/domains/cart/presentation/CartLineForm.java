package com.galid.commerce.domains.cart.presentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CartLineForm {
    private Long itemId;
    private String itemImagePath;
    private String itemName;
    private int itemPrice;
    private int orderCount;
}
