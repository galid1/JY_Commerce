package com.galid.commerce.domains.item.domain;

import com.galid.commerce.common.config.logging.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemEntity extends BaseEntity {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<CategoryEntity> categoryList;

}
