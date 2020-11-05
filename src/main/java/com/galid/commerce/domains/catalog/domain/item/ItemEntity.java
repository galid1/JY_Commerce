package com.galid.commerce.domains.catalog.domain.item;

import com.galid.commerce.common.config.logging.BaseEntity;
import com.galid.commerce.domains.catalog.service.NotEnoughStockQuantityException;
import com.galid.commerce.domains.catalog.domain.category.CategoryEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemEntity extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;
    private String imagePath;
    private String name;
    private int price;
    private int stockQuantity;

    private Long categoryId;

    @Builder
    public ItemEntity(String imagePath, String name, int price, int stockQuantity, Long categoryId) {
        this.imagePath = imagePath;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
    }

    // ==== 비즈니스 로직 ====
    public void removeStockQuantity(int orderQuantity) {
        int restStockQuantity = this.stockQuantity - orderQuantity;

        if(restStockQuantity < 0)
            throw new NotEnoughStockQuantityException();

        this.stockQuantity = restStockQuantity;
    }

    public void addStockQuantity(int quantity) {
        this.stockQuantity += quantity;
    }
}
