package com.galid.commerce.domains.catalog.service;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemSummaryInItemList {
    private Long itemId;
    private String imagePath;
    private String name;
    private int price;

    @QueryProjection
    public ItemSummaryInItemList(Long itemId, String imagePath, String name, int price) {
        this.itemId = itemId;
        this.imagePath = imagePath;
        this.name = name;
        this.price = price;
    }
}
