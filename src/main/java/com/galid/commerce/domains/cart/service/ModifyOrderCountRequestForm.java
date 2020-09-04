package com.galid.commerce.domains.cart.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyOrderCountRequestForm {
    private Long itemId;
    private int orderCount;
}
