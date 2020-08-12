package com.galid.commerce.domains.item.domain;

import com.galid.commerce.common.config.logging.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class ItemEntity extends BaseEntity {
    @Id @GeneratedValue
    private Long itemId;
    private String imagePath;
    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany
    @JoinColumn(name = "item_id")
    private List<CategoryEntity> categoryList;

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
