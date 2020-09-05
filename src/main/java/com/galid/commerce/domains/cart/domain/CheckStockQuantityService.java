package com.galid.commerce.domains.cart.domain;

import com.galid.commerce.domains.item.domain.NotEnoughStockQuantityException;

public class CheckStockQuantityService {
    private int orderCount;
    private int itemStockQuantity;

    public CheckStockQuantityService(int orderCount, int itemStockQuantity) {
        this.orderCount = orderCount;
        this.itemStockQuantity = itemStockQuantity;
    }

    public void checkEnoughStockQuantity() {
        if(this.itemStockQuantity < this.orderCount)
            throw new NotEnoughStockQuantityException();
    }
}
