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
    // 장바구니에서 수량 조절시 제한을 위해 사용(사용자 경험)
    private int stockQuantity;

    @QueryProjection
    public CartLineDto(Long itemId, String itemImagePath, String itemName, int itemPrice, int orderCount, int stockQuantity) {
        this.itemId = itemId;
        this.itemImagePath = itemImagePath;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.orderCount = orderCount;
        this.stockQuantity = stockQuantity;
    }
}
