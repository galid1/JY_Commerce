package com.galid.commerce.domains.item.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "item")
@Getter
public class ItemEntity {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<CategoryEntity> categoryList;

}
