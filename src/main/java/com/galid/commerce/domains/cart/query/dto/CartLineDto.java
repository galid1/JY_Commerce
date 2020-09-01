package com.galid.commerce.domains.cart.query.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class CartLineDto {
    private Long itemId;
    private String itemImagePath;
    private String itemName;
    private int itemPrice;
    private int orderCount;

    @QueryProjection
    public CartLineDto(Long itemId, String itemImagePath, String itemName, int itemPrice, int orderCount) {
        this.itemId = itemId;
        this.itemImagePath = itemImagePath;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.orderCount = orderCount;
    }
}
